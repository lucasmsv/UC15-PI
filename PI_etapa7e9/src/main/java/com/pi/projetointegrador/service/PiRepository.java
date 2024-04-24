package com.pi.projetointegrador.service;

import com.pi.projetointegrador.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PiRepository extends JpaRepository<Cliente, Long> {
    // 
}
