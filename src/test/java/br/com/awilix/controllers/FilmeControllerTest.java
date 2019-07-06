package br.com.awilix.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.services.FilmeService;

@RunWith(MockitoJUnitRunner.class)
public class FilmeControllerTest extends SetupTest {

	@Mock
	private FilmeService service;
	
	@InjectMocks
	private FilmeController controller;
	
	@Test
	void filmesEmCartazTest() {
		Set<FilmeEmCartaz> filmes = new HashSet<FilmeEmCartaz>(List.of(mock(FilmeEmCartaz.class)));
		when(service.listarFilmes()).thenReturn(filmes);
		
		assertThat(controller.filmesEmCartaz()).isNotEmpty();
	}
}