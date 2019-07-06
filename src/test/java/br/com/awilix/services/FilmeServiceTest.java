package br.com.awilix.services;

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
import br.com.awilix.repository.FilmeRepository;

@RunWith(MockitoJUnitRunner.class)
public class FilmeServiceTest extends SetupTest {

	@Mock
	public FilmeRepository filmeRepository;
	
	@InjectMocks
	private FilmeService service;
	
	@Test
	void listarFilmesTest() {
		Set<FilmeEmCartaz> lista = new HashSet<>(List.of(mock(FilmeEmCartaz.class), mock(FilmeEmCartaz.class)));
		when(filmeRepository.findAll()).thenReturn(lista);
		
		assertThat(service.listarFilmes()).isNotEmpty();
	}
}