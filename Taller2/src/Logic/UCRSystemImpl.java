/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.ObligatorySubject;
import Domain.OptionalSubject;
import Domain.Parallel;
import Domain.Professor;
import Domain.Student;
import Domain.Subject;

/**
 *
 * 
 */
public class UCRSystemImpl implements UCRSystem {
    private StudentList studentList;
    private SubjectList subjectList;
    private ProfessorList professorList;
    private ParallelList parallelList;

    public UCRSystemImpl() {
        this.studentList = new StudentList(9999);
        this.subjectList = new SubjectList(9999);
        this.professorList = new ProfessorList(9999);
        this.parallelList = new ParallelList(9999);
    }
    
    //adds an obligatory type of subject to the subject list
    public boolean addObligatorySubject(String code, String name, int credits, int level, int prerequisiteSubjectAmount, String [] prerequisiteSubjectCodes){
        Subject obligatorySubject = new ObligatorySubject(code, name, credits, level, prerequisiteSubjectAmount, prerequisiteSubjectCodes);
        boolean addCheck= subjectList.addSubject(obligatorySubject);
        return addCheck;
    }
    //adds an optional type of subject to the subject list
    public boolean addOptionalSubject(String code, String name, int credits, int prerequisiteCredits){
        Subject optionalSubject = new OptionalSubject(code, name, credits,prerequisiteCredits);
        boolean addCheck= subjectList.addSubject(optionalSubject);
        return addCheck;
    }

    @Override
    //adds an student to the student list
    public boolean addStudent(String rut, String email, int studentLevel, String password, int approvedSubjectsAmount, String[] approvedSubject, double[] approvecSubjectGrades, int inscribedSubjectAmount, String[] inscribedSubjectCodes, int[] parallelNumbers) {
        Student student= new Student(rut, email, studentLevel, password, approvedSubjectsAmount, approvedSubject, approvecSubjectGrades, inscribedSubjectAmount, inscribedSubjectCodes, parallelNumbers);
        boolean addCheck= studentList.addStudent(student);
        return addCheck;
    }

    @Override
    //adds a professor to the professor list
    public boolean addProfessor(String rut, String email, String password, int salary) {
        Professor professor= new Professor(rut,email,password,salary);
        boolean addCheck= professorList.addProfessor(professor);
        return addCheck;
    }

    @Override
        //adds a parallel to the parallel list
    public boolean addParallel(String parallelNumber, String parallelCode, String professorRut) {
        Parallel parallel= new Parallel(parallelNumber, parallelCode, professorRut);
        boolean addCheck= parallelList.addParallel(parallel);
        return addCheck;
    }
    
    //checks if a user is registered in the system or not
    public int [] checkIfRegister(String email){
        int [] result= new int[2]; 
        int type= -1;
        int answer= -1;
        int studentAmount= studentList.getStudentAmount();
        int professorAmount= professorList.getProfessorAmount();
        for(int i= 0;i<studentAmount;i++){
            if(email.equalsIgnoreCase(studentList.getStudentList()[i].getEmail())){
                answer= i;
                type= 1;
            }
            
        }
        for(int i= 0;i<professorAmount;i++){
            if(email.equalsIgnoreCase(professorList.getProfessorList()[i].getEmail())){
                answer= i;
                type= 2;
            }
            
        }
        if(email.equalsIgnoreCase("admin")){
            type= 3;
        }
        
        result[0]= type;
        result[1]= answer;
        
        return result;
    }

    //gets a student from the student list class
    public Student getStudent(int i) {
        Student student= studentList.getStudentI(i);
        return student;
    }
    //gets a professor  from the professor  list class
    public Professor getProfessor(int i) {
        Professor professor= professorList.getProfessorI(i);
        return professor;
    }
    //counts the amount of credits a student has 
    public int creditCounter(Student student){
        int totalCredits= 0;
        int amount= student.getApprovedSubjectAmount();
        String [] asList= student.getApprovedSubjects();
        double [] asgList= student.getApprovedSubjectsGrades();
        int sAmount= subjectList.getSubjectAmount();
        for(int i= 0;i<amount;i++){
            for(int e=0;e<sAmount;e++){
                if(asList[i].equals(subjectList.getSubjectList()[e].getCode()) && asgList[i]>3.9){
                    totalCredits+= subjectList.getSubjectList()[e].getCredits();
                }
            }
        }
        return totalCredits;
    }
    //allows the user to inscribe a subject
    public String [] inscribeSubject(Student student){
        int sLevel= student.getStudentLevel();
        int sCredits= creditCounter(student);
        String [] passedSubjects= student.getApprovedSubjects();
        String [] repeatableSubjects= new String[99];
        int counter= 0;
        int counter2=0;
        for(int e=0;e<student.getApprovedSubjectAmount();e++){
            if(student.getApprovedSubjectsGrades()[e]<4.0){
                repeatableSubjects[counter++]=student.getApprovedSubjects()[e];
            }
            
            if(student.getApprovedSubjectsGrades()[e]>3.9){
                passedSubjects[counter2++]=student.getApprovedSubjects()[e];
            }
        }
        

        
        
        String [] inListSubjects= new String[99];
        int counter3= 0;
        for(int i=0;i<subjectList.getSubjectAmount();i++){
//            System.out.println("<---"+subjectList.getSubjectList()[i].getSubjectName());
            if(subjectList.getSubjectList()[i] instanceof OptionalSubject){
                OptionalSubject oSubject= (OptionalSubject) subjectList.getSubjectList()[i];
                if(oSubject.getPrerequisiteCredits()<=sCredits){
                    inListSubjects[counter3++]=oSubject.getCode();
                }
                
            }
             //&& obSubject.getPrerequisiteSubjectAmount()!=0//
            if(subjectList.getSubjectList()[i] instanceof ObligatorySubject){
                ObligatorySubject obSubject= (ObligatorySubject) subjectList.getSubjectList()[i];
                
                ////
                if(obSubject.getLevel()<=sLevel){
//                    System.out.println("obSubject.getCode()"+obSubject.getCode());

                    String subjectCheck= checkIfAvailable(passedSubjects, obSubject, counter2);
                    if(subjectCheck!=null){
                        inListSubjects[counter3++]=subjectCheck;
//                        System.out.println("-----------------------------"+obSubject.getSubjectName());
                    }
                }
            }
        }
//        for(int i=0;i<inListSubjects.length;i++){
//            if(inListSubjects[i]!=null){
//                System.out.println("inlist "+inListSubjects[i]);
//            }
//            
//        }
        
        String [] finalList= uniqueListSorter(passedSubjects, counter2, inListSubjects, counter3);
//        for(int i=0;i<finalList.length;i++){
//            if(finalList[i]!=null){
//                    System.out.println("final "+finalList[i]);
//            }
//
//        }
        return finalList;
        
    }
    
