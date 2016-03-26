/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package workoutA;

import java.sql.*;
import java.util.*;

public class Exercise extends ActiveDomainObject {
	String name;
	String description;
	String currentGoal;
	String bestResult;
	int weight;
	int repetitions;
	int sets;
	int length;
	int duration;
    // Add all possible values from database
    
    
    public Exercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){ // Create new "exercise type" - Takes in all arguments !add more
    	this.name = name;
		this.description = description;
		this.currentGoal = currentGoal;
		this.bestResult = bestResult;
		this.weight = weight;
		this.repetitions = repetitions;
		this.sets = sets;
		this.length = length;
		this.duration = duration;
    }

    public void reuseExercise (String name, String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration) { // For log entries
    	this.name = name; 
		this.description = description;
		this.currentGoal = currentGoal;
		this.bestResult = bestResult;
		this.weight = weight;
		this.repetitions = repetitions;
		this.sets = sets;
		this.length = length;
		this.duration = duration;
		// !save all necessary arguments if it is a log exercise (checkout logentry in the workout.sql)
    }
    
    @Override
    public String toString(){
    	String str = "Name: " + name + ", description: " + description;
    	return str;
    }
    
    // FOR CONNECTION:
    @Override
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM exercise WHERE name=" + name);
            while (rs.next()) {
				
				name = rs.getString("name");
				description = rs.getString("description");
				currentGoal = rs.getString("currentGoal");
				bestResult = rs.getString("bestResult");
				weight = rs.getInt("weight");
				repetitions = rs.getInt("repetitions");
				sets = rs.getInt("sets");
				length = rs.getInt("length");
				duration = rs.getInt("duration");

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
        	String sql = "INSERT INTO exercise values("+ stringify(name) + ","+ stringify(description) + "," + stringify(currentGoal) + "," + stringify(bestResult) + "," + weight + "," + repetitions + ","
        			+ sets + "," + length + "," + duration + ") ;";
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(sql);

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
    
    // HELPERS
    
    public String stringify(String str){
    	str = "\"" + str + "\"";
    	return str;
    }
}
