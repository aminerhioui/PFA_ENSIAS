/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Connexion.Connexion;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Zouheir
 */
public class Ordonnance {

    public Ordonnance(Date dateOrdonnance, String contenuOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
        this.contenuOrdonnance = contenuOrdonnance;
    }

    public int getCodeOrdonnance() {
        return codeOrdonnance;
    }

    public void setCodeOrdonnance(int codeOrdonnance) {
        this.codeOrdonnance = codeOrdonnance;
    }

    public Date getDateOrdonnance() {
        return dateOrdonnance;
    }

    public void setDateOrdonnance(Date dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }

    public String getContenuOrdonnance() {
        return contenuOrdonnance;
    }

    public void setContenuOrdonnance(String contenuOrdonnance) {
        this.contenuOrdonnance = contenuOrdonnance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codeOrdonnance;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ordonnance other = (Ordonnance) obj;
        if (this.codeOrdonnance != other.codeOrdonnance) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ordonnance{" + "codeOrdonnance=" + codeOrdonnance + ", dateOrdonnance=" + dateOrdonnance + ", contenuOrdonnance=" + contenuOrdonnance + '}';
    }


    private int codeOrdonnance;
    private Date dateOrdonnance;
    private String contenuOrdonnance;
    
    public void setOrdonnance(Ordonnance ord)
    {
        Connexion connexion = new Connexion();
        String req="insert into ordonnance(dateOrdonnance,contenuOrdonnance) values(NOW(),"+ord.getContenuOrdonnance()+");";
        
        connexion.reqUpdate(req);
        //JOptionPane.showMessageDialog(null,"or"); 
        connexion.deconnection();
        
    }
  public ResultSet getOrdaonnance(int codeOrd)
    {
        ResultSet resSect;
        Connexion connexion = new Connexion();
        resSect = connexion.reqSelection("select * from ordonnance where="+codeOrd);
        connexion.deconnection();
        return resSect;
    }  
  public ResultSet getAllOrdaonnances()
    {
        ResultSet resSect;
        Connexion connexion = new Connexion();
        resSect = connexion.reqSelection("select * from ordonnance");
        connexion.deconnection();
        return resSect;
    }
   public String modifierrOrdonnance(Ordonnance ordonnance,int codeordonnance)
    {
        Connexion connexion = new Connexion();
        String req ="update ordonnance set contenuOrdonnance="+ordonnance.getContenuOrdonnance()+", dateOrdonnance=NOW()"
               +" where codeRDV="+codeordonnance;
        
        
        
        String S =connexion.reqUpdate(req);
        connexion.deconnection();
        return S;
    }   
   
   public String SupprimerOrdonnance(int codeOrdonnance)
    {
        Connexion connexion = new Connexion();
        
        String req2 ="delete from consultation where codeRDV="+codeOrdonnance;
        String S=connexion.reqUpdate(req2);
        String req1 ="delete from ordonnance where codeRDV="+codeOrdonnance;
        S+=connexion.reqUpdate(req1);
        connexion.deconnection();
        return S;
               
    }
    
    
}
