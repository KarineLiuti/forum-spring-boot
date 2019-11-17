package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizarTopicoForm {
	
	private String titulo;
	private String mensagem;
	
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		
		topico.setMensagem(this.mensagem);
		topico.setTitulo(this.titulo);
		return topico;
	}	

}
