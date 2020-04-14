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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SIRINE
 */
public class RetrieveFormController implements Initializable {
    
    private DAO dao;
    private ObservableList<Demande> demandeList;
    
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
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
    
}
