package com.cledsonLeite.campo_minado.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;

	private List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}

	}

	private void associarVizinhos() {
		for (Campo campoUm : campos) {
			for (Campo campoDois : campos) {
				campoUm.adicionarVizinho(campoDois);
			}
		}

	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = campo -> campo.isMinado();

		do {

			minasArmadas = campos.stream().filter(minado).count();

			int aleatorio = (int) (Math.random() * campos.size());

			campos.get(aleatorio).setMinado();

		} while (minasArmadas < minas);

	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campo -> campo.objetivoAlcancado());
	}

	public void reiniciarJogo() {
		campos.stream().forEach(campo -> campo.reiniciar());
		sortearMinas();
	}

	public void abrir(int linha, int coluna) {
		campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna).findFirst()
				.ifPresent(campo -> campo.abrir());
		;
	}

	public void AlterarMarcacao(int linha, int coluna) {
		campos.stream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna).findFirst()
				.ifPresent(campo -> campo.alternarMarcacao());
		;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		int indice = 0;
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				string.append(" ");
				string.append(campos.get(indice));
				string.append(" ");
				indice++;
			}
			string.append("\n");
		}

		return string.toString();
	}

}
