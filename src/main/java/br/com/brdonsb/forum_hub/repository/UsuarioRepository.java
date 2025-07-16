package br.com.brdonsb.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brdonsb.forum_hub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
