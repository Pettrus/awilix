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
import br.com.awilix.models.Cinema;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.CinemaService;
import br.com.awilix.repository.DetalhesRepository;
import br.com.awilix.repository.FilmeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ScrapServiceTest extends SetupTest {
	
	@Mock
	private FilmeRepository filmeRepository;
	
	@Mock
	private FilmeService filmeService;
	
	@Mock
	private CinemaService cinemaService;
	
	@Mock
	private DetalhesRepository detalhesRepository;

	@InjectMocks
	private ScrapService service;
	
	@Test
	void deveriaVerificarSalvarFilme() {
		List<FilmeEmCartaz> filmes = List.of(mock(FilmeEmCartaz.class));
		List<String> filmeIds = List.of("1");
		List<Cinema> cinemas = List.of(mock(Cinema.class));
		Linguagem linguagem = Linguagem.PORTUGUES_BRASIL;
		String cidade = "Fortaleza";
		
		when(filmeRepository.findByImdbIdInAndDetalhesLinguagem(filmeIds, linguagem.ordinal()))
			.thenReturn(List.of("2"));
		
		assertThat(service.verificarSalvarFilme(filmes, cinemas, linguagem, cidade))
			.isEqualTo(filmes);
	}
}