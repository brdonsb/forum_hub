package br.com.brdonsb.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.brdonsb.forum_hub.dto.DadosCadastroTopico;
import br.com.brdonsb.forum_hub.dto.DadosDetalhamentoTopico;
import br.com.brdonsb.forum_hub.model.Topico;
import br.com.brdonsb.forum_hub.model.Usuario;
import br.com.brdonsb.forum_hub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @PostMapping
    @Transactional

    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario autor = (Usuario) authentication.getPrincipal();
        var topico = new Topico(dados, autor);
        repository.save(topico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }    
}