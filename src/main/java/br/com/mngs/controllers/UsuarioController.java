package br.com.mngs.controllers;

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
import br.com.mngs.model.UserLogin;
import br.com.mngs.model.UsuarioAutentincado;
import br.com.mngs.service.TokenService;
import br.com.mngs.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/autenticar")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<UsuarioAutentincado> autenticar(@Valid @RequestBody UserLogin userLogin) {
		return usuarioService.autentificar(userLogin);
	}
	
	@GetMapping("/renovar-ticket")
	public ResponseEntity<Boolean> buscar(String token) {
		return this.tokenService.renewToken(token);
	}

}
