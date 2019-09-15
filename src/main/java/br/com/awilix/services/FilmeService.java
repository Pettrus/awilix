package br.com.awilix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Filme;
import br.com.awilix.models.FilmeCinema;
import br.com.awilix.repository.FilmeCinemaRepository;
import br.com.awilix.repository.FilmeRepository;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmeService {
	
	private final FilmeRepository filmeRepository;
	
	private final TmdbService tmdbService;
	
	private final FilmeCinemaRepository fcRepository;
	
	public List<Filme> listarFilmes(Linguagem linguagem) {
		return filmeRepository.findByDetalhesLinguagem(linguagem);
	}
	
	public List<String> salvar(List<Filme> filmes, Linguagem linguagem) {
		List<String> semId = new ArrayList<String>();
		
		for (Filme filme : filmes) {
			Integer id = tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem);
			
			if(id != null) {
				MovieDb movie = tmdbService.carregarCategoriaPorFilmeId(id, "pt-BR", MovieMethod.videos);
				filme.preencherComTmdb(movie, linguagem);
				
				filmeRepository.save(filme);
			}else {
				semId.add(filme.getImdbId());
			}
		}
		
		return semId;
	}
	
	public void salvarFilmeCinema(List<FilmeCinema> filmeCinema, List<String> semId) {
		List<FilmeCinema> remover = new ArrayList<FilmeCinema>();
		
		filmeCinema.forEach(fc -> {
			if(semId.contains(fc.getFilme().getImdbId())) {
				remover.add(fc);
			}else {
				fc.getHorarios().forEach(h -> {
					h.setFilmeCinema(fc);
				});
			}
		});
		
		filmeCinema.removeAll(remover);
		
		fcRepository.saveAll(filmeCinema);
	}
}