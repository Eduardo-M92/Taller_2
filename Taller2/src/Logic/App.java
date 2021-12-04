/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Professor;
import Domain.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 *
 *
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {
        UCRSystem sys = new UCRSystemImpl();
        readSubjects(sys);
        readStudents(sys);
        readParallels(sys);
        readProfessors(sys);
        menu(sys);
        
        
    }
    
    
    public static Date ConvertDate(int day,int month,int year) throws IOException{
        String date =String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
        return ParseFecha(date);
    }
    
    public static Date ParseFecha(String inputDate){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(inputDate);
        }
        catch (ParseException ex) 
        {
            System.out.println("Fecha no válida.");
        }
        return date;
    }
    
    public static int readNumber(String time) {
    Scanner scan= new Scanner(System.in);
    while (true) {
        System.out.print(time+": ");

        try {
            int readNumber = Integer.parseInt(scan.nextLine());
            return readNumber;
        } catch (Exception e) {
            System.out.println("User input was not a number.");
        }
    }
}

    public static void readSubjects(UCRSystem sys)throws FileNotFoundException{
        Scanner scan= new Scanner(new File("asignaturas.txt"));
        boolean addingSubject= true;
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            int dataLength= data.length;
            String code= data[0];
            String name= data[1];
            int credits= Integer.parseInt(data[2]);
            String type= data[3];
            if(type.equalsIgnoreCase("obligatoria")){
                int level= Integer.parseInt(data[4]);
                int amountOfRequiredSubjects= Integer.parseInt(data[5]);
                if(amountOfRequiredSubjects!=0){
                    String [] subjectCodes= new String[amountOfRequiredSubjects];
                    int counter= 6;
                    for(int i=0;i<amountOfRequiredSubjects;i++){
                        
                        subjectCodes[i]= data[counter++];
                        
                    }
                    
                    addingSubject= sys.addObligatorySubject(code, name, credits, level, amountOfRequiredSubjects, subjectCodes);
                    if(!addingSubject){
                        System.out.println("No hay más espacio.");
                        
                    }
                }
                else{
                    String [] empty= new String[10];
                    addingSubject= sys.addObligatorySubject(code, name, credits, level, amountOfRequiredSubjects, empty);
                    if(!addingSubject){
                        System.out.println("No hay más espacio.");
                        
                    }
                }
            }
            if(type.equalsIgnoreCase("opcional")){
                int prerequisiteCredits= Integer.parseInt(data[4]);
                addingSubject= sys.addOptionalSubject(code, name, credits, prerequisiteCredits);
                if(!addingSubject){
                    System.out.println("No hay más espacio.");
                }
            }
        }
    }
    
    public static void readStudents(UCRSystem sys)throws FileNotFoundException{
        Scanner scan= new Scanner(new File("estudiantes.txt"));
        boolean addingStudent= true;
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String rut= data[0];
            String email= data[1];
            int studentLevel= Integer.parseInt(data[2]);
            String password= data[3];
            int approvedSubjectsAmount= Integer.parseInt(data[4]);
            String [] approvedSubject= new String[99];
            double [] approvecSubjectGrades= new double[99];
            int counter= 5;
            if(approvedSubjectsAmount!=0){
                for(int i= 0;i<approvedSubjectsAmount;i++){
                    approvedSubject[i]=data[counter];
                    counter++;
                    approvecSubjectGrades[i]=Double.parseDouble(data[counter]);
                    counter++;
                }
            }
            int inscribedSubjectAmount= Integer.parseInt(data[counter++]);
            int counter2= counter;
            String [] inscribedSubjectCodes= new String[99];
            int [] parallelNumbers= new int[99];
            if(inscribedSubjectAmount!=0){
                for(int i= 0;i<inscribedSubjectAmount;i++){
                    inscribedSubjectCodes[i]=data[counter2++];
                    parallelNumbers[i]= Integer.parseInt(data[counter2++]);
                }
            }
            
            addingStudent= sys.addStudent(rut,email,studentLevel,password,approvedSubjectsAmount,approvedSubject,approvecSubjectGrades,inscribedSubjectAmount,inscribedSubjectCodes,parallelNumbers);
            if(!addingStudent){
                System.out.println("No hay más espacio.");
            }
        }
    }
    
    public static void readProfessors(UCRSystem sys)throws FileNotFoundException{
        Scanner scan= new Scanner(new File("profesores.txt"));
        boolean addingProfessor= true;
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String rut= data[0];
            String email= data[1];
            String password= data[2];
            int salary= Integer.parseInt(data[3]);
            addingProfessor= sys.addProfessor(rut,email,password,salary);
            if(!addingProfessor){
                System.out.println("No hay más espacio.");
            }
        }
    }
    
    public static void readParallels(UCRSystem sys)throws FileNotFoundException{
        Scanner scan= new Scanner(new File("paralelos.txt"));
        boolean addingParallel= true;
        while(scan.hasNextLine()){
            String [] data= scan.nextLine().split(",");
            String parallelNumber= data[0];
            String parallelCode= data[1];
            String professorRut= data[2];
            addingParallel= sys.addParallel(parallelNumber,parallelCode,professorRut);
            if(!addingParallel){
                System.out.println("No hay más espacio.");
            }
            
        }
    }
    
    public static void menu(UCRSystem sys) throws NullPointerException, IOException{
        Scanner scan= new Scanner(System.in);
        Scanner sc= new Scanner(System.in);
        System.out.println("--- Bienvenido al sistema de la UCR ---");
        System.out.println("Cargando...");
        System.out.println("Inicio de sesión");
        Date start1= ConvertDate(8, 3, 2021);
        Date start2= ConvertDate(2, 5, 2021);
        Date mid1= ConvertDate(3,5, 2021);
        Date mid2= ConvertDate(11, 7, 2021);
        Date final1= ConvertDate(12, 7, 2021);
        Date final2= ConvertDate(25, 7, 2021);
        
        
        boolean exit= false;
        while(!exit){
            boolean logOut= false;
            while(!logOut){
                System.out.print("Ingrese su correo electrónico (Ingrese -1 para salir del sistema): ");
                String userEmail= scan.nextLine();
                if(userEmail.equals("-1")){
                    logOut=true;
                    exit=true;
                    break;
                }
                int [] validAccount= new int[2];
                validAccount= sys.checkIfRegister(userEmail);
                
                while(validAccount[0]==-1){
                    System.out.println("¿Desea intentar nuevamente o salir del sistema?");
                    System.out.println("1) Intentar nuevamente\n2) Salir del sistema");
                    System.out.print("Ingrese 1 o 2: ");
                    String userAnswer= scan.nextLine();
                    while(!userAnswer.equals("1") && !userAnswer.equals("2")){
                        System.out.print("Respuesta inválida.\nElija una de las opciones.\n1) Intentar nuevamente\n2) Salir del sistema\nIngrese su respuesta: ");
                        userAnswer= scan.nextLine();
                    }
                    if(userAnswer.equals("2")){
                        validAccount[0]=-4;
                        exit= true;
                        logOut= true;
                        System.out.println("Gracias por usar nuestro sistema.");
                        
                    }
                    if(userAnswer.equals("1")){
                        System.out.print("Ingrese su correo electrónico: ");
                        userEmail= scan.nextLine();
                        validAccount= sys.checkIfRegister(userEmail);
                    }
                    
                }
                //Students menu
                if(validAccount[0]==1){
                    Student activeUser= sys.getStudent(validAccount[1]);
                    System.out.print("Ingrese su contraseña: ");
                    String enteredPassword= scan.nextLine();
                    String validPassword= activeUser.getPassword();
                    while(!enteredPassword.equals(validPassword)){
                        System.out.print("La contraseña ingresada es incorrecta.\nPor favor ingrese su contraseña: ");
                        enteredPassword= scan.nextLine();
                    }
                    System.out.println("Ingrese la fecha actual");
                    System.out.print("dd/MM/yyyy: ");
                    String iDate= scan.nextLine();
                    Date dateChecker= ParseFecha(iDate);
                    while(dateChecker==null){
                        System.out.println("Ingrese la fecha actual");
                        System.out.print("dd/MM/yyyy: ");
                        iDate= scan.nextLine();
                        dateChecker= ParseFecha(iDate);
                    }
                    
                    if(dateChecker.after(start1) &&dateChecker.before(start2)){
                        System.out.println("--- Inicio de semestre ---");
                        System.out.println("--------------------------");
                        System.out.println("Ingrese una de las siguientes opciones: ");
                        System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                        String userChoice= scan.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")){
                            System.out.println("Respuesta no válida");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= scan.nextLine();
                        }
                        if(userChoice.equals("3")){
                            exit=true;
                            logOut=true;
                            break;
                        }
                        while(!userChoice.equals("3")){
                            if(userChoice.equals("1")){
                                String [] availableSubjects=sys.inscribeSubject(activeUser);
                                int availableSubjectSize=0;
                                System.out.println("-- Asignaturas disponibles --");
                                for(int i=0;i<availableSubjects.length;i++){
                                    if(availableSubjects[i]!=null){
                                        availableSubjectSize++;
                                        System.out.println("-) "+availableSubjects[i]+", "+sys.searchSubjectName(availableSubjects[i]));
                                    }
                                }
                                System.out.print("Ingrese el código de una de las opciones dadas: ");
                                String userAnswer1= scan.nextLine();
                                int checkIfInside= sys.elementInListString(availableSubjects, availableSubjectSize,userAnswer1);
                                while(checkIfInside==-1){
                                    System.out.print("Error.\nIngrese el código de una de las opciones dadas: ");
                                    userAnswer1= scan.nextLine();
                                    checkIfInside= sys.elementInListString(availableSubjects, availableSubjectSize,userAnswer1);
                                }
                                System.out.println("Elija el paralelo a entrar: ");
                                String [] availableParallels= sys.availableParallels(userAnswer1);
                                int availableParallelsSize= 0;
                                if(availableParallels[0].equals("-1")){
                                    System.out.println("No existen paralelos disponibles para esta clase.");
                                    exit= true;
                                    logOut=true;
                                }
                                else{
                                    System.out.println("-- Número del paralelo --");
                                    for(int i=0;i<sys.actualSize(availableParallels);i++){
                                        if(availableParallels[i]!=null){
                                            availableParallelsSize++;
                                            System.out.println("-) "+availableParallels[i]);
                                        }
                                    }

                                    System.out.print("Ingrese el número del paralelo: ");
                                    String userAnswer2= scan.nextLine();
                                    int checkIfInside2= sys.elementInListString(availableParallels, availableParallelsSize,userAnswer2);
                                    while(checkIfInside2==-1){
                                        System.out.print("Error.\nIngrese el número del paralelo: ");
                                        userAnswer2= scan.nextLine();
                                        checkIfInside2= sys.elementInListString(availableParallels, availableParallelsSize,userAnswer2);
                                    }
                                    int activeUserAmountOfInscriptions= activeUser.getInscribedSubjectsAmount();
//                                    System.out.println(activeUserAmountOfInscriptions+"<<<<<<<<<x");
                                    String [] activeUserInscriptions= activeUser.getInscribedSubjectCodes();
                                    for(int i=0;i<activeUserAmountOfInscriptions;i++){
                                        if(activeUserInscriptions[i]!=null){
                                            System.out.println(activeUserInscriptions[i]);
                                        }
                                    }

                                    int [] activeUserParallels= activeUser.getParallelNumbers();
                                    activeUserInscriptions[activeUserAmountOfInscriptions]= userAnswer1;
                                    activeUserParallels[activeUserAmountOfInscriptions]=Integer.parseInt(userAnswer2);
                                    activeUser.setInscribedSubjectCodes(activeUserInscriptions);
                                    activeUser.setParallelNumbers(activeUserParallels);
                                    activeUser.setInscribedSubjectsAmount(activeUser.getInscribedSubjectsAmount()+1);

                                }



                             

                            }

                            if(userChoice.equals("2")){
                                System.out.println("-- Asignaturas inscritas --");
                                int amountOfInscribedSubjects= activeUser.getInscribedSubjectsAmount();
//                                System.out.println(amountOfInscribedSubjects+"<<<<<<<<<");
                                String [] inscribedSubjects= activeUser.getInscribedSubjectCodes();
                                int inscribedSubjectsSize= 0;
                                if(amountOfInscribedSubjects==0){
                                    System.out.println("No tiene asignaturas inscritas.");
                                }
                                else{
                                    for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
                                        if(inscribedSubjects[i]!=null){
                                            inscribedSubjectsSize++;
                                            System.out.println("-) "+inscribedSubjects[i]+", "+sys.searchSubjectName(inscribedSubjects[i]));
                                        }
                                    }
                                    
                                }
//                                for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
//                                    System.out.println("--<-<--<-"+inscribedSubjects[i]);
//                                }
                                System.out.print("Ingrese el código de una de las opciones dadas: ");
                                String userAnswer1= scan.nextLine();
                                int checkIfInside= sys.elementInListString(inscribedSubjects, inscribedSubjectsSize,userAnswer1);
                                while(checkIfInside==-1){
                                    System.out.print("Error.\nIngrese el código de una de las opciones dadas: ");
                                    userAnswer1= scan.nextLine();
                                    checkIfInside= sys.elementInListString(inscribedSubjects, inscribedSubjectsSize,userAnswer1);
                                }
                                
//                                for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
//                                    System.out.println("--<-<--<-"+inscribedSubjects[i]);
//                                }
                                
                                boolean deleted= sys.deleteSubject(activeUser, userAnswer1);
                                if(deleted){
                                    System.out.println("Usted a eliminado la asignatura de manera exitosa.");
                                }
                                

                            }

                            System.out.println("--- Inicio de semestre ---");
                            System.out.println("--------------------------");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= sc.nextLine();
                            while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")){
                                System.out.println("Respuesta no válida");
                                System.out.println("Ingrese una de las siguientes opciones: ");
                                System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                                userChoice= sc.nextLine();
                            }
                            
                        }
                        
                        
                    }
                    else if(dateChecker.after(mid1) &&dateChecker.before(mid2)){
                        System.out.println("--- Mitad de semestre ---");
                        System.out.println("--------------------------");
                        System.out.println("Ingrese una de las siguientes opciones: ");
                        System.out.print("1) Eliminar asignaturas\n2) Salir del sistema\nIngrese su respuesta: ");
                        String userChoice= scan.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")){
                            System.out.println("Respuesta no válida");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= scan.nextLine();
                        }
                        if(userChoice.equals("2")){
                            exit=true;
                            logOut=true;
                            break;
                        }
                        if(userChoice.equals("1")){
                            System.out.println("-- Asignaturas inscritas --");
                            int amountOfInscribedSubjects= activeUser.getInscribedSubjectsAmount();
//                                System.out.println(amountOfInscribedSubjects+"<<<<<<<<<");
                            String [] inscribedSubjects= activeUser.getInscribedSubjectCodes();
                            int inscribedSubjectsSize= 0;
                            if(amountOfInscribedSubjects==0){
                                System.out.println("No tiene asignaturas inscritas.");
                            }
                            else{
                                for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
                                    if(inscribedSubjects[i]!=null){
                                        inscribedSubjectsSize++;
                                        System.out.println("-) "+inscribedSubjects[i]+", "+sys.searchSubjectName(inscribedSubjects[i]));
                                    }
                                }

                            }
                            for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
                                System.out.println("--<-<--<-"+inscribedSubjects[i]);
                            }
                            System.out.print("Ingrese el código de una de las opciones dadas: ");
                            String userAnswer1= scan.nextLine();
                            int checkIfInside= sys.elementInListString(inscribedSubjects, inscribedSubjectsSize,userAnswer1);
                            while(checkIfInside==-1){
                                System.out.print("Error.\nIngrese el código de una de las opciones dadas: ");
                                userAnswer1= scan.nextLine();
                                checkIfInside= sys.elementInListString(inscribedSubjects, inscribedSubjectsSize,userAnswer1);
                            }

                            for(int i=0;i<sys.actualSize(inscribedSubjects);i++){
                                System.out.println("--<-<--<-"+inscribedSubjects[i]);
                            }

                            boolean deleted= sys.deleteSubject(activeUser, userAnswer1);
                            if(deleted){
                                System.out.println("Usted a eliminado la asignatura de manera exitosa.");
                            }
                                

                            }

                            System.out.println("--- Inicio de semestre ---");
                            System.out.println("--------------------------");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= sc.nextLine();
                            while(!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3")){
                                System.out.println("Respuesta no válida");
                                System.out.println("Ingrese una de las siguientes opciones: ");
                                System.out.print("1) Inscripción de asignaturas\n2) Eliminación de asignaturas\n3) Salir del sistema\nIngrese su respuesta: ");
                                userChoice= sc.nextLine();
                            }
                        
                        
                    }
                    else if(dateChecker.after(final1) &&dateChecker.before(final2)){
                        System.out.println("No hay acciones disponibles.");
                        logOut=true;
                        exit=true;
                    }
                    else{
                        System.out.println("Disfrute sus vacaciones.");
                        logOut= true;
                        exit= true;
                        
                    }
                    
                    
                    
                    

                    
                    
                }
                if(validAccount[0]==2){
                    Professor activeUser= sys.getProfessor(validAccount[1]);
                    System.out.print("Ingrese su contraseña: ");
                    String enteredPasswordP= scan.nextLine();
                    String validPassword= activeUser.getPassword();
                    while(!enteredPasswordP.equals(validPassword)){
                        System.out.print("La contraseña ingresada es incorrecta.\nPor favor ingrese su contraseña: ");
                        enteredPasswordP= scan.nextLine();
                    }
                    System.out.println("Ingrese la fecha actual");
                    System.out.print("dd/MM/yyyy: ");
                    String iDate= scan.nextLine();
                    Date dateChecker= ParseFecha(iDate);
                    while(dateChecker==null){
                        System.out.println("Ingrese la fecha actual");
                        System.out.print("dd/MM/yyyy: ");
                        iDate= scan.nextLine();
                        dateChecker= ParseFecha(iDate);
                    }
                    if(dateChecker.after(start1) &&dateChecker.before(start2)){
                        System.out.println("--- Inicio de semestre ---");
                        System.out.println("--------------------------");
                        System.out.println("Ingrese una de las siguientes opciones: ");
                        System.out.print("1) Inscripción de asignaturas\n2) Salir del sistema\nIngrese su respuesta: ");
                        String userChoice= scan.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2")){
                            System.out.println("Respuesta no válida");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Chequeo alumnos \n2) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= scan.nextLine();
                        }
                        if(userChoice.equals("2")){
                            exit=true;
                            logOut=true;
                            break;
                        }
                        while(!userChoice.equals("2")){
                            if(userChoice.equals("1")){
                                
                            
                                
                            
                            }
                
                        }
                
                    }
                    else if(dateChecker.after(mid1) &&dateChecker.before(mid2)){
                        System.out.println("No hay acciones disponibles.");
                        logOut=true;
                        exit=true;
                    }
                    else if(dateChecker.after(final1) &&dateChecker.before(final2)){
                        System.out.println("--- Inicio de semestre ---");
                        System.out.println("--------------------------");
                        System.out.println("Ingrese una de las siguientes opciones: ");
                        System.out.print("1) Ingreso nota final\n2) Salir del sistema\nIngrese su respuesta: ");
                        String userChoice= scan.nextLine();
                        while(!userChoice.equals("1") && !userChoice.equals("2")){
                            System.out.println("Respuesta no válida");
                            System.out.println("Ingrese una de las siguientes opciones: ");
                            System.out.print("1) Ingreso nota final\n2) Salir del sistema\nIngrese su respuesta: ");
                            userChoice= scan.nextLine();
                        }
                    }
                    else{
                        System.out.println("Disfrute sus vacaciones.");
                        logOut= true;
                        exit= true;
                        
                    }
                }
                if(validAccount[0]==3){
                    System.out.print("Ingrese su contraseña: ");
                    String enteredPasswordAdmin= scan.nextLine();
                    while(!enteredPasswordAdmin.equals("GHI_789")){
                        System.out.print("La contraseña ingresada es incorrecta.\nPor favor ingrese su contraseña: ");
                        enteredPasswordAdmin= scan.nextLine();
                    }
                    System.out.println("---- Bienvenido Administrador ---");
                
                
                
                
                }
                
        
            }
        }
    }
    
    
    
    
        

}