package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicosDto {
    Long id;
    String titulo;
    String mensagem;
    LocalDateTime dataCriacao;

    public TopicosDto (Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static List<TopicosDto> converter (List<Topico> topicos) {
        return topicos.stream().map(TopicosDto::new).collect(Collectors.toList());
    }

}
