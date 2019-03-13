package br.com.awilix.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="usuarios")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = -4999462981232910812L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull(message="O campo email é obrigatório")
	@Column(nullable=false, length=140)
	private String email;
	
	@NotNull(message="O campo senha é obrigatório")
	@Column(nullable=false, length=255)
	private String senha;
	
	@NotNull(message="O campo nome é obrigatório")
	@Column(nullable=false, length=160)
	private String nome;
	
	@Column(nullable=false)
	private Date ultimoLogin;
	
	@Column(nullable=true)
	private String token;
}