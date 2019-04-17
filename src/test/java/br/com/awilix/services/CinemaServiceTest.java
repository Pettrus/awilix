package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.config.SetupTest;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.FilmeRepository;
import br.com.awilix.repository.HorariosRepository;

@RunWith(MockitoJUnitRunner.class)
public class CinemaServiceTest extends SetupTest {
	
	@Mock
	public HorariosRepository horarioRepository;
	
	@Mock
	public FilmeRepository filmeRepository;
	
	@InjectMocks
	private CinemaService service;
	
	@Test
	void listarFilmesTest() {
		Set<FilmeEmCartaz> lista = new HashSet<FilmeEmCartaz>();
		lista.add(mock(FilmeEmCartaz.class));
		lista.add(mock(FilmeEmCartaz.class));
		
		when(service.listarFilmes()).thenReturn(lista);
		
		assertThat(service.listarFilmes()).isNotEmpty();
	}
	
	@Test
	void horariosFilmeTest() {
		assertNotNull(service.horariosFilme(897));
	}
	
	@Test
	void retornaIdTmdbTest() {
		assertNotNull(service.retornaIdTmdb("tt2386490"));
	}
}
