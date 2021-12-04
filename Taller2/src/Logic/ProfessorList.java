/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Professor;

/**
 *
 *
 */
public class ProfessorList {
    private int max;
    private int professorAmount;
    private Professor [] professorList;

    public ProfessorList(int max) {
        this.max = max;
        this.professorAmount = 0;
        this.professorList = new Professor[max];
    }
    
    boolean addProfessor(Professor professor) {
        if(professorAmount<max){
            professorList[professorAmount++]= professor;
            return true;
        }
        else{
            return false;
        }
    }

    public Professor getProfessorI(int i){
        Professor professor= professorList[i];            
        return professor;
    }
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProfessorAmount() {
        return professorAmount;
    }

    public void setProfessorAmount(int professorAmount) {
        this.professorAmount = professorAmount;
    }

    public Professor[] getProfessorList() {
        return professorList;
    }

    public void setProfessorList(Professor[] professorList) {
        this.professorList = professorList;
    }

    

    
    
    
    
}
