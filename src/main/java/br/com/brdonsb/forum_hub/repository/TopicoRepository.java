package br.com.brdonsb.forum_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brdonsb.forum_hub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

    Topico findByTituloAndMensagem(String titulo, String mensagem);

}
