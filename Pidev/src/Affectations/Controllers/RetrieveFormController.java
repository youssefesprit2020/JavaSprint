/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Controllers;

import Affectations.Entitys.Affectation;
import Affectations.Utilitys.DAO;

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
    private ObservableList<Affectation> affectationList;
    
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Affectation> retrieveTableView;

    @FXML
    private TableColumn<Affectation, String>  affectationRemarqueColumn;
    @FXML
    private TableColumn<Affectation, Date> affectationDateColumn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try
        {
        //recuperer la liste des demande dans un ObservableArrayList 
        dao =new DAO();
        affectationList = FXCollections.observableArrayList(dao.retrieveAllAffectation());
        
        // data binding columnView -> ObservableArrayList
        affectationRemarqueColumn.setCellValueFactory(new PropertyValueFactory<Affectation, String>("remarque"));
        affectationDateColumn.setCellValueFactory(new PropertyValueFactory<Affectation, Date>("date"));
    
        //ajouter les colones dans le table view et afficher les resultat
        retrieveTableView.getColumns().addAll(affectationRemarqueColumn, affectationDateColumn);
        retrieveTableView.setItems(affectationList);
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de view intialization"+ex);
        }
    }

    @FXML
    public void handleSearchAffectation (ActionEvent actionEvent)
    {
        try
        {
        //recuperer la liste des demande dans un ObservableArrayList 
        dao =new DAO();
        affectationList = FXCollections.observableArrayList(dao.retrieveByTagAffectation(searchTextField.getText()));        
        
        //afficher les resultat dans le tableaux
        retrieveTableView.setItems(affectationList);
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de recherche"+ex);
        }
    }    
    
}
