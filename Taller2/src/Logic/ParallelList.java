/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Parallel;

/**
 *
 
 */
public class ParallelList {
    private int max;
    private int parallelAmount;
    private Parallel [] parallelList;

    public ParallelList(int max) {
        this.max = max;
        this.parallelAmount = 0;
        this.parallelList = new Parallel[max];
    }
    
    boolean addParallel(Parallel parallel) {
        if(parallelAmount<max){
            parallelList[parallelAmount++]= parallel;
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

    public int getParallelAmount() {
        return parallelAmount;
    }

    public void setParallelAmount(int parallelAmount) {
        this.parallelAmount = parallelAmount;
    }

    public Parallel[] getParallelList() {
        return parallelList;
    }

    public void setParallelList(Parallel[] parallelList) {
        this.parallelList = parallelList;
    }

    
    
    
}
