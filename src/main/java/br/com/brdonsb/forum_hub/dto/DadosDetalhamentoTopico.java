package br.com.brdonsb.forum_hub.dto;

import java.time.LocalDateTime;

import br.com.brdonsb.forum_hub.model.Curso;
import br.com.brdonsb.forum_hub.model.Status;
import br.com.brdonsb.forum_hub.model.Topico;
import br.com.brdonsb.forum_hub.model.Usuario;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime dataCriacao,
    Status status,
    Usuario autor,
    Curso curso) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }        
}




