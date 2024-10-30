package br.ufrn.imd.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matricula {
	private final Discente discente;
	
	private final Turma turma;

	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private Integer frequencia;
	
	private StatusAprovacao status;

	public Matricula(Discente discente, Turma turma) {
		this.discente = discente;
		this.turma = turma;
	}

    public BigDecimal getNota1() {
		return nota1;
	}

	public void cadastrarNota1(BigDecimal nota1) {
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Discente getDiscente() {
		return discente;
	}

	public Turma getTurma() {
		return turma;
	}
	public BigDecimal calcularMediaParcial() {
		BigDecimal mediaParcial;
		if(nota1 == null || nota2 == null || nota3 == null){
			throw new IllegalArgumentException("As notas não podem ser nulas.");
		}
		mediaParcial = nota1.add(nota2).add(nota3);
		mediaParcial = mediaParcial.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_EVEN);
		return mediaParcial;
	}

	public void consolidarParcialmente() {
		BigDecimal media = calcularMediaParcial(); // Calcula a média uma vez

		if (frequencia >= 75) {
			if (media.doubleValue() >= 6.0) {
				this.setStatus(StatusAprovacao.APR); // Aprovado
			} else if (media.doubleValue() <= 3.0) {
				this.setStatus(StatusAprovacao.REP); // Reprovado
			} else {
				this.setStatus(StatusAprovacao.REC); // Recuperação
			}
		} else { // frequencia < 75
			if (media.doubleValue() >= 6.0) {
				this.setStatus(StatusAprovacao.REPF); // Reprovado por falta
			} else if (media.doubleValue() <= 3.0) {
				this.setStatus(StatusAprovacao.REPMF); // Reprovado por média e falta
			} else {
				this.setStatus(StatusAprovacao.REPF); // Recuperação mas nao necessario
			}
		}
	}

	public StatusAprovacao getStatus() {
		return status;
	}

	private void setStatus(StatusAprovacao status) {
		this.status = status;
	}
}