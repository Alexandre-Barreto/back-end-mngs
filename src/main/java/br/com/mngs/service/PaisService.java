package br.com.mngs.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mngs.model.Pais;
import br.com.mngs.model.Token;
import br.com.mngs.repository.PaisRepository;
import br.com.mngs.repository.TokenRepository;

@Service
public class PaisService {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private PaisRepository paisRepository;

	public ResponseEntity<List<Pais>> listPaises(String token) {
		Optional<Token> tokenResp = tokenRepository.findByToken(token);
		if (tokenResp.isPresent() && tokenService.isAutorizado(tokenResp)) {
			return ResponseEntity.ok(paisRepository.findAll());
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	public ResponseEntity<Pais> salvarPais(@Valid Pais pais, String token) {
		Optional<Token> tokenResp = tokenRepository.findByToken(token);
		if (tokenResp.isPresent() && tokenResp.get().getAdministrador() 
				&& tokenService.isAutorizado(tokenResp)) {			
			if (pais.getId() == 0) {
				paisRepository.save(pais);
				return ResponseEntity.ok(pais);
			} else {
				Optional<Pais> paisById = paisRepository.findById(pais.getId());
				if (paisById.isPresent()) {
					paisRepository.deleteById(paisById.get().getId());
					return ResponseEntity.ok(paisRepository.save(pais));
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Pais> pesquisarPais(String token, String nome) {
		Optional<Token> tokenResp = tokenRepository.findByToken(token);
		if (tokenResp.isPresent() && tokenService.isAutorizado(tokenResp)) {
			Optional<Pais> paisResp = paisRepository.findByNome(nome.toUpperCase());
			if (paisResp.isPresent()) {
				return ResponseEntity.ok(paisResp.get());
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Boolean> excluirPais(String token, Long id) {
		Optional<Token> tokenResp = tokenRepository.findByToken(token);
		if (tokenResp.isPresent() && tokenService.isAutorizado(tokenResp)) {
			if(paisRepository.findById(id).isPresent()) {
				paisRepository.deleteById(id);
				return ResponseEntity.ok(true);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

}
