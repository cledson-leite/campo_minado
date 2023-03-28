package com.cledsonLeite.campo_minado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import com.cledsonLeite.campo_minado.excecao.ExplocaoExceprion;
import com.cledsonLeite.campo_minado.excecao.SairException;
import com.cledsonLeite.campo_minado.modelo.Tabuleiro;

public class Terminal {

	final Tabuleiro tabuleiro;
	final Scanner entrada = new Scanner(System.in);

	public Terminal(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			while (continuar) {
				cicloJogo();
				System.out.print("Outra Partida? (S/n): ");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciarJogo();
				}
			}
			throw new SairException();

		} catch (SairException erro) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}

	} 

	private void cicloJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Digite numero de (linha, coluna): ");
				Iterator<Integer> lc = Arrays
						.stream(digitado.split(","))
						.map(item -> Integer.parseInt(item.trim()))
						.iterator();
				digitado = capturarValorDigitado("1-abrir;\n2-(Des)Marcar:\n");
				if("1".equals(digitado)) tabuleiro.abrir(lc.next(), lc.next());
				if("2".equals(digitado)) tabuleiro.AlterarMarcacao(lc.next(), lc.next());
			}
			
			System.out.println("Vovê Ganhou !! :) ");
			
		} catch (ExplocaoExceprion erro) {
			System.out.println(tabuleiro);
			System.out.println("Vovê Perdeu !! :( ");
		}
		
	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}

}
