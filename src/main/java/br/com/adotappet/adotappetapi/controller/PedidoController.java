package br.com.adotappet.adotappetapi.controller;

import br.com.adotappet.adotappetapi.dto.PedidoDTO;
import br.com.adotappet.adotappetapi.dto.PetDTO;
import br.com.adotappet.adotappetapi.dto.UsuarioDTO;
import br.com.adotappet.adotappetapi.service.PedidoService;
import br.com.adotappet.adotappetapi.service.PetService;
import br.com.adotappet.adotappetapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final PetService petService;
    private final UsuarioService usuarioService;

    @Autowired
    public PedidoController(PedidoService pedidoService, PetService petService, UsuarioService usuarioService) {
        this.pedidoService = pedidoService;
        this.petService = petService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/novo/{petId}/{usuarioId}")
    public PedidoDTO novo(@PathVariable Long petId, @PathVariable Long usuarioId) {
        PetDTO petDTO = petService.findById(petId);
        UsuarioDTO usuarioDTO = usuarioService.findById(usuarioId);
        return pedidoService.iniciaPedido(usuarioDTO, petDTO);
    }

    @GetMapping("/aprova/{pedidoId}")
    public PedidoDTO aprova(@PathVariable Long pedidoId) {
        return pedidoService.aprovaPedido(pedidoId, true);
    }

    @GetMapping("/rejeita/{pedidoId}")
    public PedidoDTO rejeita(@PathVariable Long pedidoId) {
        return pedidoService.aprovaPedido(pedidoId, false);
    }

    @GetMapping("/finaliza/{pedidoId}")
    public PedidoDTO finaliza(@PathVariable Long pedidoId) {
        return pedidoService.finalizaPedido(pedidoId);
    }

    @GetMapping("/cancela/{pedidoId}")
    public PedidoDTO cancela(@PathVariable Long pedidoId) {
        return pedidoService.cancelaPedido(pedidoId);
    }
}
