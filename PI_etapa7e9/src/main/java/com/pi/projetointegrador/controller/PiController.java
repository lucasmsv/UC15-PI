package com.pi.projetointegrador.controller;

import com.pi.projetointegrador.model.Cliente;
import com.pi.projetointegrador.service.PiService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cadastro")
public class PiController {

    @Autowired
    private PiService piService;

    @GetMapping
    public String mostrarFormularioCadastro() {
        return "novo_cadastro";
    }

    @PostMapping
    public String cadastrarCliente(@RequestParam("nome") String nome,
            @RequestParam("documento") String documento,
            @RequestParam("tipoVeiculo") String tipoVeiculo,
            @RequestParam("placa") String placa,
            @RequestParam("dataHora") LocalDateTime dataHoraCadastro) {

        if (nome == null || nome.isEmpty() || documento == null || documento.isEmpty()
                || tipoVeiculo == null || tipoVeiculo.isEmpty() || placa == null || placa.isEmpty()
                || dataHoraCadastro == null) {
            return "redirect:/cadastro/erro";
        }
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(documento);
        cliente.setTipoVeiculo(tipoVeiculo);
        cliente.setPlaca(placa);
        cliente.setDataHoraCadastro(dataHoraCadastro);

        piService.cadastrarCliente(cliente);

        return "redirect:/cadastro/sucesso";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = piService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "lista_clientes";
    }

}
