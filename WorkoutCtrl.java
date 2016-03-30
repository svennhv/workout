/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workout;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class WorkoutCtrl extends DBConn {
    private Workout workout; // buffer workout
    private ArrayList<Workout> workouts;
    

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
    public void createWorkout (String name, int duration, String workoutnote){ // create new workout template
    	System.out.println("Creating workut");
        workout = new Workout (name, duration, workoutnote);
        workout.save(conn);
    }
    
    // Registering completed workout
    public void registerWorkout(Workout workout, int duration, int shape, int performance, String weatherconditions, String airconditions, String numberOfSpectators, String workoutnote){
    	// save something to logentry in sql??
        workout.regWorkout(duration, shape, performance, workoutnote, weatherconditions, airconditions, numberOfSpectators);
        workout.save(conn);
    }
    
    public void addExercise(Workout workout, Exercise exercise){
    	exercise.getName();
    	System.out.println("inserting: " + exercise.getName() +", " + workout.getId());

    	String sql = "INSERT INTO EXERCISEINWORKOUT (exercisename, workoutid)"
    			+ " VALUES(" + "\'" + exercise.getName()+ "\'" + "," + +workout.getId() + ");";
    	insert(conn, sql);
    }
    // Get exercises in workout
    public ArrayList<Exercise> getExercises(Workout workout, ExerciseCtrl eCtrl){
    	String sql = "SELECT e.*"
    			+ " FROM exercise e"
    			+ " INNER JOIN exerciseinworkout ew "
    			+ " ON e.name = ew.exercisename"
    			+ " WHERE ew.workoutid =\'" + workout.getId() +"\'";//"SELECT exercise FROM exerciseinworkout WHERE workoutid = " + workout.getId(); // FIX THIS  - not correct sql
    	
    	ArrayList<Exercise> list = eCtrl.getExercises(sql);
    	return list;
    }
    /*
    public Workout getWorkout(String name){
		ArrayList<Workout> workouts = getAll();
		for(Workout workout : workouts){
			if (workout.getName().equals(name) ){
				return workout;
			}
			else return new Workout();
		}
    }*/
    
    

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
    	String sql = "SELECT * FROM workout WHERE workouttime >= " + "\'" + this.getWeekStart() +"\'" + " AND workouttime <= " + "\'" +this.getNowString()+ "\'" + "";
    	workouts = getWorkouts(sql);
    	System.out.println("doing sql: " + sql); // debugging
        String summary = "Last weeks workouts: " + workouts.toString();
    	
        return summary;
    }
    public Workout getWorkout(String name){
    	name = name.toUpperCase();
    	String sql = "SELECT * FROM workout WHERE name=\'" + name + "\'";
    	System.out.println("Executing: " + sql);
    	try{
    	return getWorkouts(sql).get(0);
    	}
    	catch (IndexOutOfBoundsException e) {
    	    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
    	}
    	return null;

    }

    // HELPERS
    public String stringify(String str){
    	str = "\"" + str + "\"";
    	return str;
    }
	
	public String getNowString(){
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
		return timeStamp;
	}
	
	public String getWeekStart(){
		long millis = System.currentTimeMillis();
		long lastWeekMillis = millis - 604800000;
		Date date=new Date(lastWeekMillis);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
		return timeStamp;
	}
	
}
