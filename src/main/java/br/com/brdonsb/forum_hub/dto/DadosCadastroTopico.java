package br.com.brdonsb.forum_hub.dto;

import java.time.LocalDateTime;

import br.com.brdonsb.forum_hub.model.Curso;
import br.com.brdonsb.forum_hub.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
    @NotBlank
    String titulo,
    @NotBlank
    String mensagem,
    @NotNull
    LocalDateTime dataCriacao,
    @Enumerated(EnumType.STRING)
    Status status,
    @Valid
    DadosCadastroUsuario autor,
    @Enumerated(EnumType.STRING)
    Curso curso) {
}