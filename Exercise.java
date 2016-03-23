/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workoutA;

import java.sql.*;
import java.util.*;

public class Exercise extends ActiveDomainObject {
	String name;
    // Add all possible values from database
    
    
    public Exercise (String name, String duration){ // Create new "exercise type" - Takes in all arguments !add more
    	
    }

    public void regExercise () { // For log entries
    	// !save all necessary arguments if it is a log exercise (checkout logentry in the workout.sql)
    }
    
    // FOR CONNECTION:
    @Override
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM exercise WHERE name=" + name);
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
            		"insert into exercsie values (NULL,"+startTid+","+timer+","+type+")");
        } catch (Exception e) {
            System.out.println("db error during insert of Exercise="+e);
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
