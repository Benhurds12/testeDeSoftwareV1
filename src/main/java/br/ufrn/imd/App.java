package br.ufrn.imd;

import br.ufrn.imd.model.Discente;
import br.ufrn.imd.model.Docente;
import br.ufrn.imd.model.Matricula;
import br.ufrn.imd.model.Turma;
import br.ufrn.imd.model.Disciplina;


import java.math.BigDecimal;



/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Discente discente = new Discente("Ben");
        Docente docente = new Docente("Eiji");
        Disciplina disciplina = new Disciplina();
        Turma turma = new Turma(docente, disciplina);
        Matricula matricula = new Matricula(discente, turma);

        matricula.cadastrarFrequencia(90);
        matricula.cadastrarNota1(BigDecimal.valueOf(5.5));
        matricula.cadastrarNota2(BigDecimal.valueOf(5.5));
        matricula.cadastrarNota3(BigDecimal.valueOf(5.5));
        matricula.consolidarParcialmente();
        System.out.println(matricula.getStatus());
        System.out.println(matricula.calcularMediaParcial());


    }
}
