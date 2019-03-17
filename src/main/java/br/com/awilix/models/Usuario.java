package br.com.awilix.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = -4999462981232910812L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	
	@NotEmpty(message="O campo email é obrigatório")
	@Column(nullable=false, length=140)
	@Email
	@Size(max=140)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty(message="O campo senha é obrigatório")
	@Column(nullable=false, length=255)
	@Size(min=8, max=90, message="Senha de ter entre 8 e 90 caracteres")
	private String senha;
	
	@NotEmpty(message="O campo nome é obrigatório")
	@Column(nullable=false, length=160)
	@Size(min=3, max=160, message="Senha de ter entre 3 e 160 caracteres")
	private String nome;
	
	@Column(nullable=true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date ultimoLogin;
	
	@Column(nullable=true)
	private String token;
	
	@Column(nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date dataCadastro;
}