package br.com.awilix.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
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
	
	@Column(nullable=true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date dataConfirmacao;

	@Builder.Default
	@Column(nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Boolean emailConfirmado = false;
	
	@Column(nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String tokenConfirmacao;
}