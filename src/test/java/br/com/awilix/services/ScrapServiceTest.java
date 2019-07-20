package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Filme;
import br.com.awilix.repository.DetalhesRepository;
import br.com.awilix.repository.FilmeCinemaRepository;
import br.com.awilix.repository.FilmeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ScrapServiceTest extends SetupTest {
	
	@Mock
	private FilmeRepository filmeRepository;
	
	@Mock
	private DetalhesRepository detalhesRepository;
	
	@Mock
	private FilmeCinemaRepository fcRepository;
	
	@InjectMocks
	private ScrapService service;
	
	@Test
	void deveriaRetornarFilmesNovos() {
		List<Filme> filmes = List.of(mock(Filme.class), mock(Filme.class));
		Linguagem linguagem = Linguagem.PORTUGUES_BRASIL;
		List<String> ids = List.of("1", "2");
		
		when(filmeRepository.findByImdbIdInAndDetalhesLinguagem(ids, linguagem.ordinal()))
		.thenReturn(List.of("2"));
		
		assertThat(service.encontrarFilmesNovos(filmes, linguagem)).isNotEmpty();
	}
}