package br.com.awilix.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import br.com.awilix.config.SetupTest;
import br.com.awilix.models.Feed;
import br.com.awilix.services.FeedService;

@RunWith(MockitoJUnitRunner.class)
public class FeedControllerTest extends SetupTest {

	@Mock
	private FeedService service;
	
	@InjectMocks
	private FeedController controller;
	
	@Test
	void deveriaSalvarERetornarOk() {
		Feed feed = mock(Feed.class);
		
		assertEquals(controller.salvar(feed), new ResponseEntity<>(HttpStatus.OK));
	}
}
