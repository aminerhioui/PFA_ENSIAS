/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author Zouheir
 */

import Connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



public class Consultation  {

    public Integer getCodeConsultation() {
        return codeConsultation;
    }

    public void setCodeConsultation(Integer codeConsultation) {
        this.codeConsultation = codeConsultation;
    }

    public String getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(String dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getResultExam() {
        return resultExam;
    }

    public void setResultExam(String resultExam) {
        this.resultExam = resultExam;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getCodeOrdonnance() {
        return codeOrdonnance;
    }

    public void setCodeOrdonnance(int codeOrdonnance) {
        this.codeOrdonnance = codeOrdonnance;
    }

    public int getCodeRDV() {
        return codeRDV;
    }

    public void setCodeRDV(int codeRDV) {
        this.codeRDV = codeRDV;
    }

    public int getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(int codePatient) {
        this.codePatient = codePatient;
    }

    public Consultation(String dateConsultation, String motif, String resultExam, String diagnostic, int codeOrdonnance, int codeRDV, int codePatient) {
        this.dateConsultation = dateConsultation;
        this.motif = motif;
        this.resultExam = resultExam;
        this.diagnostic = diagnostic;
        this.codeOrdonnance = codeOrdonnance;
        this.codeRDV = codeRDV;
        this.codePatient = codePatient;
    }

    /**
     *
     * @param dateConsultation
     * @param motif
     * @param resultExam
     * @param diagnostic
     * @param codeOrdonnance
     * @param codeRDV
     * @param codePatient
     */
    public Consultation(int codeConsultation,String dateConsultation, String motif, String resultExam, String diagnostic, int codeOrdonnance, int codeRDV, int codePatient) {
        this.codeConsultation=codeConsultation;
        this.dateConsultation = dateConsultation;
        this.motif = motif;
        this.resultExam = resultExam;
        this.diagnostic = diagnostic;
        this.codeOrdonnance = codeOrdonnance;
        this.codeRDV = codeRDV;
        this.codePatient = codePatient;
    }
    private Integer codeConsultation;
    private String dateConsultation;
    private String motif;
    private String resultExam;
    private String diagnostic;
    private int codeOrdonnance;
    private int codeRDV;
    private int codePatient;


    public Consultation() {
    }

    public Consultation(Integer codeConsultation) {
        this.codeConsultation = codeConsultation;
    }

    
    public void setCon(){
        try {
            Connexion connexion = new Connexion();
            
            PreparedStatement ps = null;
            
           ps=connexion.Cnx.prepareStatement("INSERT INTO `mydoctor`.`consultation` (`dateConsultation`, `motif`, `resultExam`, `diagnostic`, `codeRDV`, `codePatient`, `codeOrdonnance`) VALUES (now(),?,?,?,?,?,?);");
          // ps.setString(1,new(dateConsultation);
           ps.setString(1, motif);
           ps.setString(2,resultExam);
           ps.setString(3,diagnostic);
           ps.setInt(4,codeRDV);
           ps.setInt(5,codePatient);
           ps.setInt(6,codeOrdonnance);
           if(ps.executeUpdate()==0) System.out.println("11111222223333333444445555555666666777777788888889999999");;
        } catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getCon(int codeConsultation){
       String  req="SELECT * FROM `mydoctor`.`consultation` WHERE `codeConsultation`= "+codeConsultation+" ;";
       Connexion connexion = new Connexion();
        ResultSet rs=connexion.reqSelection(req);
        try {
        while(rs.next()){
        this.codeConsultation=rs.getInt("codeConsultation");
        this.dateConsultation =rs.getDate("dateConsultation").toString();
        this.motif = rs.getString("motif");
        this.resultExam = rs.getString("resultExam");
        this.diagnostic = rs.getString("diagnostic");
        this.codeOrdonnance = rs.getInt("codeOrdonnance");
        this.codeRDV =rs.getInt("codeRDV");
        this.codePatient =rs.getInt("codePatient");
            }} catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UPdateCon(int codeConsultation){
        try {
            Connexion connexion = new Connexion();
            String req ="UPDATE `mydoctor`.`consultation` SET `dateConsultation`=now(), `motif`=?, `resultExam`=?, `diagnostic`=? WHERE  `codeConsultation`=?;";
            PreparedStatement ps=connexion.Cnx.prepareStatement(req);
            ps.setString(1,motif);
            ps.setString(2,resultExam);
            ps.setString(3,diagnostic);
           
            ps.setInt(4,codeConsultation);
 
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    public ResultSet getConsultation(int idp,int idRDV)
    {
        ajoutConsultation(this);
        ResultSet resSect;
        Connexion connexion = new Connexion();
        resSect = connexion.reqSelection("select * from consultation where codePatient="+idp+" and codeRDV="+idRDV);
        return resSect;
    }

    
    public String ajoutConsultation(Consultation consult)
    {
        Connexion connexion = new Connexion();
        String req ="insert into consultation(dateConsultation,motif,resultExam,diagnostic,codeOrdonnance,codeRDV,codePatient) values('"
                +consult.getDateConsultation()+"','"+consult.getMotif()+"','"+consult.getResultExam()+"','"
                +consult.getDiagnostic()+"','"+consult.getCodeOrdonnance()+"',"
                +consult.getCodeRDV()+","+consult.getCodePatient()+")";
        JOptionPane.showMessageDialog(null,consult.getDateConsultation());
        return connexion.reqUpdate(req);
    }
    
    public void modifierConsultation(Consultation consult,int idRDV)
    {
        Connexion connexion = new Connexion();
       String req ="update consultation set  dateConsultation='"+consult.getDateConsultation()
               +"', motif='"+consult.getMotif()+"', resultExam='"+consult.getResultExam()
               +"', diagnostic='"+consult.getDiagnostic()
               +"' where codeRDV="+idRDV;
        connexion.reqUpdate(req);
    }
    
    public void SupprimerConsultation(int idRDV)
    {
        Connexion connexion = new Connexion();
        String req ="delete from rdv where codeRDV="+idRDV;
        connexion.reqUpdate(req);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeConsultation != null ? codeConsultation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.codeConsultation == null && other.codeConsultation != null) || (this.codeConsultation != null && !this.codeConsultation.equals(other.codeConsultation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modele.consultation_1[ codeConsultation=" + codeConsultation + " ]";
    }
    
}
