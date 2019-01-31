package com.example.demo.controller;

import com.example.demo.model.Zauzetost;
import com.example.demo.service.SmestajService;
import com.example.demo.service.ZauzetostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zauzetost")
public class ZauzetostController {

    @Autowired
    ZauzetostService zauzetostService;
    @Autowired
    SmestajService smestajService;


    @GetMapping("/{smestajId}")
    public List<Zauzetost> getAll(@PathVariable("smestajId") long id){
        return zauzetostService.getAll(id);
    }

    @GetMapping("/getOne/{zId}")
    public Zauzetost getOne(@PathVariable("zId") long id){
        return zauzetostService.getById(id);
    }
    @PostMapping
    public Zauzetost addZauzetost(@RequestBody Zauzetost z){//moze samo id od smestaja u telo zahteva
        z.setSmestaj(smestajService.findBySmestajId(z.getSmestaj().getId()));
        return zauzetostService.addZauzetost(z);

    }

    @DeleteMapping("/{zId}")
    public void removeZauzetost(@PathVariable("zId") long id){
        zauzetostService.deleteZauzetostById(id);
    }

    @PutMapping("/{zId}")
    public Zauzetost modifyZauzetost(@PathVariable("zId") long id, @RequestBody Zauzetost zauzetost){
        Zauzetost z = zauzetostService.getById(id);
        z.setSmestaj(zauzetost.getSmestaj());
        z.setEndDate(zauzetost.getEndDate());
        z.setStartDate(zauzetost.getStartDate());
        z.setId(id);

        zauzetostService.addZauzetost(z);
        return  z;
    }
}
