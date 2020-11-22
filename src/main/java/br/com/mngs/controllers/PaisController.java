package br.com.mngs.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mngs.model.Pais;
import br.com.mngs.service.PaisService;

@RestController
@RequestMapping("/pais")
public class PaisController {
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Pais>> listPaises(String token){
		return paisService.listPaises(token);			
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Pais> autenticar(@Valid @RequestBody Pais pais, String token) {
		return paisService.salvarPais(pais, token);
	}
	
	@GetMapping("/pesquisar")
	public ResponseEntity<Pais> pesquisarPais(String token, String nome){
		return paisService.pesquisarPais(token, nome);			
	}
	
	@GetMapping("/excluir")
	public ResponseEntity<Boolean> excluirPais(String token, Long id){
		return paisService.excluirPais(token, id);			
	}
}
