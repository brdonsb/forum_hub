package br.com.brdonsb.forum_hub.dto;

import br.com.brdonsb.forum_hub.model.Curso;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
    @NotBlank
    String titulo,
    @NotBlank
    String mensagem,
    @Enumerated(EnumType.STRING)
    Curso curso) {
}