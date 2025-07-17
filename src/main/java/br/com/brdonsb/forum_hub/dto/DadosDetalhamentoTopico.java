package br.com.brdonsb.forum_hub.dto;

import java.time.LocalDate;
import br.com.brdonsb.forum_hub.model.Topico;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDate dataCriacao
    ) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }        
}