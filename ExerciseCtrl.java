/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workoutA;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

public class ExerciseCtrl extends DBConn {
    private Exercise exercise;
    

    public ExerciseCtrl () {
        connect();
        // La laging av avtale være en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAvtaleCtrl="+e);
            return;
        }
    }

    // !convert example to exercise:
    public void createExercise (){ // ! add arguments
        exercise = new Exercise (); // ! fill in arguments after Exercise class is finished
 
    }
    public void registerExercise(){ // log entry, !arguments
        exercise = new Exercise (); // ! fill in arguments after Exercise class is finished
        exercise.regExercise(); // ! add arguments
    }
    
    
    // Records and special information:
    public Exercise getHeaviest(String name){
        try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery("SELECT MAX(weight) FROM exercise WHERE name = \""+ name + "\" ;");
            while (rs.next()) {
            	// ! create variables. like in the example under:
            	/*
            	timer = rs.getInt("timer");
            	*/
            }
            return new Exercise(); //! add variables in constructor
        } catch (Exception e) {
            System.out.println("db error during select of Exercise="+e);
            return null;
        }
    }
    
    public Exercise getFastest(String name){
    	// !do like in getHeaviest
    }

}
