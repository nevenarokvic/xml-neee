package com.example.demo.controller;

import com.example.demo.model.Rezervacija;
import com.example.demo.service.RezervisanSmestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class RezervisanSmestajController {

    private RezervisanSmestajService rezervisanSmestajService;

    @Autowired
    public  RezervisanSmestajController(RezervisanSmestajService rezervisanSmestajService){
        this.rezervisanSmestajService = rezervisanSmestajService;
    }

    @PostMapping
    public Rezervacija createRezervacija(@RequestBody Rezervacija rezervacija){

        return rezervisanSmestajService.addRezervacija(rezervacija);
    }

    @PostMapping(value = "/confirmReservation")
    public Rezervacija confirmReservationByAgent(@RequestBody Rezervacija rezervacija){
         Rezervacija r = rezervisanSmestajService.findByIdRezervacije(rezervacija);
         rezervisanSmestajService.setConfirmed(r);
         return rezervisanSmestajService.addRezervacija(r);

    }

    @GetMapping("/get/{id}")
    public Rezervacija getRezervacijaById(@PathVariable("id") long id) {
        return rezervisanSmestajService.findById(id);
    }

    @GetMapping(path = "/getAll")
    public List<Rezervacija> getAll() {
        return rezervisanSmestajService.findAll();
    }

}
