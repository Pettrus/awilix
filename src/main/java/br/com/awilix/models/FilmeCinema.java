package br.com.awilix.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "filme_cinema")
@Data
@NoArgsConstructor
public class FilmeCinema {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "filme_id")
	@JsonIgnoreProperties("filmeCinemas")
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	@JsonIgnoreProperties("filmeCinemas")
	private Cinema cinema;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "filmeCinema", orphanRemoval = true)
	@JsonIgnoreProperties("filmeCinema")
	private List<Horarios> horarios;
}