package br.com.awilix.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="cinemas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cinema {

	@Id
	@EqualsAndHashCode.Include
	@JsonProperty(access = Access.WRITE_ONLY)
	private String id;
	
	@NotNull
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable = false)
	private String cidade;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "cinema")
	@JsonIgnoreProperties("cinema")
	private List<Horarios> horarios;
}