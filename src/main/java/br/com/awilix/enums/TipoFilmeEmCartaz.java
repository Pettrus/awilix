package br.com.awilix.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoFilmeEmCartaz {
	@JsonProperty("LEG")
	LEGENDADO,
	@JsonProperty("DUB")
	DUBLADO,
	@JsonProperty("LEG VIP")
	LEGENDADO_VIP,
	@JsonProperty("3D LEG")
	LEGENDADO_3D,
	@JsonProperty("3D LEG IMAX")
	LEGENDADO_3D_IMAX,
	@JsonProperty("3D LEG VIP")
	LEGENDADO_3D_VIP,
	@JsonProperty("3D LEG 4DX")
	LEGENDADO_3D_4DX,
	@JsonProperty("3D DUB")
	DUBLADO_3D,
	@JsonProperty("3D DUB IMAX")
	DUBLADO_3D_IMAX,
	@JsonProperty("3D DUB VIP")
	DUBLADO_3D_VIP,
	@JsonProperty("3D DUB 4DX")
	DUBLADO_3D_4DX,
	@JsonProperty("DUB VIP")
	DUBLADO_VIP,
	@JsonProperty("DUB 4DX")
	DUBLADO_4DX,
	@JsonProperty("LEG 4DX")
	LEGENDADO_4DX,
	@JsonProperty("DUB IMAX")
	DUBLADO_IMAX,
	@JsonProperty("LEG IMAX")
	LEGENDADO_IMAX;
}