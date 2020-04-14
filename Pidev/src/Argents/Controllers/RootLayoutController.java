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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
}
