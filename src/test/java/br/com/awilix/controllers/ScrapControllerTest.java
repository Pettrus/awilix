package br.com.awilix.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.awilix.config.SetupTest;
import br.com.awilix.dto.ScrapDTO;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.services.EmailService;
import br.com.awilix.services.ScrapService;

@RunWith(MockitoJUnitRunner.class)
public class ScrapControllerTest extends SetupTest {

	@Mock
	private ScrapService service;
	
	@Mock
	private EmailService emailService;
	
	@InjectMocks
	private ScrapController controller;
	
	@Test
	void deveriaVerificarSalvarNovo() {
		ScrapDTO scrap = mock(ScrapDTO.class);
		List<FilmeEmCartaz> filmes = List.of(mock(FilmeEmCartaz.class), mock(FilmeEmCartaz.class));
		
		when(service.verificarSalvarFilme(scrap.getFilmes(), scrap.getCinemas(), scrap.getLinguagem(), scrap.getCidade()))
			.thenReturn(filmes);
		
		assertEquals(controller.verificarNovoESalvar(scrap), new ResponseEntity<>(HttpStatus.OK));
	}
}
