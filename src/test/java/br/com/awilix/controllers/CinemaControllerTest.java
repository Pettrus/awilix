package br.com.awilix.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.dto.HorariosDTO;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.services.CinemaService;

@RunWith(MockitoJUnitRunner.class)
public class CinemaControllerTest extends SetupTest {

	@Mock
	private CinemaService service;
	
	@InjectMocks
	private CinemaController controller;
	
	@Test
	void filmesEmCartazTest() {
		Set<FilmeEmCartaz> filmes = new HashSet<FilmeEmCartaz>();
		filmes.add(mock(FilmeEmCartaz.class));
		
		when(service.listarFilmes()).thenReturn(filmes);
		
		assertThat(controller.filmesEmCartaz()).isNotEmpty();
	}
	
	@Test
	void horariosFilmeTest() {
		when(service.horariosFilme(Mockito.anyInt())).thenReturn(mock(HorariosDTO.class));
		
		assertThat(controller.horariosFilme(Mockito.anyInt())).isNotNull();
	}
	
	@Test
	void tmdbIdTest() {
		when(service.retornaIdTmdb(Mockito.anyString())).thenReturn(1);
		
		assertThat(controller.tmdbId(Mockito.anyString())).isNotNull();
	}
}