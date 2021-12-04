/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 *
 */
public class Parallel {
    private String parallelNumber;
    private String parallelCode;
    private String professorRut;

    public Parallel(String parallelNumber, String parallelCode, String professorRut) {
        this.parallelNumber = parallelNumber;
        this.parallelCode = parallelCode;
        this.professorRut = professorRut;
    }

    public String getParallelNumber() {
        return parallelNumber;
    }

    public void setParallelNumber(String parallelNumber) {
        this.parallelNumber = parallelNumber;
    }

    public String getParallelCode() {
        return parallelCode;
    }

    public void setParallelCode(String parallelCode) {
        this.parallelCode = parallelCode;
    }

    public String getProfessorRut() {
        return professorRut;
    }

    public void setProfessorRut(String professorRut) {
        this.professorRut = professorRut;
    }
    
    
    
}
