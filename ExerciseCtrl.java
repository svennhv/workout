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
    public void createExercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){ // ! add arguments !! done
		exercise = new Exercise (name, description, currentGoal, bestResult, weight, repetitions, sets, length, duration); // ! fill in arguments after Exercise class is finished !! done
		exercise.save(conn);
    }
    public void registerExercise(Exercise exercise, Workout workout){ // log entry, !arguments
    	// connect to database and make new entry in LOGENTRY
    }
    
    private ArrayList<Exercise> getExercises(String sql){
    	ArrayList<Exercise> list = new ArrayList<Exercise>();
    	try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery(sql);
            while (rs.next()) {
            	
				String name = rs.getString("name");
				String description = rs.getString("description");
				String currentGoal = rs.getString("currentGoal");
				String bestResult = rs.getString("bestResult");
				int weight = rs.getInt("weight");
				int repetitions = rs.getInt("repetitions");
				int sets = rs.getInt("sets");
				int length = rs.getInt("length");
				int duration = rs.getInt("duration");
	            list.add( new Exercise(name, description, currentGoal, bestResult, weight, repetitions, sets, length, duration) ); //! add variables in constructor   	
            }
            return list;
        } catch (Exception e) {
            System.out.println("db error during select of Exercise="+e);
            return null;
        }
    }
    
    
    // Records and special information:
    public Exercise getHeaviest(String name){
        try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery("SELECT MAX(weight) FROM exercise WHERE name = \""+ name + "\" ;");
            while (rs.next()) {
            	
				name = rs.getString("name");
				String description = rs.getString("description");
				String currentGoal = rs.getString("currentGoal");
				String bestResult = rs.getString("bestResult");
				int weight = rs.getInt("weight");
				int repetitions = rs.getInt("repetitions");
				int sets = rs.getInt("sets");
				int length = rs.getInt("length");
				int duration = rs.getInt("duration");

	            return new Exercise(name, description, currentGoal, bestResult, weight, repetitions, sets, length, duration); //! add variables in constructor   	
            }
            return null;
        } catch (Exception e) {
            System.out.println("db error during select of Exercise="+e);
            return null;
        }
    }
    
    public ArrayList<Exercise> getAll(){
    	ArrayList<Exercise> list = getExercises("SELECT * FROM exercises;");
    	return list;
    }
    
    public Exercise getFastest(String name){
    	// !do like in getHeaviest
    }

}
