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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SIRINE
 */
public class UpdateFormController implements Initializable {

    private DAO dao;
    private ObservableList<Affectation> affectationList;
    private Affectation affectation;
    
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField remarqueTextField;

    @FXML
    private TextField dateTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button selectButton;
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
    
    @FXML
    public void handleAffectationSelect (ActionEvent actionEvent)
    {
        if(retrieveTableView.getSelectionModel().getSelectedItem() != null) 
        {   
            //activer les textfiels
            remarqueTextField.disableProperty().setValue(Boolean.FALSE);
            dateTextField.disableProperty().setValue(Boolean.FALSE);
            
            //recuperer la ligne séléctioné du tableau
            affectation = retrieveTableView.getSelectionModel().getSelectedItem();
            remarqueTextField.setText(affectation.getRemarque());
            dateTextField.setText(affectation.getDate().toString());
        }
    }
    
    @FXML
    public void handleUpdateAffectation (ActionEvent actionEvent)
    {
        try
        { 
            //la date doit etre valide (aaaa-mm-jj)
            Pattern validDate = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); 
            
            if(validDate.matcher(dateTextField.getText()).matches())
            {
            //persister les donnees dans objet de type affectation
            affectation = new Affectation(retrieveTableView.getSelectionModel().getSelectedItem().getId(), remarqueTextField.getText(), Date.valueOf(dateTextField.getText()));
             
            //modifier cette ligne dans la bdd
            dao = new DAO();
            dao.updateAffectation(affectation);
            
            //afficher les résultat dans le tableaux
            affectationList = FXCollections.observableArrayList(dao.retrieveAllAffectation());                
            retrieveTableView.setItems(affectationList);
            
            //vider les textfields
            remarqueTextField.clear();
            dateTextField.clear();
            
            //desactiver les textfiels
            remarqueTextField.disableProperty().setValue(Boolean.TRUE);
            dateTextField.disableProperty().setValue(Boolean.TRUE);
            
            //un msg confirmation
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setContentText("Affectation modifié");
            alert.show();
            }
            else{
                //un msg d erreur
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setContentText("Veuillez selectioner une affectation");
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
