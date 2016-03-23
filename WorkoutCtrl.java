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
    private Workout Workout;
    

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

    public void createWorkout (){ // ! add arguments
        Workout = new Workout (); // ! fill in arguments after Workout class is finished
 
    }
    public void registerWorkout(){ // log entry, !arguments
        Workout = new Workout (); // ! fill in arguments after Workout class is finished
        Workout.regWorkout(); // ! add arguments
    }
    
    
    // Records and special information:
    public String lastWeekSummary(){  // returns a formatted summa
        String summary = "";
    	try {
        	Date today = new Date();
   	     	Date lastWeek = new Date(today.getTime() - 7*86400000);
   	     	TimeZone tz = TimeZone.getTimeZone("UTC");
   	     	DateFormat df = new SimpleDateFormat("yyyyMMdd");
   	     	df.setTimeZone(tz);
   	     	String lastWeekISO = df.format(lastWeek);
   	     	String sql = "SELECT COUNT(*) AS numberOfWorkouts, SUM(duration) AS timer, AVG(performance) AS Performance, " +
   	         "FROM workout, " + "WHERE date > " + lastWeekISO + ";";  // !check if sql is correct
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery(sql);
            while (rs.next()) {
            	// ! create variables. like in the example under:
            	/*
            	timer = rs.getInt("timer");
            	*/
            	
            	// !add variables to String summary
            }
            return summary; //! add variables in constructor
        } catch (Exception e) {
            System.out.println("db error during select of Workout="+e);
            return null;
        }
    }


}
