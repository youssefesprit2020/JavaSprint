/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.Controllers;

import Demandes.Entitys.Demande;
import Demandes.Utilitys.DAO;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author SIRINE
 */
public class UpdateFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private DAO dao;
    private ObservableList<Demande> demandeList;
    private Demande demande;
    
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField remarqueTextField;
    @FXML
    private TextField etatTextField;
    @FXML
    private TextField casTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button selectButton;
    @FXML
    private TableView<Demande> retrieveTableView;
    @FXML
    private TableColumn<Demande, String>  demandeRemarqueColumn;
    @FXML
    private TableColumn<Demande, String> demandeEtatColumn;
    @FXML
    private TableColumn<Demande, String> demandeCasColumn;
    @FXML
    private TableColumn<Demande, Date> demandeDateColumn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
        //recuperer la liste des demande dans un ObservableArrayList 
        dao =new DAO();
        demandeList = FXCollections.observableArrayList(dao.retrieveAllDemande());
        
        // data binding columnView -> ObservableArrayList
        demandeRemarqueColumn.setCellValueFactory(new PropertyValueFactory<Demande, String>("remarque"));
        demandeEtatColumn.setCellValueFactory(new PropertyValueFactory<Demande, String>("etat"));
        demandeCasColumn.setCellValueFactory(new PropertyValueFactory<Demande, String>("cas"));
        demandeDateColumn.setCellValueFactory(new PropertyValueFactory<Demande, Date>("date"));
    
        //ajouter les colones dans le table view et afficher les resultat
        retrieveTableView.getColumns().addAll(demandeRemarqueColumn, demandeEtatColumn, demandeCasColumn, demandeDateColumn);
        retrieveTableView.setItems(demandeList);
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de view intialization"+ex);
        }
    }

    @FXML
    public void handleSearchDemande (ActionEvent actionEvent)
    {
        try
        {
        //recuperer la liste des demande dans un ObservableArrayList 
        dao =new DAO();
        demandeList = FXCollections.observableArrayList(dao.retrieveByTagDemande(searchTextField.getText()));        
        
        //afficher les resultat dans le tableaux
        retrieveTableView.setItems(demandeList);
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de recherche"+ex);
        }
    }  
   
    @FXML
    private void handleDemandeSelect (ActionEvent actionEvent)
    {
        if(retrieveTableView.getSelectionModel().getSelectedItem() != null) 
        {   
            //activer les textfiels
            remarqueTextField.disableProperty().setValue(Boolean.FALSE);
            etatTextField.disableProperty().setValue(Boolean.FALSE);
            casTextField.disableProperty().setValue(Boolean.FALSE);
            dateTextField.disableProperty().setValue(Boolean.FALSE);
            
            //recuperer la ligne séléctioné du tableau
            demande = retrieveTableView.getSelectionModel().getSelectedItem();
            remarqueTextField.setText(demande.getRemarque());
            etatTextField.setText(demande.getEtat());
            casTextField.setText(demande.getCas());
            dateTextField.setText(demande.getDate().toString());
        }
    }
        
    @FXML
    public void handleUpdateDemande (ActionEvent actionEvent)
    {
        try
        { 
            //la date doit etre valide (aaaa-mm-jj)
            Pattern validDate = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); 
            
            if(validDate.matcher(dateTextField.getText()).matches())
            {
            //persister les données dans un objet de type Demande
            demande = new Demande(retrieveTableView.getSelectionModel().getSelectedItem().getId(), remarqueTextField.getText(), etatTextField.getText(), casTextField.getText(), Date.valueOf(dateTextField.getText()));
            
            //modifier cette ligne dans la bdd
            dao = new DAO();
            dao.updateDemande(demande);
            
            //afficher les résultat dans le tableaux
            demandeList = FXCollections.observableArrayList(dao.retrieveAllDemande());                
            retrieveTableView.setItems(demandeList);
            
            //vider les textfields
            remarqueTextField.clear();
            etatTextField.clear();
            casTextField.clear();
            dateTextField.clear();
            
            //desactiver les textfiels
            remarqueTextField.disableProperty().setValue(Boolean.TRUE);
            etatTextField.disableProperty().setValue(Boolean.TRUE);
            casTextField.disableProperty().setValue(Boolean.TRUE);
            dateTextField.disableProperty().setValue(Boolean.TRUE);
            
            //un msg confirmation
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setContentText("Demande modifié");
            alert.show();
            }
            else{
                //un msg d erreur
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setContentText("Veuillez selectioner une demande");
            alert.show();
            }
            
        }catch(Exception e){
            //un msg d erreur
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setContentText("Erreur de modification");
            alert.show();
            System.out.println("Erreur de modification"+e);
        }
    }
    
}
