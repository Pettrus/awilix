package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.awilix.config.SetupTest;

@RunWith(MockitoJUnitRunner.class)
public class FilmeServiceTest extends SetupTest {

	@InjectMocks
	private FilmeService service;
	
	@Test
	void pesquisarTest() throws UnirestException {
		assertThat(service.pesquisar("asdasdasdas")).isEqualTo("[]");
		
		assertThat(service.pesquisar("predator")).isNotNull();
	}
	
	@Test
	void consultarFilmesTest() throws UnirestException {
		assertThat(service.consultarFilmes(1)).isNotNull();
	}
	
	@Test
	void consultarFilmesPorGeneroTest() throws UnirestException {
		assertThat(service.consultarFilmesPorGenero(1, "fgfsdfsdfsdfsd")).isEqualTo("[]");
		
		assertThat(service.consultarFilmesPorGenero(1, "action")).isNotNull();
	}
	
	@Test
	void detalharTest() {
		assertThat(service.detalhar("tt2386490")).isNotNull();
	}
}
