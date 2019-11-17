package br.com.alura.forum.config.validacao;

public class ErroDeFormularioDto {
	private String nome;
	private String mensagem;
	
	public ErroDeFormularioDto(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}
	
	public String getNome() {
		return nome;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	
}
