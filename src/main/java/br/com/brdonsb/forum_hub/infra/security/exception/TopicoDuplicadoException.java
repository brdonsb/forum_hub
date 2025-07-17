package br.com.brdonsb.forum_hub.infra.security.exception;

public class TopicoDuplicadoException extends RuntimeException {
    public TopicoDuplicadoException() {
        super("tópico e mensagem já cadastrados");
    }
}
