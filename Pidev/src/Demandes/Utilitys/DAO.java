/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.Utilitys;

import Demandes.Entitys.Demande;
import Demandes.Utilitys.DbCnx;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author SIRINE
 */
public class DAO {
    
    private Connection cnx;
    private Statement stm;
    private String query;
    private ResultSet result;
    private List<Demande> demandeList;
   

    public DAO() 
    {
        cnx = DbCnx.getInstance().getConnexion();
        //utiliser notre classe DbCnx pour ouvrir une cnx sur la bdd
    }
    
    public List<Demande> retrieveAllDemande()
    {
        query="select * from demande";
        
        try{                       
        stm = cnx.createStatement();
        result =  stm.executeQuery(query);
        demandeList = new ArrayList<Demande>();
        
        while(result.next()){
            Demande d = new Demande(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5));
            demandeList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection"+ex);
        }
        
        return demandeList;
    }
    
    public List<Demande> retrieveByTagDemande(String tag)
    {
        query="select * from demande where(remarque like '%"+tag+"%' or etat like '%"+tag+"%' or cas like '%"+tag+"%' or date like '%"+tag+"%')";
        
        try{                          
            stm = cnx.createStatement();
            result =  stm.executeQuery(query);
            demandeList = new ArrayList<Demande>();
        
        while(result.next()){
            Demande d = new Demande(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5));
            demandeList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection by tag"+ex);
        }
        
        return demandeList;
    }
    
    public void insertDemande(Demande d)
    {
        query="insert into demande (remarque, etat, cas, date)"
                + "values ('"+d.getRemarque()+"', '"+d.getEtat()+"', '"+d.getCas()+"', '"+d.getDate()+"')";
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme d insertion"+ex);
        }
    }
    
    public void updateDemande(Demande d)
    {
        query="update demande set remarque = '"+d.getRemarque()+"', etat = '"+d.getEtat()+"', cas = '"+d.getCas()+"', date = '"+d.getDate()+"'"
                + "where demande.id = '"+d.getId()+"'";
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme d update"+ex);
        }
    }
    
    public void deleteDemande(int id)
    {
        query = "delete from demande where (id = '"+id+"')";
        
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de delete"+ex);
        }
    }
    
}
