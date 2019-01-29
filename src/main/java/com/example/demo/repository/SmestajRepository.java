package com.example.demo.repository;

import com.example.demo.model.Smestaj;
import com.example.demo.model.SmestajPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {

    Smestaj findById(long smestajId);
    List<Smestaj> findByAgentId(long agentId);


}
