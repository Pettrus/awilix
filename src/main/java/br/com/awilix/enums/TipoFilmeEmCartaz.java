package br.com.awilix.enums;

public enum TipoFilmeEmCartaz {
	LEGENDADO("Legendado"),
	DUBLADO("Dublado");
	
	private String descricao;
	
	TipoFilmeEmCartaz(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}