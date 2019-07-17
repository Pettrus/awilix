package br.com.awilix.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.Feed;
import br.com.awilix.repository.FeedRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest extends SetupTest {

	@Mock
	private FeedRepository repository;
	
	@InjectMocks
	private FeedService service;
	
	@Test
	void deveriaSalvar() {
		Feed feed = mock(Feed.class);
		
		when(repository.findByEmail(feed.getEmail())).thenReturn(Optional.of(feed));
		assertThrows(GeneralException.class, () -> service.salvar(feed));
		
		when(repository.findByEmail(feed.getEmail())).thenReturn(Optional.empty());
		service.salvar(feed);
		verify(repository).save(Mockito.any());
	}
}