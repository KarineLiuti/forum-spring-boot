package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicosDto;
import br.com.alura.forum.controller.form.AtualizarTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
    @Transactional
    public ResponseEntity<TopicosDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
    	Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    	
    	return ResponseEntity.created(uri).body(new TopicosDto(topico));
    	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
    	Optional<Topico> topico = topicoRepository.findById(id);
    	
    	if(topico.isPresent()) {
    		return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));     		
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicosDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoForm form) {
    	Optional<Topico> optionalTopic = topicoRepository.findById(id);
    	
    	if(optionalTopic.isPresent()) {
    		form.atualizar(id, topicoRepository);
    		
    		Topico topico = form.atualizar(id, topicoRepository);
    		return ResponseEntity.ok(new TopicosDto(topico));    		
    	}
    	
    	return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
    	Optional<Topico> topico = topicoRepository.findById(id);
    	
    	if(topico.isPresent()) {
    		topicoRepository.deleteById(id);
    		
    		return ResponseEntity.ok().build();    		
    	}
    	return ResponseEntity.notFound().build();
    }
}
