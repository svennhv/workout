/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workoutA;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Workout extends ActiveDomainObject {
	int workoutID;
	String name;
	boolean isTemplate;
	Date workoutTime;
	int duration;
	int shape;
	int performance;
	String workoutnote;
	String weatherconditions;
	String airconditions;
	String numberOfSpectators;
	
    // Add all possible values from database
    
    
    public Workout (int workoutID, String name, boolean isTemplate, Date workoutTime, int duration, int shape, 
	int performance, String workoutnote, String weatherconditions, String airconditions, String numberOfSpectators){ // Create new "Workout type" - Takes in all arguments !add more
			this.workoutID = workoutID;
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

    public void regWorkout () { // For log entries
    	// !save all necessary arguments if it is a log Workout (checkout logentry in the workout.sql)
    }
    
    // FOR CONNECTION:
    @Override
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Workout WHERE name=" + name);
            while (rs.next()) {
            	
            	// get values from database like in the example under
            	
            	/* example:
                startTid =  rs.getInt("starttid");
                timer = rs.getInt("timer");
                type = rs.getInt("avtaletype");
                */
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
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(	// ! add all values/variables...  example:
            		//"insert into exercsie values (NULL,"+startTid+","+timer+","+type+")");
        } catch (Exception e) {
            System.out.println("db error during insert of Workout="+e);
            return;
        }
        /* FROM EXAMPLE:
         * not necessary?
        for (int i=0;i<brukere.size();i++) {
            try {    
                Statement stmt = conn.createStatement(); 
                stmt.executeUpdate("insert into HarAvtale values ("+brukere.get(i).getBid()+",LAST_INSERT_ID())");
            } catch (Exception e) {
                System.out.println("db error during insert of HarAvtale="+e);
                return;
            }
        }
        */
    }
}
