package com.example.demo.controller;

import com.example.demo.mapper.SmestajMapper;
import com.example.demo.model.Smestaj;
import com.example.demo.model.User;
import com.example.demo.service.SmestajService;
import com.example.demo.ws.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import schema.wsdl.CreateSmestajIdReponse;
import schema.wsdl.CreateSmestajResponse;
import schema.wsdl.SmestajWS;

import java.util.List;

@RestController
@RequestMapping("/smestaj")
@CrossOrigin
public class SmestajController {

    private SmestajService smestajService;
    private WSClient wsClient;

    @Autowired
    public SmestajController(SmestajService smestajService, WSClient wsClient) {
        this.smestajService = smestajService;
        this.wsClient = wsClient;
    }

    @PostMapping(value = "/create")
    public Smestaj createSmestaj(@RequestBody Smestaj smestaj) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        smestaj.setAgent(authUser);
        CreateSmestajIdReponse smestajIdReponse = wsClient.createSmestajId();
        smestaj.setId(smestajIdReponse.getId());
        SmestajWS smestajWS = new SmestajWS();
        SmestajMapper smestajMapper = new SmestajMapper(smestaj, smestajWS);
        CreateSmestajResponse smestajResponse = wsClient.createSmestaj(smestajMapper.namapiraj());

        // smestaj.setAgent();
        return smestajService.addSmestaj(smestaj);
    }

    @PostMapping("/modify")
    public Smestaj modifySmestaj(@RequestBody Smestaj smestaj){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        smestaj.setAgent(authUser);
        SmestajWS smestajWS = new SmestajWS();
        smestajWS.setId(smestaj.getId());
        SmestajMapper smestajMapper = new SmestajMapper(smestaj, smestajWS);
        CreateSmestajResponse smestajResponse = wsClient.createSmestaj(smestajMapper.namapiraj());
        return smestajService.addSmestaj(smestaj);

    }

    @GetMapping(value = "/getAll")//sve smestaje tog agenta
    public List<Smestaj> getAll() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return smestajService.findSmestajByAgentId(authUser.getId());


    }

    @GetMapping(value = "/{smestajId}")
    public Smestaj getOneById(@PathVariable("smestajId") long id){
        return smestajService.findBySmestajId(id);
    }
}
