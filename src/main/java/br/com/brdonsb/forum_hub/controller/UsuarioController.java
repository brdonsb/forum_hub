package br.com.brdonsb.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brdonsb.forum_hub.dto.DadosCadastroUsuario;
import br.com.brdonsb.forum_hub.dto.DadosDetalhamentoUsuario;
import br.com.brdonsb.forum_hub.model.Usuario;
import br.com.brdonsb.forum_hub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }
}




