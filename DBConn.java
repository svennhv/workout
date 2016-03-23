/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package workoutA;

/**
 *
 * @author Svein Erik
 */

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
    protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "");           
            conn = DriverManager.getConnection("jdbc:mysql://localhost/workout",p);
            System.out.println("Connected..");
        } catch (Exception e)
    	{

    	}
    }
}
