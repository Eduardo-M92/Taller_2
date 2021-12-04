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
public class ObligatorySubject extends Subject{
    private int level;
    private int prerequisiteSubjectAmount;
    private String [] prerequisiteSubjectCodes;
    
    public ObligatorySubject(String code, String subjectName, int credits, int level, int prerequisiteSubjectAmount,String [] prerequisiteSubjectCodes) {
        super(code, subjectName, credits);
        this.level= level;
        this.prerequisiteSubjectAmount= prerequisiteSubjectAmount;
        this.prerequisiteSubjectCodes= prerequisiteSubjectCodes;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrerequisiteSubjectAmount() {
        return prerequisiteSubjectAmount;
    }

    public void setPrerequisiteSubjectAmount(int prerequisiteSubjectAmount) {
        this.prerequisiteSubjectAmount = prerequisiteSubjectAmount;
    }

    public String[] getPrerequisiteSubjectCodes() {
        return prerequisiteSubjectCodes;
    }

    public void setPrerequisiteSubjectCodes(String[] prerequisiteSubjectCodes) {
        this.prerequisiteSubjectCodes = prerequisiteSubjectCodes;
    }
    
    
    
}
