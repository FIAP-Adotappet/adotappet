package br.com.adotappet.adotappetapi.controller;

import br.com.adotappet.adotappetapi.dto.UsuarioDTO;
import br.com.adotappet.adotappetapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioDTO newUser(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.criaUsuario(usuarioDTO);
    }

    @PostMapping("/{id}")
    public UsuarioDTO updateUser(@Validated @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
        return usuarioService.atualizaUsuario(usuarioDTO, id);
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    //TODO login
}
