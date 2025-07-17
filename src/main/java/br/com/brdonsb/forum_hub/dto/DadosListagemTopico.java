package br.com.brdonsb.forum_hub.dto;

import java.time.LocalDate;

import br.com.brdonsb.forum_hub.model.Curso;
import br.com.brdonsb.forum_hub.model.Status;
import br.com.brdonsb.forum_hub.model.Topico;

public record DadosListagemTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDate dataCriacao,
    Status status,
    String autor,
    Curso curso
    ) {

    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso());
    }        
}