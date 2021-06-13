/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Connexion.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Zouheir
 */
public class Medical_record {

    public Medical_record(int idMedecin, int codePatient) {
        try {
            
            this.idMedecin = idMedecin;
            this.codePatient = codePatient;
            String req="SELECT * FROM consultation Where `codePatient`="+this.codePatient+"  and codeRDV IN (SELECT codeRDV FROM rdv WHERE idMedecin="+ this.idMedecin+");";;
           // System.out.println(req);
            Connexion cnx = new Connexion();
            ResultSet rs=cnx.reqSelection(req);
            while(rs.next()){
                Consultation Cons = new Consultation(rs.getInt("codeConsultation"),rs.getString("dateConsultation"),rs.getString("motif"),rs.getString("resultExam"),rs.getString("diagnostic"),rs.getInt("codeOrdonnance"),rs.getInt("codeRDV"),rs.getInt("codePatient"));
                this.List_Consultations.add(Cons);
            }
            String req2="SELECT * from scanners WHERE `codePatient`="+this.codePatient+";";
                        ResultSet rs1=cnx.reqSelection(req2);
                        System.out.println(req);

            while(rs1.next()){ 
                Scanners Scn=new Scanners(rs1.getInt("idSC"),rs1.getString("Type_Scanners"),rs1.getBytes("Image_Scanners"),rs1.getString("description"),rs1.getInt("codePatient"),rs1.getDate("date_Scanners"));
                this.List_Scanners.add(Scn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Medical_record.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getIdMR() {
        return idMR;
    }

    public void setIdMR(int idMR) {
        this.idMR = idMR;
    }

    public ArrayList<Consultation> getList_Consultations() {
        return List_Consultations;
    }

    public void setList_Consultations(ArrayList<Consultation> List_Consultations) {
        this.List_Consultations = List_Consultations;
    }

    public ArrayList<Scanners> getList_Scanners() {
        return List_Scanners;
    }

    public void setList_Scanners(ArrayList<Scanners> List_Scanners) {
        this.List_Scanners = List_Scanners;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public int getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(int codePatient) {
        this.codePatient = codePatient;
    }

    @Override
    public String toString() {
        return "Medical_record{" + "idMR=" + idMR + ", idMedecin=" + idMedecin + ", codePatient=" + codePatient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idMR;
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
        final Medical_record other = (Medical_record) obj;
        if (this.idMR != other.idMR) {
            return false;
        }
        return true;
    }
    private int idMR;
    private ArrayList<Consultation> List_Consultations= new ArrayList();
    private ArrayList<Scanners> List_Scanners= new ArrayList();
    private int idMedecin;
    private int codePatient;
}
