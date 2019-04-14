package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mashape.unirest.http.exceptions.UnirestException;

@RunWith(MockitoJUnitRunner.class)
public class FilmeServiceTest {

	@InjectMocks
	private FilmeService service;
	
	@BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);  
    }
	
	@Test
	void pesquisar() throws UnirestException {
		assertThat(service.pesquisar("fdsfsderwuiy").getBody()).isEqualTo("[]");
		
		assertThat(service.pesquisar("predator")).isNotNull();
	}
	
	@Test
	void consultarFilmes() throws UnirestException {
		assertThat(service.consultarFilmes(1)).isNotNull();
	}
	
	@Test
	void consultarFilmesPorGenero() throws UnirestException {
		assertThat(service.consultarFilmesPorGenero(1, "asdasdas").getBody()).isEqualTo("[]");
		
		assertThat(service.consultarFilmesPorGenero(1, "action")).isNotNull();
	}
	
	@Test
	void detalhar() {
		assertThat(service.detalhar("tt2386490")).isNotNull();
	}
}
