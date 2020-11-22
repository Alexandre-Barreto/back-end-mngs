package br.com.mngs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mngs.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{	
	@Query("SELECT p FROM Pais p WHERE UPPER(p.nome) = :nomePais")
	public Optional<Pais> findByNome(String nomePais);
}
