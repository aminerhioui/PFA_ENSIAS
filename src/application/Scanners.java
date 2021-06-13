/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Connexion.Connexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.PseudoClass;
import org.eclipse.persistence.internal.sessions.ResultSetRecord;

/**
 *
 * @author Zouheir
 */
public class Scanners {

    public Scanners() {
    }
   
    public Scanners(String Type_Scanners, byte[] Image_Scanners, String description, int codePatient) {
        this.Type_Scanners = Type_Scanners;
        this.Image_Scanners = Image_Scanners;
        this.description = description;
        this.codePatient = codePatient;
    }
     public Scanners(int idSC,String Type_Scanners, byte[] Image_Scanners, String description, int codePatient,Date date_Scanners) {
        this.idSC=idSC;
        this.date_Scanners=date_Scanners;
        this.Type_Scanners = Type_Scanners;
        this.Image_Scanners = Image_Scanners;
        this.description = description;
        this.codePatient = codePatient;
    }


    public int getIdSC() {
        return idSC;
    }

    public void setIdSC(int idSC) {
        this.idSC = idSC;
    }

    public String getType_Scanners() {
        return Type_Scanners;
    }

    public void setType_Scanners(String Type_Scanners) {
        this.Type_Scanners = Type_Scanners;
    }

    public byte[] getImage_Scanners() {
        return Image_Scanners;
    }

    public void setImage_Scanners(byte[] Image_Scanners) {
        this.Image_Scanners = Image_Scanners;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(int codePatient) {
        this.codePatient = codePatient;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idSC;
        hash = 83 * hash + Objects.hashCode(this.Type_Scanners);
        hash = 83 * hash + Arrays.hashCode(this.Image_Scanners);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + this.codePatient;
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
        final Scanners other = (Scanners) obj;
        if (this.idSC != other.idSC) {
            return false;
        }
        if (this.codePatient != other.codePatient) {
            return false;
        }
        if (!Objects.equals(this.Type_Scanners, other.Type_Scanners)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Arrays.equals(this.Image_Scanners, other.Image_Scanners)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Scanners{" + "idSC=" + idSC + ", Type_Scanners=" + Type_Scanners + ", Image_Scanners=" + Image_Scanners + ", description=" + description + ", codePatient=" + codePatient + '}';
    }


      public Date getDate_Scanners() {
        return date_Scanners;
    }

    public void setDate_Scanners(Date date_Scanners) {
        this.date_Scanners = date_Scanners;
    }
    public void setScanners(String path) throws SQLException{
        try {
            Connexion connexion = new Connexion();
            
            PreparedStatement ps = null;
            
           ps=connexion.Cnx.prepareStatement("INSERT INTO `mydoctor`.`scanners` (`codePatient`, `date_Scanners`, `Type_Scanners`, `description`, `Image_Scanners`) VALUES (?,now(),?,?,?)");
           ps.setInt(1, codePatient);
         
           ps.setString(2,Type_Scanners);
           ps.setString(3,description);
           if(path != null )
           { File f=new File(path);
           File img=new File(path); 
           FileInputStream istm =new FileInputStream(img);
           ps.setBinaryStream(4, istm,(int)img.length());}
           else{
                ps.setBinaryStream(4,null);}
           
    
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getScanners(int idSC){
       String  req="SELECT * FROM `mydoctor`.`scanners` WHERE `idSC` = "+idSC+" ;";
       Connexion connexion = new Connexion();
        ResultSet rs=connexion.reqSelection(req);
        try {
            while(rs.next()){
        this.idSC = idSC;
        this.Type_Scanners = rs.getString("Type_Scanners");
        this.Image_Scanners = rs.getBytes("Image_Scanners");
        this.date_Scanners =rs.getDate("date_Scanners");
        this.description =rs.getString("description"); 
        this.codePatient = rs.getInt("codePatient");
            }} catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void UPdateScanners(int idSC,String path){
        try {
            Connexion connexion = new Connexion();
            String req ="UPDATE `mydoctor`.`scanners` SET  `date_Scanners`=?, `Type_Scanners`=?, `description`=?, `Image_Scanners`=? WHERE  `idSC`=?;";
            String req2 ="UPDATE `mydoctor`.`scanners` SET  `date_Scanners`=?, `Type_Scanners`=?, `description`=? WHERE  `idSC`=?;";
           if(!path.isEmpty()){
            PreparedStatement ps=connexion.Cnx.prepareStatement(req);
            ps.setDate(1,date_Scanners);
            ps.setString(2,Type_Scanners);
            ps.setString(3,description);
            File img;
            img=new File(path); 
                try {
                     istm =new FileInputStream(img);
                     ps.setBinaryStream(4, istm,(int)img.length());
                } catch (FileNotFoundException ex) {
                    //
               }
                
            ps.setInt(5,idSC);
            ps.executeUpdate(); 
           }
           else{
            PreparedStatement ps=connexion.Cnx.prepareStatement(req2);
            ps.setDate(1,date_Scanners);
            ps.setString(2,Type_Scanners);
            ps.setString(3,description);         
            ps.setInt(4,idSC);

           ps.executeUpdate();
           }
               
            

        } catch (SQLException ex) {
            Logger.getLogger(Scanners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int idSC;
    private String Type_Scanners;
   
    private byte[] Image_Scanners; 
private  FileInputStream istm ;
 
    private Date date_Scanners;
    private String description;
    private int codePatient;

    public Scanners(int idSC, String Type_Scanners, byte[] Image_Scanners, Date date_Scanners, String description, int codePatient) {
        this.idSC = idSC;
        this.Type_Scanners = Type_Scanners;
        this.Image_Scanners = Image_Scanners;
        this.date_Scanners = date_Scanners;
        this.description = description;
        this.codePatient = codePatient;
    }
   
}
