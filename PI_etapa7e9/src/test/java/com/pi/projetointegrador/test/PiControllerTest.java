package com.pi.projetointegrador.test;

import com.pi.projetointegrador.controller.PiController;
import com.pi.projetointegrador.model.Cliente;
import com.pi.projetointegrador.service.PiService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class PiControllerTest {

    @InjectMocks
    private PiController _PiController;

    @Mock
    private PiService _PiService;

    public PiControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setTipoVeiculo("Carro");
        cliente.setPlaca("ABC1234");
        cliente.setDataHoraCadastro(LocalDateTime.now());
        Mockito.when(_PiService.cadastrarCliente(Mockito.any(Cliente.class))).thenReturn(cliente);
        String resultado = _PiController.cadastrarCliente(cliente.getNome(), cliente.getCpf(), 
                cliente.getTipoVeiculo(), cliente.getPlaca(), cliente.getDataHoraCadastro());
        assertEquals("redirect:/cadastro/sucesso", resultado);
    }

    @Test
    public void testCadastrarClienteComParametrosInvalidos() {
        String nome = "";
        String documento = "12345678900";
        String tipoVeiculo = "";
        String placa = "ABC12345";
        LocalDateTime dataHoraCadastro = LocalDateTime.now();
        String resultado = _PiController.cadastrarCliente(nome, documento, tipoVeiculo, placa, dataHoraCadastro);
        assertEquals("redirect:/cadastro/erro", resultado);
    }

    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Maria", "98765432100", "Moto", "XYZ5678", LocalDateTime.now()));
        clientes.add(new Cliente("Pedro", "55544433322", "Caminhão", "DEF9012", LocalDateTime.now()));
        Mockito.when(_PiService.listarClientes()).thenReturn(clientes);
        Model model = Mockito.mock(Model.class);
        String viewName = _PiController.listarClientes(model);
        ArgumentCaptor<List<Cliente>> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(model).addAttribute(Mockito.eq("clientes"), argument.capture());
        assertEquals(clientes, argument.getValue());
        assertEquals("lista_clientes", viewName);
    }

    @Test
    public void testListarClientesVazia() {
        Model model = Mockito.mock(Model.class);
        Mockito.when(_PiService.listarClientes()).thenReturn(new ArrayList<>());
        String viewName = _PiController.listarClientes(model);
        ArgumentCaptor<List<Cliente>> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(model).addAttribute(Mockito.eq("clientes"), argument.capture());
        assertTrue(argument.getValue().isEmpty());
        assertEquals("lista_clientes", viewName);
    }

}
