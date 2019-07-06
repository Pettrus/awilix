package br.com.awilix.dto;

import java.util.List;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Cinema;
import br.com.awilix.models.FilmeEmCartaz;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapDTO {

	private List<FilmeEmCartaz> filmes;
	
	private List<Cinema> cinemas;
	
	private Linguagem linguagem;
	
	private String cidade;
}
