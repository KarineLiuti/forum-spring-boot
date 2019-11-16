package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
    @GetMapping
    public List<TopicosDto> lista(String nomeDoCurso) {
    	System.out.println(nomeDoCurso);
    	if(nomeDoCurso == null) {
    		List<Topico> topicos = topicoRepository.findAll();    		
    		return TopicosDto.converter(topicos);
    	} else {
    		List<Topico> topicos = topicoRepository.findByCursoNome(nomeDoCurso);  
    		return TopicosDto.converter(topicos);
    	}
	}
    
    @PostMapping
    public ResponseEntity<TopicosDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
    	Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    	
    	return ResponseEntity.created(uri).body(new TopicosDto(topico));
    	
    }
}
