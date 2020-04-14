/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Argents.Utilitys;

import Argents.Entitys.Argent;
import Argents.Utilitys.DbCnx;
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
    private List<Argent> argentList;
    
    
    public DAO() 
    {
        cnx = DbCnx.getInstance().getConnexion();
        //utiliser notre classe DbCnx pour ouvrir une cnx sur la bdd
    }
    
    public List<Argent> retrieveAllArgent()
    {
        query="select * from argent";
        
        try{                       
        stm = cnx.createStatement();
        result =  stm.executeQuery(query);
        argentList = new ArrayList<Argent>();
        
        while(result.next()){
            Argent d = new Argent(result.getInt(1), result.getFloat(2), result.getDate(3));
            argentList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection"+ex);
        }
        
        return argentList;
    }
    
    public List<Argent> retrieveByTagArgent(String tag)
    {
        query="select * from argent where(montant like '%"+tag+"%' or date like '%"+tag+"%')";
        
        try{                          
            stm = cnx.createStatement();
            result =  stm.executeQuery(query);
            argentList = new ArrayList<Argent>();
        
        while(result.next()){
            Argent d = new Argent(result.getInt(1), result.getFloat(2), result.getDate(3));
            argentList.add(d);
        }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection by tag"+ex);
        }
        
        return argentList;
    }
    
    public void insertArgent(Argent a)
    {
        query="insert into argent (montant, date)"
                + "values ('"+a.getMontant()+"', '"+a.getDate()+"')";
        try{
                       
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme d insertion"+ex);
        }
    }

}
