package br.com.mngs.model;

import java.time.LocalDateTime;
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
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	@Column
	private String token;
	
	@NotBlank
	@Size(max = 60)
	@Column
	private String login;
	
	@Column
	private LocalDateTime expiracao;
	
	@Column
	private Boolean administrador;
	
}
