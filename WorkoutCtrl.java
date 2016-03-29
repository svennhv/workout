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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class WorkoutCtrl extends DBConn {
    private Workout workout; // buffer workout
    //private ArrayList<Workout> workouts;
    

    public WorkoutCtrl () {
        connect();
        // Let creation of workout be a transaction
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAvtaleCtrl="+e);
            return;
        }
    }

    // For templates:
    public void createWorkout (String name, int duration, String workoutnote){
    	System.out.println("Creating workut");
        workout = new Workout (name, true, null, duration, 0, 0, workoutnote, "", "", "");
        workout.save(conn);
    }
    
    // Registering completed workout
    public void registerWorkout(Workout workout, int duration, int shape, int performance, String weatherconditions, String airconditions, String numberOfSpectators, String workoutnote){ // log entry, !arguments
    	Date now = new Date();
    	workout = new Workout(workout.getName(), false, now, duration, shape, performance, workoutnote, weatherconditions, airconditions, numberOfSpectators);
        workout.regWorkout(); // ! add arguments
    }
    
    public void addExercise(Workout workout, Exercise exercise){
    	exercise.getName();
    	String sql = "INSERT INTO exerciseinworkout (" + stringify(exercise.getName()) + "," + workout.getId() + ")";
    	insert(conn, sql);
    }
    
    public ArrayList<Exercise> getExercises(Workout workout, ExerciseCtrl eCtrl){
    	String sql = "SELECT * FROM exerciseinworkout WHERE workoutid = " + workout.getId(); // FIX THIS  - not correct sql
    	ArrayList<Exercise> list = eCtrl.getExercises(sql);
    	
 
    	return list;
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
    private ArrayList<Workout> getWorkouts(String sql){ // execute sql query and get exercises
    	ArrayList<Workout> list = new ArrayList<Workout>();
    	try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery(sql);
            while (rs.next()) {
            	int id = rs.getInt("id");
               	String name = rs.getString("name");
            	Boolean isTemplate = rs.getBoolean("isTemplate");
            	Date workoutTime = rs.getDate("workoutTime");
            	int duration = rs.getInt("duration");
            	int shape = rs.getInt("shape");
            	int performance = rs.getInt("performance");
            	String workoutnote = rs.getString("workoutnote");
            	String weatherconditions = rs.getString("weatherconditions");
            	String airconditions = rs.getString("airconditions");
            	String numberOfSpectators = rs.getString("numberOfSpectators");
				
	            list.add( new Workout(id, name, isTemplate, workoutTime, duration, shape, 
	            		performance, workoutnote, weatherconditions, airconditions, numberOfSpectators) ); //! add variables in constructor   	
            }
            return list;
        } catch (Exception e) {
            System.out.println("db error during select of Exercise="+e);
            return null;
        }
    }
    
    
    // SPECIAL SELECTION:
    public ArrayList<Workout> getAll(){
    	String sql = "SELECT * FROM workout";
    	ArrayList<Workout> list = getWorkouts(sql);
    	return list;
    }
    public Workout getLast(){ // returns newest workout
    	String sql = "SELECT MAX(workoutTime) FROM workout";
    	return getWorkouts(sql).get(0);
    }
    public String lastWeekSummary(){  // returns a formatted summary
    	String sql = "SELECT * FROM workout";
    	workouts = getWorkouts(sql);
        String summary = "";
    	
        return summary; //! add variables in constructor
    }

    // HELPERS
    public String stringify(String str){
    	str = "\"" + str + "\"";
    	return str;
    }

}
