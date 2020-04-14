/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Controllers;

import Affectations.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SIRINE
 */
public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane rootBorderPane;
    
    private AnchorPane myView;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialisation
    }
    
    @FXML
    public void handleRetrieveView() {
                
      //afficher le retrieve view
        try {            
            //load CreationView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/RetrieveForm.fxml"));
            myView = (AnchorPane) loader.load();
            //affecter la vue dans le root border pane
            rootBorderPane.setCenter(myView);
        }catch (IOException e) {
            System.out.println("Probleme de chargement de retrieve view"+e);
        }  
    }
    
    @FXML    
    public void handleCreateView() {
      
        //afficher le create view
        try {
            //load CreationView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/CreationForm.fxml"));
            myView = (AnchorPane) loader.load();
            //affecter la vue dans le root border pane
            rootBorderPane.setCenter(myView);
            
        }catch (IOException e) {
            System.out.println("Probleme de chargement de create view"+e);
        }
        
    }
    
    @FXML    
    public void handleUpdateView() {
        
     //afficher le update view
        try {
            //load UpdateView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/UpdateForm.fxml"));
            myView = (AnchorPane) loader.load();
            //affecter la vue dans le root border pane
            rootBorderPane.setCenter(myView);
        }catch (IOException e) {
            System.out.println("Probleme de chargement de update view"+e);
        }     
    }
        
    @FXML
    public void handleDeleteView() {
    
    //afficher le delete view
        try {
            //load DeleteView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/DeleteForm.fxml"));
            myView = (AnchorPane) loader.load();
            //affecter la vue dans le root border pane
            rootBorderPane.setCenter(myView);
        }catch (IOException e) {
            System.out.println("Probleme de chargement de delete view"+e);
        }
    }
    
    @FXML
    public void handleExit(ActionEvent actionEvent) {
        
        //soritr de l application
        System.exit(0);
    }
    
    @FXML
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("A Propos");
        alert.setHeaderText("Bonjour Administrateur !");
        alert.setContentText("Vous pouvez consulter, chercher, ajouter, modifier, et supprimer des affectations.");
        alert.show();
    }   
    
}