    @Override
    //checks if a student has enough of the prerequired subjects to inscribe a new subject
    public String checkIfAvailable(String [] list1, ObligatorySubject obSubject, int amount1){
        String available= null;
        int amountOfPrerequiredSubjects= obSubject.getPrerequisiteSubjectAmount();
        
        int counter= 0;
        for(int i=0;i<amountOfPrerequiredSubjects;i++){
            for(int e=0;e<amount1;e++){
                if(obSubject.getPrerequisiteSubjectCodes()[i].equals(list1[e])){
                    counter++;
                }
            }
            
        }
        if(counter==amountOfPrerequiredSubjects){
            available= obSubject.getCode();
        }
        return available;
        
    }
    
    //allows the user to sort a list with just unique elements in it
    public String [] uniqueListSorter(String [] list1,int length1, String [] list2, int length2){
        for(int i=0;i<length1;i++){
            String [] newList= new String[99];
            int newListCounter= 0;
            for(int e=0;e<length2;e++){
                if(!list1[i].equalsIgnoreCase(list2[e])){
                    newList[newListCounter++]=list2[e];
                }
            }
            list2=newList;
        }
        
        return list2;
    }
    
    //allows the user to look for a subject's name in the subject list
    public String searchSubjectName(String subjectCode){
        String sName= "";
        int subjAmount= subjectList.getSubjectAmount();
        for(int i=0;i<subjAmount;i++){
            if(subjectList.getSubjectList()[i].getCode().equals(subjectCode)){
                sName=subjectList.getSubjectList()[i].getSubjectName();
            }
        }
        
        
        return sName;
    }
    
    //allows the user to search for a String element in a list
    public int elementInListString(String[]list, int listLength,String element){
        int value=-1;
        for(int i=0;i<listLength;i++){
            if(list[i].equalsIgnoreCase(element)){
                value++;
            }
        }
        return value;
    }
    
    //allows the user to check if there are any parallels available for them to pick one for a subject
    public String [] availableParallels(String subjectCode){
        String [] aParallels= new String[30];
        int amountOfParallels= parallelList.getParallelAmount();
        int counter= 0;
        for(int i= 0;i<amountOfParallels;i++){
//            System.out.println("inside "+parallelList.getParallelList()[i].getParallelCode());
            if(parallelList.getParallelList()[i].getParallelCode().equalsIgnoreCase(subjectCode)){
                aParallels[counter]=parallelList.getParallelList()[i].getParallelNumber();
                counter++;
            }
        }
        if(counter==0){
            aParallels[0]="-1";
            
            
        }
        return aParallels;
    }
    //allows the user to delete a subject from the inscribed subjects they took
    public boolean deleteSubject(Student student, String subjectCode){
        int i;
        String [] sSubject= student.getInscribedSubjectCodes();
        for(i=0;i<student.getInscribedSubjectsAmount();i++){
            if(sSubject[i].equals(subjectCode)){
                break;
            }
        }
        if(i==student.getInscribedSubjectsAmount()){
            return false;
        }
        else{
            for(int j=i;j<student.getInscribedSubjectsAmount()-1;j++){
                student.getInscribedSubjectCodes()[j]=student.getInscribedSubjectCodes()[j+1];
            }
            return true;
        }
    }
    //allows the user to know how big an array actually is in size
    public int actualSize(String [] list1){
        int counter= 0;
        for(int i=0;i<list1.length;i++){
            if(list1[i]!=null){
                counter++;
            }
        }
        return counter;
    }

    
    
}
