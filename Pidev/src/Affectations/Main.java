/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations;

import Affectations.Controllers.RootLayoutController;
import Affectations.Utilitys.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane retrieveView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //definir le window de base pour notre application et lui donner un nom
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Avengers App");
        
        //appeler la vue parent (rootLayout)
        initRootLayout();
        
        //affecter la vue par defaut(retrieveForm) au vue parent
        showRetrieveView();
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void initRootLayout() {
        try {
            //appler le fichier root view file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
 
            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.
 
            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/
 
            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
     //Show retrieve view pardefaut
    public void showRetrieveView() {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Affectations/Views/RetrieveForm.fxml"));
            retrieveView = (AnchorPane) loader.load();
 
            // Set Employee Operations view into the center of root layout.
            rootLayout.setCenter(retrieveView);
        } catch (IOException e) {
            System.out.println("Probleme de chargement de retrieve view"+e);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 
}