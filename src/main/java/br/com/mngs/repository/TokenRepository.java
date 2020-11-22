package br.com.mngs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mngs.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{
	@Query("SELECT t FROM Token t WHERE t.token = :token")
	public Optional<Token> findByToken(String token);
}
