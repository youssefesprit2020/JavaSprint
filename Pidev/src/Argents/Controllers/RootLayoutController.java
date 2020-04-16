/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Argents.Controllers;

import Argents.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
        // TODO
        //afficher le retrieve view
        try {            
            //load CreationView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Argents/Views/DonnationForm.fxml"));
            myView = (AnchorPane) loader.load();
            //affecter la vue dans le root border pane
            rootBorderPane.setCenter(myView);
        }catch (IOException e) {
            System.out.println("Probleme de chargement de donnation view"+e);
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
        alert.setHeaderText("Bonjour Benevole !");
        alert.setContentText("Vous pouvez effectuer des dons d argents.");
        alert.show();
    }    
    
}
