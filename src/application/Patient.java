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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;


public class Patient {

    public Integer getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(Integer codePatient) {
        this.codePatient = codePatient;
    }

    public String getPrenomPatient() {
        return prenomPatient;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public Integer getTelPatient() {
        return telPatient;
    }

    public void setTelPatient(int telPatient) {
        this.telPatient = telPatient;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getAdressePatient() {
        return adressePatient;
    }

    public void setAdressePatient(String adressePatient) {
        this.adressePatient = adressePatient;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public Patient(String prenomPatient, String nomPatient, String login, String password, int Age, int telPatient, String Sexe, String adressePatient, byte[] Image) {
        this.prenomPatient = prenomPatient;
        this.nomPatient = nomPatient;
        this.login = login;
        try {
            this.password = Function.CredAndPass(password);
        } catch (Exception ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Age = Age;
        this.telPatient = telPatient;
        this.Sexe = Sexe;
        this.adressePatient = adressePatient;
        this.Image = Image;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getGsang() {
        String[] T=Gsang.split("|");
        
        return T[0]+"  "+T[2];
    }

    public void setGsang(String Gsang) {
        this.Gsang = Gsang;
    }

    public Patient( String prenomPatient, String nomPatient, String login, String password, String CIN, String Email, String Gsang, int Age, int telPatient, String Sexe, String adressePatient, byte[] Image) {
       
        this.prenomPatient = prenomPatient;
        this.nomPatient = nomPatient;
        this.login = login;
        try {
            this.password = Function.CredAndPass(password);
        } catch (Exception ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.CIN = CIN;
        this.Email = Email;
        this.Gsang = Gsang;
        this.Age = Age;
        this.telPatient = telPatient;
        this.Sexe = Sexe;
        this.adressePatient = adressePatient;
        this.Image = Image;
    }

     public Patient( String prenomPatient, String nomPatient, String login, String password, String CIN, String Email, String Gsang, int Age, int telPatient, String Sexe, String adressePatient, String Path_image) throws FileNotFoundException {
       
        try {
            this.prenomPatient = prenomPatient;
            this.nomPatient = nomPatient;
            this.login = login;
            try {
                this.password = Function.CredAndPass(password);
            } catch (Exception ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.CIN = CIN;
            this.Email = Email;
            this.Gsang = Gsang;
            this.Age = Age;
            this.telPatient = telPatient;
            this.Sexe = Sexe;
            this.adressePatient = adressePatient;
            Connexion connexion = new Connexion();
            PreparedStatement ps = null;
            ps = connexion.Cnx.prepareStatement("insert into patient(nomPatient,login,password,prenomPatient,Age,telPatient,Sexe,adressePatient,image,CIN,Email,G_sang) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, nomPatient);
            ps.setString(2, login);
            ps.setString(3, this.password);
            ps.setString(4, prenomPatient);
            ps.setInt(5, Age);
            ps.setInt(6,telPatient);
            ps.setString(7, Sexe);
            ps.setString(8, adressePatient);
            File img=new File(Path_image); 
            FileInputStream istm =new FileInputStream(img);
            ps.setBinaryStream(9, istm,(int)img.length());

            ps.setString(10, CIN);
            ps.setString(11, Email);
            ps.setString(12,  Gsang);
            ps.executeUpdate();
            System.out.println("tout est bein");
            
           msg="mise a jour effectuee";

            //this.Image = Image;
        } catch (SQLException ex) {
           msg="erreur de mise a jour"+ex.getMessage();
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    //


    public String getMsg() {
        return msg;
    }
     String msg;
///
    private Integer codePatient;
    private String prenomPatient;
    private String nomPatient;
    private String login;
    private String password;
    private String CIN;
    private String Email;
    private String Gsang;
    private Integer Age;
    private Integer telPatient;
    private String Sexe;
    private String adressePatient;
    byte[] Image;
    

    public Patient() {
    }

    public Patient(Integer codePatient) {
        this.codePatient = codePatient;
    }




  
    public void getPatientbyid(int idp)
    {
        try {
            ResultSet resSect;
            Connexion connexion = new Connexion();
            resSect = connexion.reqSelection("select * from patient where codePatient="+idp+";");
            if(resSect.next()){
            this.codePatient=resSect.getInt("codePatient");
            this.prenomPatient =resSect.getString("prenomPatient");
            this.nomPatient =resSect.getString("nomPatient");
            this.login = resSect.getString("login");
            this.password =resSect.getString("password");
            this.Age = resSect.getInt("Age");
            this.telPatient =resSect.getInt("telPatient");
            this.Sexe = resSect.getString("Sexe");
            this.adressePatient = resSect.getString("adressePatient");;
            this.Image = resSect.getBytes("image");
            this.CIN = resSect.getString("CIN");
            this.Email =resSect.getString("Email");
            this.Gsang = resSect.getString("G_sang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public boolean getPatientbyUsernme(String login,String password) throws SQLException
    {
     
        boolean res=false;
        ResultSet resSect = null;
     
            
          Connexion connexion = new Connexion();
          resSect = connexion.reqSelection("select * from patient where login='"+login+"' and `password`=md5('"+password+"');");
          System.out.println("select * from patient where login='"+login+"' and `password`=md5('"+password+"');");
        if(resSect.next()){
               this.codePatient=resSect.getInt("codePatient");
               this.prenomPatient =resSect.getString("prenomPatient");
               this.nomPatient =resSect.getString("nomPatient");
               this.login = resSect.getString("login");
               this.password =resSect.getString("password");
               this.Age = resSect.getInt("Age");
               this.telPatient =resSect.getInt("telPatient");
               this.Sexe = resSect.getString("Sexe");
               this.adressePatient = resSect.getString("adressePatient");;
               this.Image = resSect.getBytes("image");
               this.CIN = resSect.getString("CIN");
               this.Email =resSect.getString("Email"); 
               this.Gsang = resSect.getString("G_sang");
                   res=true;  
        
        }else{
         res=false;     
        }
          
                
        
        return res;
    }
    public ResultSet getlesPatient()
    {
        ResultSet resSect;
        Connexion connexion = new Connexion();
        resSect = connexion.reqSelection("select * from patient");
        connexion.deconnection();
        return resSect;
    }
	
    public  String ajoutPatient(Patient patient)
    {
        Connexion connexion = new Connexion();
            String req ="insert into patient(codePatient,nomPatient,login,password,prenomPatient,Age,telPatient,Sexe,adressePatient,image,CIN,Email,G_sang) values(NULL,'"+patient.getNomPatient()+"','"+patient.getLogin()+"','"+patient.getPassword()+"','"+patient.getPrenomPatient()+"','"+patient.getAge()+"','"+patient.getTelPatient()+"','"+patient.getSexe()+"','"+patient.getAdressePatient()+"','"+patient.getImage()+"','"+patient.getCIN()+"','"+patient.getEmail()+"','"+patient.getGsang()+"');";
            System.out.println(req);
            return connexion.reqUpdate(req);
        }
    
    public String modifierPatient(Patient patient,int idp)
    {
        Connexion connexion = new Connexion();
        String req ="update patient set "
                + "nomPatient='"+patient.getNomPatient()+
                "' , prenomPatient='" +patient.getPrenomPatient()+
                 "' , login='"+patient.getLogin()+
                 "' , password='"+patient.getPassword()+
                "' , Age='"+patient.getAge()+
                "' ,telPatient="  +patient.getTelPatient()+
                " , Sexe='"+patient.getSexe()+
                " , adressePatient='"+patient.getAdressePatient()+
                "' , image=" +patient.getImage()+
                "' , CIN=" +patient.getCIN()+""+
                        "' , G_sang=" +patient.getGsang()+
                                "' , Email=" +patient.getEmail()+""
               + " where codePatient="+idp;
        return connexion.reqUpdate(req);
    }
    
    public String SupprimerPatient(int idp)
    {
        Connexion connexion = new Connexion();
        String req ="delete from patient where codePatient="+idp;
        return connexion.reqUpdate(req);
    }
    
    
    public Vector<Patient> find(String choix, String val)throws SQLException{
       // seach by first nom or last nom 
        
        String req;
         Vector v=new Vector();
          Connexion connexion = new Connexion();
        try {
         req="SELECT * from patient where "+choix+"Patient"+"="+val+"";   
        
        
     
            
        
        
        ResultSet rs=connexion.St.executeQuery(req);
        while(rs.next()){
            Vector vi=new Vector();
            vi.add(rs.getString("nomPatient"));
            vi.add(rs.getString("prenomPatient"));
            vi.add(rs.getString("Age"));
            vi.add(rs.getString("telPatient"));
            vi.add(rs.getString("CIN"));
            vi.add(rs.getString("login"));
            vi.add(rs.getString("password"));
            vi.add(rs.getString("Email"));
            vi.add(rs.getString("codePatient"));
            vi.add(rs.getString("image"));
            vi.add(rs.getString("G_sang"));
            vi.add(rs.getString("Sexe"));
            vi.add(rs.getString("adressePatient"));
            v.add(vi);
            
        }
        
        
           
        } catch (Exception e){
            e.getMessage();
        }
        return v;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePatient != null ? codePatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.codePatient == null && other.codePatient != null) || (this.codePatient != null && !this.codePatient.equals(other.codePatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modele.patient[ codePatient=" + codePatient + " ]";
    }
    
}
