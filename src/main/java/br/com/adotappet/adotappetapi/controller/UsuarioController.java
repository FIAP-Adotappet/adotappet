package br.com.adotappet.adotappetapi.controller;

import br.com.adotappet.adotappetapi.dto.LoginDTO;
import br.com.adotappet.adotappetapi.dto.UsuarioDTO;
import br.com.adotappet.adotappetapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioDTO newUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.criaUsuario(usuarioDTO);
    }

    @PostMapping("/{id}")
    public UsuarioDTO updateUser(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
        return usuarioService.atualizaUsuario(usuarioDTO, id);
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping("/login")
    public UsuarioDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return usuarioService.login(loginDTO);
    }
}
