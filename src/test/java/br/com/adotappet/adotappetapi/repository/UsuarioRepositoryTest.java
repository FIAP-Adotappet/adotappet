package br.com.adotappet.adotappetapi.repository;

import br.com.adotappet.adotappetapi.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
    }

    @Test
    public void deveSalvarUmUsuario(){
        usuarioRepository.save(usuario);
        assertNotNull(usuario.getId());
    }

    @Test
    public void deveBuscarUmUsuario() {
        usuarioRepository.save(usuario);
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuario.getId());
        assertTrue(usuarioDb.isPresent());
    }

    @Test
    public void deveDeletarUmUsuario() {
        usuarioRepository.save(usuario);
        assertFalse(usuarioRepository.findAll().isEmpty());
        usuarioRepository.delete(usuario);
        assertTrue(usuarioRepository.findAll().isEmpty());
    }

    @Test
    public void deveAtualizarUmUsuario() {
        usuarioRepository.save(usuario);
        String nomeUsuario = "João do Teste";
        usuario.setNomeCompleto(nomeUsuario);
        usuarioRepository.save(usuario);
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuario.getId());
        assertTrue(usuarioDb.isPresent());
        assertEquals(nomeUsuario, usuarioDb.get().getNomeCompleto());
    }

}
