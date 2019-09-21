package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Filme;
import br.com.awilix.repository.FilmeRepository;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;

@RunWith(MockitoJUnitRunner.class)
public class FilmeServiceTest extends SetupTest {

	@Mock
	private FilmeRepository filmeRepository;
	
	@Mock
	private TmdbService tmdbService;
	
	@InjectMocks
	private FilmeService service;
	
	@Test
	void listarFilmesTest() {
		when(filmeRepository.findByDetalhesLinguagem(Linguagem.PORTUGUES_BRASIL)).thenReturn(List.of(mock(Filme.class)));
		
		assertThat(service.listarFilmes(Linguagem.PORTUGUES_BRASIL)).isNotEmpty();
	}
	
	@Test
	void deveriaSalvar() {
		Filme filme = mock(Filme.class);
		Linguagem linguagem = Linguagem.PORTUGUES_BRASIL;
		
		when(tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem)).thenReturn(null);
		
		when(tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem)).thenReturn(1);
		when(tmdbService.carregarCategoriaPorFilmeId(1, "pt-BR", MovieMethod.videos)).thenReturn(mock(MovieDb.class));
		
		service.salvar(List.of(filme));
		verify(filmeRepository).saveAll(Mockito.any());
	}
}