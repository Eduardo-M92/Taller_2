/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Student;

/**
 *
 * 
 */
public class StudentList {
    private int max;
    private int studentAmount;
    private Student [] studentList;

    public StudentList(int max) {
        this.max = max;
        this.studentAmount = 0;
        this.studentList = new Student[max];
    }
    
    public boolean addStudent(Student student) {
        if(studentAmount<max){
            studentList[studentAmount++]= student;
            return true;
        }
        else{
            return false;
        }
    }
    
    public Student getStudentI(int i){
        Student student= studentList[i];            
        return student;
    }
    

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public void setStudentList(Student[] studentList) {
        this.studentList = studentList;
    }

    
    
    
}
