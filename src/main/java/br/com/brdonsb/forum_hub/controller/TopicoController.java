package br.com.brdonsb.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brdonsb.forum_hub.dto.DadosAtualizacaoTopico;
import br.com.brdonsb.forum_hub.dto.DadosCadastroTopico;
import br.com.brdonsb.forum_hub.dto.DadosDetalhamentoTopico;
import br.com.brdonsb.forum_hub.dto.DadosListagemTopico;
import br.com.brdonsb.forum_hub.dto.MensagemErroDTO;
import br.com.brdonsb.forum_hub.infra.security.exception.TopicoDuplicadoException;
import br.com.brdonsb.forum_hub.infra.security.exception.TopicoNullException;
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
        Topico topicoDuplicado = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topicoDuplicado != null) {
           throw new TopicoDuplicadoException();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario autor = (Usuario) authentication.getPrincipal();
        var topico = new Topico(dados, autor);
        repository.save(topico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var topico = repository.findById(id);
        if (topico.isEmpty()) {
           throw new TopicoNullException();
        } 
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}