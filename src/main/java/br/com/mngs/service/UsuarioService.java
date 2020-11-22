package br.com.mngs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mngs.model.UserLogin;
import br.com.mngs.model.Usuario;
import br.com.mngs.model.UsuarioAutentincado;
import br.com.mngs.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TokenService service;
	
	public ResponseEntity<UsuarioAutentincado> autentificar(UserLogin userLogin) {
		Optional<Usuario> usuario = repository.authentification(userLogin.getLogin(), userLogin.getSenha());
		if (usuario.isPresent()) {
			UsuarioAutentincado usuarioAutenticado = new UsuarioAutentincado();
			usuarioAutenticado.setLogin(usuario.get().getLogin());
			usuarioAutenticado.setNome(usuario.get().getNome());
			usuarioAutenticado.setAdministrador(usuario.get().getAdministrador());
			usuarioAutenticado.setToken(service.criarToken(usuario.get().getLogin(), usuario.get().getAdministrador()));
			return ResponseEntity.ok(usuarioAutenticado);
		}
		return ResponseEntity.notFound().build();
		
	}
}
