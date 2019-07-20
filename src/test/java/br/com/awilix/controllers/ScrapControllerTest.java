package br.com.awilix.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.awilix.config.SetupTest;
import br.com.awilix.dto.ScrapDTO;
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
		
		assertEquals(controller.verificarNovoESalvar(scrap), new ResponseEntity<>(HttpStatus.OK));
	}
}
