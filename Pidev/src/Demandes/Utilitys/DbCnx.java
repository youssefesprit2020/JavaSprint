/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.Utilitys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SIRINE
 */
public class DbCnx {
    
    private static DbCnx MaDbCnx;
    //Dans cette classe on va implémenter le design pattren Singleton
    //Pour limiter l'acces au serveur de données et améliorer le performances de notre App
    //cet attribut static nous permet d utiliser les méthodes static sans instncier la classe
    
    private Connection connexion;
    //le type Connection est un type prédefinie de jdbc
    
    private String url = "JDBC:mysql://localhost:3306/dev";
    private String user = "root";
    private String password = "";

    private DbCnx() 
    {
        //un constructeur private pour limiter l'instanciation de cette classe
        
        try {
            
            connexion = DriverManager.getConnection(url, user, password);
            //une fois une instance de cette classe est créer
            //on instancie avec elle une connxion sur la bdd
            
        } catch (SQLException ex) {
            
            System.out.println("Probleme de connexion DbCnx"+ex);
        }
    }
    
    public static DbCnx getInstance() {
        
        //une méthode static utilisable par l attribut static de cette classe
        
        if (DbCnx.MaDbCnx == null) {            
            //si l objet MaDbCnx n est pas instancié .. alors on l'instancie
            
            DbCnx.MaDbCnx = new DbCnx();
        }
        
        //cette méthode retourne un objet MaDbCnx de type DbCnx
        //cette objet contient une seule cnx sur la bdd(definie dans le constructeur)
        return DbCnx.MaDbCnx;
    }

    public Connection getConnexion() {
        return this.connexion;
        
        //une méthode d instance
        //après instanciation d un objet de cette classe
        //on aura l attribut connexion instancié (dans le constructeur)
        //cette methode return cet attribut connexion rempli
    }
    
    
}
