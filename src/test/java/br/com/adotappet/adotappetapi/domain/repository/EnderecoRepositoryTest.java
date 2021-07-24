package br.com.adotappet.adotappetapi.domain.repository;

import br.com.adotappet.adotappetapi.domain.entity.Endereco;
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
public class EnderecoRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;

    @BeforeEach
    public void setup() {
        endereco = new Endereco();
    }

    @Test
    public void deveSalvarUmEndereco(){
        enderecoRepository.save(endereco);
        assertNotNull(endereco.getId());
    }

    @Test
    public void deveBuscarUmEndereco() {
        enderecoRepository.save(endereco);
        Optional<Endereco> enderecoDb = enderecoRepository.findById(endereco.getId());
        assertTrue(enderecoDb.isPresent());
    }

    @Test
    public void deveDeletarUmEndereco() {
        enderecoRepository.save(endereco);
        assertFalse(enderecoRepository.findAll().isEmpty());
        enderecoRepository.delete(endereco);
        assertTrue(enderecoRepository.findAll().isEmpty());
    }

    @Test
    public void deveAtualizarUmEndereco() {
        enderecoRepository.save(endereco);
        String cidade = "Diadema";
        endereco.setCidade(cidade);
        enderecoRepository.save(endereco);
        Optional<Endereco> enderecoDb = enderecoRepository.findById(endereco.getId());
        assertTrue(enderecoDb.isPresent());
        assertEquals(cidade, enderecoDb.get().getCidade());
    }
}
