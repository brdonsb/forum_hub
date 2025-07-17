package br.com.brdonsb.forum_hub.infra.security.exception;

public class TopicoNullException extends RuntimeException{
    public TopicoNullException(){
        super("tópico inválido");
    }
}