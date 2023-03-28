package com.cledsonLeite.campo_minado;

import com.cledsonLeite.campo_minado.modelo.Tabuleiro;
import com.cledsonLeite.campo_minado.visao.Terminal;

public class Aplicacao {
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(10, 5);
		new Terminal(tabuleiro);
	}
}
