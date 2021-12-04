/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Subject;

/**
 *
 * 
 */
public class SubjectList {
    private int max;
    private int subjectAmount;
    private Subject [] subjectList;

    public SubjectList(int max) {
        this.max = max;
        this.subjectAmount = 0;
        this.subjectList = new Subject[max];
    }

    public boolean addSubject(Subject subject){
        if(subjectAmount<max){
            subjectList[subjectAmount++]= subject;
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSubjectAmount() {
        return subjectAmount;
    }

    public void setSubjectAmount(int subjectAmount) {
        this.subjectAmount = subjectAmount;
    }

    public Subject[] getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Subject[] subjectList) {
        this.subjectList = subjectList;
    }
    
    
    
}
