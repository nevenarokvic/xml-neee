package com.example.demo.service;

import com.example.demo.model.Rezervacija;
import com.example.demo.model.Zauzetost;
import com.example.demo.repository.ZauzetostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZauzetostService {

    @Autowired
     ZauzetostRepository zauzetostRepository;

    public List<Zauzetost> getAll(Long smestajId){
        return zauzetostRepository.findBySmestajId(smestajId);
    }

    public Zauzetost getById(Long id){
        Optional<Zauzetost> z = zauzetostRepository.findById(id);
        return z.get();
    }

    public Zauzetost addZauzetost(Zauzetost zauzetost){
        return zauzetostRepository.save(zauzetost);
    }

    public List<Zauzetost> addAllZauzetosti(List<Zauzetost> zauzetosts){
        return zauzetostRepository.saveAll(zauzetosts);
    }

    public void deleteZauzetostById(Long id){
        zauzetostRepository.deleteById(id);
    }




}
