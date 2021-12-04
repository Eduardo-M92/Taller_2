/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.ObligatorySubject;
import Domain.Professor;
import Domain.Student;

/**
 *
 * 
 */
public interface UCRSystem {
    
    public boolean addObligatorySubject(String code, String name, int credits, int level, int prerequisiteSubjectAmount, String [] prerequisiteSubjectCodes);
    public boolean addOptionalSubject(String code, String name, int credits, int prerequisiteCredits);
    public boolean addStudent(String rut, String email, int studentLevel, String password, int approvedSubjectsAmount, String[] approvedSubject, double[] approvecSubjectGrades, int inscribedSubjectAmount, String[] inscribedSubjectCodes, int[] parallelNumbers);

    public boolean addProfessor(String rut, String email, String password, int salary);

    public boolean addParallel(String parallelNumber, String parallelCode, String professorRut);


    //
//    public int checkIfRegisteredStudent(String email);
//    public int checkIfRegisteredProfessor(String email);
    //
    public String [] inscribeSubject(Student student);
    public int [] checkIfRegister(String email);
    public String checkIfAvailable(String [] list1, ObligatorySubject oSubject, int amount1);
    public Student getStudent(int i);
    public String searchSubjectName(String subjectCode);
    public String [] uniqueListSorter(String [] list1,int length1, String [] list2, int length2);
    public int elementInListString(String[]list, int listLength,String element);
    
    public String [] availableParallels(String subjectCode);
    public boolean deleteSubject(Student student, String subjectCode);
    public int actualSize(String [] list1);
    public Professor getProfessor(int i);
    
    
}
