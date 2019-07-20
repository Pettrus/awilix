package br.com.awilix.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.awilix.enums.TipoFilmeEmCartaz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="horarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	@JoinColumn(name = "filme_cinema_id", nullable = false)
	private FilmeCinema filmeCinema;
}