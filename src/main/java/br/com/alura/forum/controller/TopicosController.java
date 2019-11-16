package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicosDto> lista() {
        List<Topico> topicos = topicoRepository.findAll();

		return TopicosDto.converter(topicos);
	}
}
