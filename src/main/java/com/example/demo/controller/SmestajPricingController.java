package com.example.demo.controller;

import com.example.demo.model.Smestaj;
import com.example.demo.model.SmestajPricing;
import com.example.demo.model.dto.PricingDTO;
import com.example.demo.service.SmestajPricingService;
import com.example.demo.service.SmestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
public class SmestajPricingController {

    private SmestajPricingService smestajPricingService;
    private SmestajService smestajService;

    @Autowired
    public SmestajPricingController(SmestajPricingService smestajPricingService, SmestajService smestajService){
        this.smestajPricingService = smestajPricingService;
        this.smestajService = smestajService;
    }

    @PostMapping("/{smestajId}")
    public boolean addSmestajPricing(@PathVariable("smestajId") long id, @RequestBody PricingDTO pricingDTO){
        boolean isAdded = false;
        if(smestajService.smestajExists(id) && smestajPricingService.smestajDontExist(id)) {
            Smestaj s = smestajService.findBySmestajId(id);
            SmestajPricing sp = new SmestajPricing(pricingDTO);
            sp.setSmestaj(s);
            smestajPricingService.savePricing(sp);
            isAdded = true;
        }
        return isAdded;
    }


    @GetMapping("/{smestajId}")
    public SmestajPricing findPricingBySmestajId(@PathVariable("smestajId") long id){
        return smestajPricingService.getBySmestajId(id);
    }

    @DeleteMapping("/{smestajId}")
    public void deletePricing(@PathVariable("smestajId") long id){
         smestajPricingService.deletePricing(id);
    }

    @PutMapping("/{smestajId}")
    public SmestajPricing modifyPricing(@PathVariable("smestajId") long id, @RequestBody SmestajPricing smestajPricing){
        SmestajPricing sp = smestajPricingService.getBySmestajId(id);
        sp.setDTO(smestajPricing);
        return smestajPricingService.savePricing(sp);
    }

}
