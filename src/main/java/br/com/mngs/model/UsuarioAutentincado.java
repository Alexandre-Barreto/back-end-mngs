package br.com.mngs.model;

import lombok.Data;

@Data
public class UsuarioAutentincado {
	
	private String login;
	private String nome;
	private String token;
	private Boolean administrador;
}
