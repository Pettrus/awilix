package br.com.awilix.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.awilix.models.Horarios;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorariosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private MovieDb detalhes = new MovieDb();
	
	private Set<Horarios> horarios = new HashSet<Horarios>();
}
