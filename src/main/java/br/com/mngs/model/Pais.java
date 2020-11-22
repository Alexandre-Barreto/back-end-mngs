package br.com.mngs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;

@Entity
@Table(schema="mngs")
@Data
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Column
	private String nome;
	
	@NotBlank
	@Size(max = 60)
	@Column
	private String sigla;
	
	@NotBlank
	@Size(max = 60)
	@Column
	private String gentilico;

}
