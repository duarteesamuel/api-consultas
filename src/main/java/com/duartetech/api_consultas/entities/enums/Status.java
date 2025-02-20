package com.duartetech.api_consultas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
	ACTIVE("Ativo"),
	INACTIVE("Inativo");

	
	private final String descricao;
	
}
