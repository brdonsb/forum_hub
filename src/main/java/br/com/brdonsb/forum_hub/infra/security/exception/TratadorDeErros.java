package br.com.brdonsb.forum_hub.infra.security.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import br.com.brdonsb.forum_hub.dto.DadosErroValidacao;
import br.com.brdonsb.forum_hub.dto.MensagemErroDTO;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErrovalidacao(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        var resposta = erros.stream().map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MensagemErroDTO> tratarErroEnum(HttpMessageNotReadableException ex) {
        Throwable causa = ex.getCause();
        if (causa instanceof InvalidFormatException ife) {
            Class<?> tipoEsperado = ife.getTargetType();      
            if (tipoEsperado.isEnum()) {
                String campo = "campo desconhecido";
                List<Reference> caminho = ife.getPath();
                if (!caminho.isEmpty()) {
                    campo = caminho.get(0).getFieldName();
                }
                String valoresAceitos = Arrays.stream(tipoEsperado.getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
                String mensagem = "Valor inválido para o campo '" + campo +
                    "'. Valores aceitos: [" + valoresAceitos + "]";
                return ResponseEntity.badRequest().body(new MensagemErroDTO(mensagem));
            }
        }
        return ResponseEntity.badRequest().body(new MensagemErroDTO("Erro ao processar requisição."));
    }

    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<MensagemErroDTO> tratarTopicoDuplicado(TopicoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new MensagemErroDTO(ex.getMessage()));
    }
    @ExceptionHandler(TopicoNullException.class)
    public ResponseEntity<MensagemErroDTO> tratarTopicoNull(TopicoNullException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new MensagemErroDTO(ex.getMessage()));
    }
}