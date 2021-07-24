package br.com.adotappet.adotappetapi.domain.service;

import br.com.adotappet.adotappetapi.api.dto.LoginDTO;
import br.com.adotappet.adotappetapi.api.dto.UsuarioDTO;
import br.com.adotappet.adotappetapi.domain.entity.Usuario;
import br.com.adotappet.adotappetapi.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public UsuarioDTO criaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);
        return toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO atualizaUsuario(UsuarioDTO usuarioDTO, Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        updateEntity(usuarioDTO, usuario);
        return toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO findById(Long id) {
        return toDTO(usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado")));
    }

    public UsuarioDTO login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return toDTO(usuario);
    }

    private Usuario toEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private void updateEntity(UsuarioDTO usuarioDTO, Usuario usuario) {
        modelMapper.map(usuarioDTO, usuario);
    }
}
