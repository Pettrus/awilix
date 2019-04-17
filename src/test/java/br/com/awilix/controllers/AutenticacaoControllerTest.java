package br.com.awilix.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import br.com.awilix.config.SetupTest;
import br.com.awilix.models.Usuario;
import br.com.awilix.services.AutenticacaoService;

@RunWith(MockitoJUnitRunner.class)
public class AutenticacaoControllerTest extends SetupTest {

	@Mock
	private AutenticacaoService service;
	
	@InjectMocks
	private AutenticacaoController controller;
	
	@Test
	void logarTest() {
		when(service.logar(mock(Usuario.class))).thenReturn(mock(Usuario.class));
		
		ResponseEntity<?> response = controller.logar(mock(Usuario.class));
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	void verificarConfirmacaoTokenTest() {
		when(service.isTokenConfirmacaoValido(Mockito.anyString())).thenReturn(false);
		
		assertFalse(controller.verificarConfirmacaoToken(Mockito.anyString()));
	}
	
	@Test
	void cadastroTest() throws IOException {
		controller.cadastro(mock(Usuario.class));
		verify(service).cadastrar(Mockito.any());
	}
}