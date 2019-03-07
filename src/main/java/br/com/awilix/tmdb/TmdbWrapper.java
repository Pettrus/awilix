package br.com.awilix.tmdb;

import info.movito.themoviedbapi.TmdbApi;

public class TmdbWrapper {
	
	private static TmdbApi instancia = null;
	
	private TmdbWrapper() {};
	
	public static TmdbApi getInstance() {
		if(instancia == null) {
			synchronized (TmdbWrapper.class) {
				if(instancia == null) {
					instancia = new TmdbApi("220572e789e2a2ef208a54f35ab85e26");
				}
			}
		}
		
		return instancia;
	}
}