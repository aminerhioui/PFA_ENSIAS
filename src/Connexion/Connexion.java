    package Connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;



public class Connexion {
    
  
    
    
	public Connection Cnx;
        public Statement  St;
    
	public Connexion(){
		try {
	        Class.forName("com.mysql.jdbc.Driver"); 
                Cnx=DriverManager.getConnection("Jdbc:mysql://localhost/mydoctor","root","root");
                St = Cnx.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Database connexion error !"+e.getMessage());
                }
	} 
	
	public String reqUpdate(String req){
		try{
                   // JOptionPane.showMessageDialog(null, req);
                    St.executeUpdate(req);
                    
                   // JOptionPane.showMessageDialog(null,"erreur de selection");
                    return "mise a jour effectuee";
		}catch (SQLException e) {
                    //JOptionPane.showMessageDialog(null,"erreur de mise a jour"+e.getMessage());
			 return "erreur de mise a jour"+e.getMessage();			
		}
	}
       
	public ResultSet reqSelection(String req){
		ResultSet res = null;
		try{
        	res = St.executeQuery(req);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"erreur de mise a jour"+e.getMessage());			
		}
		return res;
	}
       
        
        
	public void deconnection(){
		try{
			St.close();
			Cnx.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"erreur de fermeture de connexion");			
		}
	}
}    
