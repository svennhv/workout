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
	
	    String name = name; 
		String description = description;
		String currentGoal = currentGoal;
		String bestResult = bestResult;
		int weight = weight;
		int repetitions = repetitions;
		int sets = sets;
		int length = length;
		int duration = duration;
    

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
    public void createExercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){ // ! add arguments !! done
		this.name = name;
		this.description = description;
		this.currentGoal = currentGoal;
		this.bestResult = bestResult;
		this.weight = weight;
		this.repetitions = repetitions;
		this.sets = sets;
		this.length = length;
		this.duration = duration;
		exercise = new Exercise (name, description, currentGoal, bestResult, weight, repetitions, sets, length, duration); // ! fill in arguments after Exercise class is finished !! done
 
    }
    public void registerExercise(String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){ // log entry, !arguments
        exercise = new Exercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration); // ! fill in arguments after Exercise class is finished
        exercise.regExercise(String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration); // ! add arguments
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
