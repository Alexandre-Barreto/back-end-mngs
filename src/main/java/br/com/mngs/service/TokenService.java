package br.com.mngs.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.mngs.model.Token;
import br.com.mngs.repository.TokenRepository;

@Service
public class TokenService {
	@Autowired
    private TokenRepository repository;

    public String criarToken(String login, boolean administrador) {
        Token token = new Token();
        String idToken = UUID.randomUUID().toString();
        token.setToken(idToken);
        token.setExpiracao(LocalDateTime.now().plusMinutes(5));
        token.setLogin(login);
        token.setAdministrador(administrador);
        return saveToken(token);
    }
    
    private String saveToken(Token token){
    	return repository.save(token).getToken();
    }
    
    public ResponseEntity<Boolean> renewToken(String token){
    	Optional<Token> resp = repository.findByToken(token);
    	if(resp.isPresent()){
    		criarToken(resp.get().getLogin(), resp.get().getAdministrador());
			return ResponseEntity.ok(true);
    	}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    public boolean isAutorizado(Optional<Token> token){
		if(token.get().getExpiracao().isAfter(LocalDateTime.now())){
			return true;			
		}
		return false;
    }
}
