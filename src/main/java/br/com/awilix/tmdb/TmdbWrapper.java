package br.com.awilix.tmdb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import info.movito.themoviedbapi.TmdbApi;

@Component
public class TmdbWrapper {
	
	@Autowired
	private Environment env0;
	
	private static Environment env;
	
	@PostConstruct
	private void initEnv() {
		env = this.env0;
	}
	
	private static TmdbApi instancia = null;
	
	private TmdbWrapper() {};
	
	public static TmdbApi getInstance() {
		if(instancia == null) {
			synchronized (TmdbWrapper.class) {
				if(instancia == null) {
					instancia = new TmdbApi(env.getProperty("API_KEY"));
				}
			}
		}
		
		return instancia;
	}
}