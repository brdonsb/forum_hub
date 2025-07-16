package br.com.brdonsb.forum_hub.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class AutenticacaoServiceTest {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Test
    void deveEncontrarUsuarioPorEmail() {
        UserDetails usuario = autenticacaoService.loadUserByUsername("pedro.carlos@forumhub");
        assertNotNull(usuario);
        assertEquals("pedro.carlos@forumhub", usuario.getUsername());
    }

    @Test
    void deveLancarExcecaoParaEmailInexistente() {
        assertThrows(RuntimeException.class, () -> {
            autenticacaoService.loadUserByUsername("email.invalido@teste.com");
        });
    }
}
