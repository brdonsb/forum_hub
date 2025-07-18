package br.com.brdonsb.forum_hub.dto;

import br.com.brdonsb.forum_hub.model.Perfil;
import br.com.brdonsb.forum_hub.model.Usuario;

public record DadosDetalhamentoUsuario(
    Long id,
    String nome,
    String email,
    String senha,
    Perfil perfil) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
    }
}
