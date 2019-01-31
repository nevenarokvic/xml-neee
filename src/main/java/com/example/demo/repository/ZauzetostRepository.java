package com.example.demo.repository;

import com.example.demo.model.Zauzetost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZauzetostRepository extends JpaRepository<Zauzetost, Long> {
        List<Zauzetost> findBySmestajId(Long smestajId);
}
