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
public class OptionalSubject extends Subject{
    private int prerequisiteCredits;

    public OptionalSubject(String code, String subjectName, int credits, int prerequisiteCredits) {
        super(code, subjectName, credits);
        this.prerequisiteCredits= prerequisiteCredits;
    }

    public int getPrerequisiteCredits() {
        return prerequisiteCredits;
    }

    public void setPrerequisiteCredits(int prerequisiteCredits) {
        this.prerequisiteCredits = prerequisiteCredits;
    }

    
    

    
    
    
}
