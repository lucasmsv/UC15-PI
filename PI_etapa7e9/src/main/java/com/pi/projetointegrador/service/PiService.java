package com.pi.projetointegrador.service;

import com.pi.projetointegrador.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PiService {

    @Autowired
    private PiRepository piRepository;

    public List<Cliente> listarClientes() {
        return piRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return piRepository.save(cliente);
    }
}
