package br.com.awilix.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.awilix.config.SetupTest;
import br.com.awilix.services.FilmeService;
import info.movito.themoviedbapi.model.MovieDb;

@RunWith(MockitoJUnitRunner.class)
public class FilmeControllerTest extends SetupTest {

	@Mock
	private FilmeService service;
	
	@InjectMocks
	private FilmeController controller;
	
	@Test
	void pesquisaTest() throws UnirestException {
		when(service.pesquisar(Mockito.anyString())).thenReturn("[]");
		assertThat(controller.pesquisa(Mockito.anyString())).isEqualTo("[]");
	}
	
	@Test
	void listarFilmesTest() throws UnirestException {
		when(service.consultarFilmes(Mockito.anyInt())).thenReturn("[]");
		assertThat(controller.listarFilmes(Mockito.anyInt())).isEqualTo("[]");
	}
	
	@Test
	void listarFilmesPorGeneroTest() throws UnirestException {
		when(service.consultarFilmesPorGenero(Mockito.anyInt(), Mockito.anyString())).thenReturn("[]");
		assertThat(controller.listarFilmesPorGenero(Mockito.anyInt(), Mockito.anyString())).isEqualTo("[]");
	}
	
	@Test
	void detalhar() {
		when(service.detalhar(Mockito.anyString())).thenReturn(mock(MovieDb.class));
		assertThat(controller.detalhar(Mockito.anyString())).isNotNull();
	}
}
