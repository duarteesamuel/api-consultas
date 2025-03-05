package com.duartetech.api_consultas.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AppoimentStatus {

	SCHEDULED("Agendada"),
	CANCELED("Cancelada");
	
	private final String descricao;
}
