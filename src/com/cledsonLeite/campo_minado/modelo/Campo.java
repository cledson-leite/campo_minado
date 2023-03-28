package com.cledsonLeite.campo_minado.modelo;

import java.util.ArrayList;
import java.util.List;

import com.cledsonLeite.campo_minado.excecao.ExplocaoExceprion;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList();
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean diagonal = 
				(linha != vizinho.linha)
				&& (coluna != vizinho.coluna);
		
		int delta = 
				(Math.abs(linha - vizinho.linha))
				+ (Math.abs(coluna - vizinho.coluna));
		if(delta == 1) {
			vizinhos.add(vizinho);
			return true;
		}
		if(delta == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		return false;
	}
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public void setAberto() {
		aberto = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public void setMinado() {
		minado = true;
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
	}
	
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			if(minado) {
				throw new ExplocaoExceprion();
			}
			if(vizinhancaSegura()) {
				vizinhos.forEach(vizinho -> vizinho.abrir());
			}
			
			return true;
		}
		
		return false;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(vizinho -> vizinho.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		marcado = false;
		minado = false;
	}
	
	public String toString() {
		if(marcado) return "x";
		if(aberto && minado) return "*";
		if(aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		}
		if(aberto) return " ";
		return "?";
	}

}
