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
    public Workout lastWeekSummary(DateTime time){
        try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs =  stmt.executeQuery("SELECT * FROM Workout WHERE WORKOUTTIME = \""+ name + "\" ;");
            while (rs.next()) {
            	// ! create variables. like in the example under:
            	/*
            	timer = rs.getInt("timer");
            	*/
            }
            return new Workout(); //! add variables in constructor
        } catch (Exception e) {
            System.out.println("db error during select of Workout="+e);
            return null;
        }
    }
    
    public Workout getFastest(String name){
    	// !do like in getHeaviest
    }

}
