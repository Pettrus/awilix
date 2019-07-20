package br.com.awilix.dto;

import java.util.List;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Cinema;
import br.com.awilix.models.Filme;
import br.com.awilix.models.FilmeCinema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapDTO {

	private List<Filme> filmes;
	
	private List<Cinema> cinemas;
	
	private Linguagem linguagem;
	
	private String cidade;
	
	private List<FilmeCinema> filmeCinemas;
}
