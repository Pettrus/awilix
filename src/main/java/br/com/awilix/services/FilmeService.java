package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.FilmeRepository;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmeService {
	
	private final FilmeRepository filmeRepository;
	
	private final TmdbService tmdbService;
	
	public List<FilmeEmCartaz> listarFilmes() {
		return filmeRepository.findByDetalhesLinguagem(Linguagem.PORTUGUES_BRASIL);
	}
	
	public void salvar(List<FilmeEmCartaz> filmes, Linguagem linguagem) {
		for (FilmeEmCartaz filme : filmes) {
			Integer id = tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem);
			
			if(id == null) {
				throw new GeneralException("Filme não encontrado");
			}
			
			MovieDb movie = tmdbService.carregarCategoriaPorFilmeId(id, "pt-BR", MovieMethod.videos);
			filme.preencherComTmdb(movie, linguagem);
			
			filme.getHorarios().forEach(h -> h.setFilme(filme));
			
			filmeRepository.save(filme);
		}	
	}
}