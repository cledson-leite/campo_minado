package com.cledsonLeite.campo_minado.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cledsonLeite.campo_minado.excecao.ExplocaoExceprion;

class CampoTest {

	Campo campo;

	@BeforeEach
	void makeSut() {
		campo = new Campo(3, 3);
	}

	@Test
	void nãoDeveAdicionarVizinhoNaMesmaPosicao() {
		Campo vizinho = new Campo(3, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

	@Test
	void nãoDeveAdicionarVizinho() {
		Campo vizinho = new Campo(3, 5);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);

		vizinho = new Campo(5, 3);
		resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);

		vizinho = new Campo(5, 5);
		resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaDiagonalSuperiorEsquerda() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaDiagonalInferiorEsquerda() {
		Campo vizinho = new Campo(4, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaSuperiorDireita() {
		Campo vizinho = new Campo(2, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaInferiorDireita() {
		Campo vizinho = new Campo(4, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaAcima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void deveAdicionarVizinhoNaAbaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void naoDeveAlterarMarcacao() {
		campo.setAberto();
		assertFalse(campo.isMarcado());
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void deveAlterarMarcacao() {
		assertFalse(campo.isMarcado());
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void naoDeveRetornarComoVizinhancaSegura() {
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.setMinado();
		campo.adicionarVizinho(vizinho1);
		assertFalse(campo.vizinhancaSegura());
	}

	@Test
	void deveRetornarComoVizinhancaSegura() {
		Campo vizinho1 = new Campo(2, 2);
		campo.adicionarVizinho(vizinho1);
		assertTrue(campo.vizinhancaSegura());
	}

	@Test
	void naoDeveAbrirQuandoJaEstiverAberto() {

		campo.setAberto();
		assertFalse(campo.abrir());
	}

	@Test
	void naoDeveAbrirQuandoEstiverMarcado() {

		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void deveRetornarExplocaoException() {

		campo.setMinado();
		assertThrows(
				ExplocaoExceprion.class, () -> {
					campo.abrir();
		});
	}
	
	@Test
	void deveAbrir() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void deveRetornarobjetivoAlcancadoFalsoQuandoNaoDesvendado() {
		assertFalse(campo.objetivoAlcancado());
		campo.setMinado();
		assertFalse(campo.objetivoAlcancado());
		campo.setAberto();
		assertFalse(campo.objetivoAlcancado());
	}
	
	@Test
	void deveRetornarobjetivoAlcancadoFalsoQuandoNaoProtegido() {
		assertFalse(campo.objetivoAlcancado());
		campo.setMinado();
		assertFalse(campo.objetivoAlcancado());
	}
	

	@Test
	void deveRetornarZero() {
		Campo vizinho1 = new Campo(2, 2);
		campo.adicionarVizinho(vizinho1);
		assertEquals(0, campo.minasNaVizinhanca());
	}
	@Test
	void deveRetornarUm() {
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.setMinado();
		campo.adicionarVizinho(vizinho1);
		assertEquals(1, campo.minasNaVizinhanca());
	}
	
	@Test
	void deveReiniciar() {
		campo.alternarMarcacao();
		campo.setAberto();
		campo.setMinado();
		assertTrue(campo.isAberto());
		assertTrue(campo.isMarcado());
		assertTrue(campo.isMinado());
		
		campo.reiniciar();
		assertFalse(campo.isAberto());
		assertFalse(campo.isMarcado());
		assertFalse(campo.isMinado());
	}
	
	@Test
	void deveRetornarInterrogacao() {
		assertEquals("?", campo.toString());
	}
	
	@Test
	void deveRetornarStringVazia() {
		campo.setAberto();
		assertEquals(" ", campo.toString());
	}
	
	@Test
	void deveRetornarStringComUm() {
		campo.setAberto();
		Campo vizinho1 = new Campo(2, 2);
		vizinho1.setMinado();
		campo.adicionarVizinho(vizinho1);
		assertEquals("1", campo.toString());
	}
	
	@Test
	void deveRetornarAsterisco() {
		campo.setAberto();
		campo.setMinado();
		assertEquals("*", campo.toString());
	}
	
	@Test
	void deveRetornarX() {
		campo.alternarMarcacao();
		assertEquals("x", campo.toString());
	}

}
