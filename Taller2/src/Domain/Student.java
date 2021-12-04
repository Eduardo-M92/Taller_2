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
public class Student {
    private String rut;
    private String email;
    private int studentLevel;
    private String password;
    private int approvedSubjectAmount;
    private String [] approvedSubjects;
    private double [] approvedSubjectsGrades;
    private int inscribedSubjectsAmount;
    private String [] inscribedSubjectCodes;
    private int [] parallelNumbers;

    public Student(String rut, String email, int studentLevel, String password, int approvedSubjectAmount, String[] approvedSubjects, double[] approvedSubjectsGrades, int inscribedSubjectsAmount, String[] inscribedSubjectCodes, int[] parallelNumbers) {
        this.rut = rut;
        this.email = email;
        this.studentLevel = studentLevel;
        this.password = password;
        this.approvedSubjectAmount = approvedSubjectAmount;
        this.approvedSubjects = approvedSubjects;
        this.approvedSubjectsGrades = approvedSubjectsGrades;
        this.inscribedSubjectsAmount = inscribedSubjectsAmount;
        this.inscribedSubjectCodes = inscribedSubjectCodes;
        this.parallelNumbers = parallelNumbers;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(int studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getApprovedSubjectAmount() {
        return approvedSubjectAmount;
    }

    public void setApprovedSubjectAmount(int approvedSubjectAmount) {
        this.approvedSubjectAmount = approvedSubjectAmount;
    }

    public String[] getApprovedSubjects() {
        return approvedSubjects;
    }

    public void setApprovedSubjects(String[] approvedSubjects) {
        this.approvedSubjects = approvedSubjects;
    }

    public double[] getApprovedSubjectsGrades() {
        return approvedSubjectsGrades;
    }

    public void setApprovedSubjectsGrades(double[] approvedSubjectsGrades) {
        this.approvedSubjectsGrades = approvedSubjectsGrades;
    }

    public int getInscribedSubjectsAmount() {
        return inscribedSubjectsAmount;
    }

    public void setInscribedSubjectsAmount(int inscribedSubjectsAmount) {
        this.inscribedSubjectsAmount = inscribedSubjectsAmount;
    }

    public String[] getInscribedSubjectCodes() {
        return inscribedSubjectCodes;
    }

    public void setInscribedSubjectCodes(String[] inscribedSubjectCodes) {
        this.inscribedSubjectCodes = inscribedSubjectCodes;
    }

    public int[] getParallelNumbers() {
        return parallelNumbers;
    }

    public void setParallelNumbers(int[] parallelNumbers) {
        this.parallelNumbers = parallelNumbers;
    }
    
    
    
}