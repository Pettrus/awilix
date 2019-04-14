package br.com.awilix.tmdb;

import org.springframework.stereotype.Component;

import info.movito.themoviedbapi.TmdbApi;

@Component
public class TmdbWrapper {
	
	private static TmdbApi instancia = null;
	
	private TmdbWrapper() {};
	
	public static TmdbApi getInstance() {
		if(instancia == null) {
			synchronized (TmdbWrapper.class) {
				if(instancia == null) {
					instancia = new TmdbApi(System.getenv("API_KEY"));
				}
			}
		}
		
		return instancia;
	}
}