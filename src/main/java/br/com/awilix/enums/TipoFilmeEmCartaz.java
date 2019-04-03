package br.com.awilix.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoFilmeEmCartaz {
	@JsonProperty("Legendado")
	LEGENDADO,
	@JsonProperty("Dublado")
	DUBLADO;
}