package br.com.brdonsb.forum_hub.dto;

import br.com.brdonsb.forum_hub.model.Perfil;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String senha,
    @Enumerated(EnumType.STRING)
    Perfil perfil) {
}
