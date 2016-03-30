/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workout;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

import jdk.nashorn.internal.runtime.ECMAException;

public class ExerciseCtrl extends DBConn {
    private Exercise exercise; // buffer workout
	

    

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

    public void createExercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){ // ! add arguments !! done
		exercise = new Exercise (name, description, currentGoal, bestResult, weight, repetitions, sets, length, duration); // ! fill in arguments after Exercise class is finished !! done
		exercise.save(conn);
    }
    public void registerExercise(Exercise exercise, Workout workout){ // log entry
    	// connect to database and make new entry in LOGENTRY
    	String sql = "";
    	insert(conn, sql);
    }
    
    
    // BASIC FUNCTIONS
    public void insert(Connection conn, String sql){ // insert sql
    	try {    
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("db error during insert ="+e);
            return;
        }
    }

    protected ArrayList<Exercise> getExercises(String sql){ // execute sql query and get exercises
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
    
    
    
    // SPECIAL SELECTION:
    public Exercise getFastest(String name){
        return getExercises("SELECT MIN(duration) FROM exercise WHERE name = "+ stringify(name) +  ";").get(0);
    }
    
    public ArrayList<Exercise> getAll(){
    	ArrayList<Exercise> list = getExercises("SELECT * FROM exercise;");
    	return list;
    }
    
    public Exercise getExercise(String name){
    	name = name.toLowerCase();
    	Exercise ex = getExercises("SELECT * FROM exercise WHERE name=\'" + name + "\';").get(0);
    	return ex;
    }
    

    // HELPERS
    public String stringify(String str){
    	str = "\"" + str + "\"";
    	return str;
    }
}
