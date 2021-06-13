/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import Connexion.Connexion;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



public class Rdv  {
    private Integer codeRDV;
    private int numRDV;
    private String dateRDV;
    private int codePatient;
    private int idMedecin;

    public Rdv() {
    }

    public Rdv(Integer codeRDV) {
        this.codeRDV = codeRDV;
    }

    public Rdv(int numRDV, String dateRDV, int codePatient, int idMedecin) {
        this.numRDV = numRDV;
        this.dateRDV = dateRDV;
        this.codePatient = codePatient;
        this.idMedecin = idMedecin;
       
    }

    public Integer getCodeRDV() {
        return codeRDV;
    }

    public void setCodeRDV(Integer codeRDV) {
        this.codeRDV = codeRDV;
    }

    public int getNumRDV() {
        return numRDV;
    }

    public void setNumRDV(int numRDV) {
        this.numRDV = numRDV;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public int getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(int codePatient) {
        this.codePatient = codePatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }
    

    
    public ResultSet getlesRDV()
    {
        ResultSet resSect;
        Connexion connexion = new Connexion();
        resSect = connexion.reqSelection("select * from rdv");
        connexion.deconnection();
        return resSect;
    }
	
    public String ajoutRDV(Rdv rdv)
    {
        Connexion connexion = new Connexion();
        String req ="insert into rdv(numRDV,dateRDV,codePatient,idMedecin,) values("
                    +rdv.getNumRDV()+",'"+rdv.getDateRDV()+"',"+rdv.getCodePatient()+","
                    +rdv.getIdMedecin()+")";
        
        return connexion.reqUpdate(req);
    }
    
    public String modifierRDV(Rdv rdv,int idRDV)
    {
        Connexion connexion = new Connexion();
        String req ="update rdv set numRDV="+rdv.getNumRDV()+", dateRDV='"+rdv.getDateRDV()
               +"', codePatient="+rdv.getCodePatient()+", idMedecin="+rdv.getIdMedecin()
               +" where codeRDV="+idRDV;
        
        
         return connexion.reqUpdate(req);
    }
    
    public String SupprimerRDV(int idRDV)
    {
        Connexion connexion = new Connexion();
        String S;
        String req2 ="delete from consultation where codeRDV="+idRDV;
        S=connexion.reqUpdate(req2);
        
        String req1 ="delete from rdv where codeRDV="+idRDV;
        S+=connexion.reqUpdate(req1);
        
        return S;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeRDV != null ? codeRDV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Rdv)) {
            return false;
        }
        Rdv other = (Rdv) object;
        if ((this.codeRDV == null && other.codeRDV != null) || (this.codeRDV != null && !this.codeRDV.equals(other.codeRDV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modele.Rdv_1[ codeRDV=" + codeRDV + " ]";
    }
    
}
