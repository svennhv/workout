/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workout;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Workout extends ActiveDomainObject {

	private int id;
	private String name;
	private boolean isTemplate;
	private Date workoutTime;
	private String timeString = "NULL";
	private int duration;
	private int shape;
	private int performance;
	private String workoutnote;
	private String weatherconditions;
	private String airconditions;
	private String numberOfSpectators = "0";
	
    // Add all possible values from database
    public Workout (int id, String name, boolean isTemplate, Date workoutTime, int duration, int shape, 
	int performance, String workoutnote, String weatherconditions, String airconditions, String numberOfSpectators){ // Create new "Workout type" - for construction from database
			this.id = id;
			this.name = name;
			this.isTemplate = isTemplate;
			this.workoutTime = workoutTime;
			this.duration = duration;
			this.shape = shape;
			this.performance = performance;
			this.workoutnote = workoutnote;
			this.weatherconditions = weatherconditions;
			this.airconditions = airconditions;
			this.numberOfSpectators = numberOfSpectators;
    }
    
    public Workout (String name, int duration, String workoutnote){ // Create new "Workout type" - for construction by user
			this.name = name;
			this.isTemplate = true;
			this.duration = duration;
			this.workoutnote = workoutnote;
    }

    public void regWorkout ( int duration, int shape, 
	int performance, String workoutnote, String weatherconditions, String airconditions, String numberOfSpectators) { // For log entries
		// !save all necessary arguments if it is a log Workout (checkout logentry in the workout.sql
		this.isTemplate = false;
		this.timeString =  getNowString();
		this.duration = duration;
		this.shape = shape;
		this.performance =  performance;
		this.workoutnote = workoutnote;
		this.weatherconditions =  weatherconditions;
		this.airconditions = airconditions;
		this.numberOfSpectators =  numberOfSpectators;

    }
    
    @Override
    public String toString(){
    	String str = "# Name: " + name + ", duration:" + duration;
    	return str;
    }
    
    
    
    // CONNECTION:
    @Override
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Workout WHERE name=" + name);
            while (rs.next()) {

            	id = rs.getInt("ID");
            	name = rs.getString("name");
            	isTemplate = rs.getBoolean("isTemplate");
            	workoutTime = rs.getDate("workoutTime");
            	duration = rs.getInt("duration");
            	shape = rs.getInt("shape");
            	performance = rs.getInt("performance");
            	workoutnote = rs.getString("workoutnote");
            	weatherconditions = rs.getString("weatherconditions");
            	airconditions = rs.getString("airconditions");
            	numberOfSpectators = rs.getString("numberOfSpectators");
            }

        } catch (Exception e) {
            System.out.println("db error during select of avtale= "+e);
            return;
        }
    
    }
    
    @Override
    public void refresh (Connection conn) {
        initialize (conn);
    }

    @Override
    public void save (Connection conn) {

        try {
        	String sql = "INSERT INTO workout (id, name, istemplate, workouttime, duration, shape, performance, workoutnote, weatherconditions, airconditions, numberofspectators)"
        			+ "values ("+ id + "," + stringify(name) +","+ isTemplate +","
        			+timeString +","+ duration +","+ shape + ","+ performance +","
        			+stringify(workoutnote) +","+ stringify(weatherconditions) +","+ stringify(airconditions) +","
        			+stringify(numberOfSpectators) +");";
        			
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("db error during insert of Exercise="+e);
            return;
        }
    }
    
    // HELPERS
    
    public String stringify(String str){
    	str = "\"" + str + "\"";
    	return str;
    }
	public String getNowString(){
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").format(new java.util.Date());
		return timeStamp;
	}
    
    
    // Getters and setters
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTemplate() {
		return isTemplate;
	}

	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public Date getWorkoutTime() {
		return workoutTime;
	}

	public void setWorkoutTime(Date workoutTime) {
		this.workoutTime = workoutTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

	public String getWorkoutnote() {
		return workoutnote;
	}

	public void setWorkoutnote(String workoutnote) {
		this.workoutnote = workoutnote;
	}

	public String getWeatherconditions() {
		return weatherconditions;
	}

	public void setWeatherconditions(String weatherconditions) {
		this.weatherconditions = weatherconditions;
	}

	public String getAirconditions() {
		return airconditions;
	}

	public void setAirconditions(String airconditions) {
		this.airconditions = airconditions;
	}

	public String getNumberOfSpectators() {
		return numberOfSpectators;
	}

	public void setNumberOfSpectators(String numberOfSpectators) {
		this.numberOfSpectators = numberOfSpectators;
	}
}
