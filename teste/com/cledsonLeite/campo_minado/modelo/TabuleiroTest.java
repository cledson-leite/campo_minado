package com.cledsonLeite.campo_minado.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabuleiroTest {
	Tabuleiro tabuleiro;
	
	@BeforeEach
	void makeSut() {
		tabuleiro = new Tabuleiro(6, 6, 6);
	}

	
	@Test
	void DeveExibirUmTabuleiroComSeisLinhaESeisColunas() {
		StringBuilder string = new StringBuilder();
		string.append(" ?  ?  ?  ?  ?  ? \n");
		string.append(" ?  ?  ?  ?  ?  ? \n");
		string.append(" ?  ?  ?  ?  ?  ? \n");
		string.append(" ?  ?  ?  ?  ?  ? \n");
		string.append(" ?  ?  ?  ?  ?  ? \n");
		string.append(" ?  ?  ?  ?  ?  ? \n");
		
		
		assertEquals(string.toString(), tabuleiro.toString());
	}

}
