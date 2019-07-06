package br.com.awilix.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.awilix.enums.TipoFilmeEmCartaz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="horarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private TipoFilmeEmCartaz tipoFilme;
	
	@Column(nullable = false)
	private String inicio;
	
	@ManyToOne
	@JoinColumn(name = "cinema_id", nullable = false)
	@JsonIgnoreProperties("horarios")
	private Cinema cinema;
	
	@ManyToOne
	@JoinColumn(name = "filme_id", nullable = false)
	@JsonIgnoreProperties("horarios")
	private FilmeEmCartaz filme;
}