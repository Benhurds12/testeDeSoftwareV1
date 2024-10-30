package br.ufrn.imd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;


import br.ufrn.imd.model.Discente;
import br.ufrn.imd.model.Docente;
import br.ufrn.imd.model.Matricula;
import br.ufrn.imd.model.Turma;
import br.ufrn.imd.model.Disciplina;
import br.ufrn.imd.model.StatusAprovacao;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    Discente discente = new Discente("Ben");
    Docente docente = new Docente("Eiji");
    Disciplina disciplina = new Disciplina();
    Turma turma = new Turma(docente, disciplina);
    Matricula matricula = new Matricula(discente, turma);

    @ParameterizedTest
    @CsvSource({
            "5.0,   5.0,    5.0,    80, REC",
            "2.9,   2.8,    2.5,    20, REPMF",
            "0.1,   3.0,    4.0,    80, REP",
            "10.0,  10.0,   10.0,   49, REPF",
            "5.0,   6.0,    7.0,    80, APR",
            "6.0,   6.0,    5.9,    100, REC",
            "2.9,   3.0,    3.0,    20, REPMF",
            "2.9,   3.0,    3.0,    75, REP",
            "10.0,  10.0,   10.0,   74, REPF",
            "4.0,   6.0,    8.0,    95, APR"


    })
    @DisplayName("Os alunos em ordem: REC, REPMF, REP, REPF, APR")
    public void testarAluno(BigDecimal nota1,BigDecimal nota2, BigDecimal nota3,Integer frequencia, StatusAprovacao expectedStatus){



        matricula.cadastrarFrequencia(frequencia);
        matricula.cadastrarNota1(nota1);
        matricula.cadastrarNota2(nota2);
        matricula.cadastrarNota3(nota3);;


        matricula.consolidarParcialmente();

        assertEquals(expectedStatus, matricula.getStatus());
    }

}

