/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Utilitys;

import Affectations.Entitys.Affectation;
import Affectations.Utilitys.DbCnx;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;


public class DAO {
    
    private Connection cnx;
    private Statement stm;
    private String query;
    private ResultSet result;
    private List<Affectation> affectationList;
    
    
    public DAO() 
    {
        cnx = DbCnx.getInstance().getConnexion();
        //utiliser notre classe DbCnx pour ouvrir une cnx sur la bdd
    }
    
    public List<Affectation> retrieveAllAffectation()
    {
        query="select * from affectation";
        
        try{                       
        stm = cnx.createStatement();
        result =  stm.executeQuery(query);
        affectationList = new ArrayList<Affectation>();
        
        while(result.next()){
            Affectation d = new Affectation(result.getInt(1), result.getString(2), result.getDate(3));
            affectationList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection"+ex);
        }
        
        return affectationList;
    }
    
    public List<Affectation> retrieveByTagAffectation(String tag)
    {
        query="select * from affectation where(remarque like '%"+tag+"%' or date like '%"+tag+"%')";
        
        try{                          
            stm = cnx.createStatement();
            result =  stm.executeQuery(query);
            affectationList = new ArrayList<Affectation>();
        
        while(result.next()){
            Affectation d = new Affectation(result.getInt(1), result.getString(2), result.getDate(3));
            affectationList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection by tag"+ex);
        }
        
        return affectationList;
    }
    
    public void insertAffectation(Affectation a)
    {
        query="insert into affectation (remarque, date)"
                + "values ('"+a.getRemarque()+"', '"+a.getDate()+"')";
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme d insertion"+ex);
        }
    }
    
    public void updateAffectation(Affectation a)
    {
        query="update affectation set remarque = '"+a.getRemarque()+"', date = '"+a.getDate()+"'"
                + "where affectation.id = '"+a.getId()+"'";
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme d update"+ex);
        }
    }
    
    public void deleteAffectation(int id)
    {
        query = "delete from affectation where (id = '"+id+"')";
        
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de delete"+ex);
        }
    }
    
}
