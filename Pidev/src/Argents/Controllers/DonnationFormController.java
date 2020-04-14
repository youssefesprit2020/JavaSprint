/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Argents.Controllers;

import Argents.Utilitys.DAO;
import Argents.Utilitys.DbCnx;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import com.stripe.param.PaymentIntentCreateParams;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DonnationFormController implements Initializable {
    
    private DAO dao;
    @FXML
    private TextField donateTextField;
    @FXML
    private Button donateButton;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try
        {
        //initialiser la cnx a la bdd
        dao =new DAO();
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de view intialization"+ex);
        }
    }
    
    @FXML
    public void handleDonateArgent() throws StripeException
    {
        try{
            
                Stripe.apiKey = "sk_test_WhRxlL2CKVEMphc4DT0v1SLw00WXkFlMym";
            
                //créer un don
                Map<String, Object> donParams = new HashMap<>();
                donParams.put("amount", parseInt(donateTextField.getText()));
                donParams.put("currency", "usd");
                donParams.put("source", "tok_mastercard");
                donParams.put(
                    "description",
                    "Avengers App Donation"
                    );

                Charge charge = Charge.create(donParams);
            
                //un msg confirmation
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setContentText("Merci pour votre don");
                alert.show();
            
                //vider les champs
                donateTextField.clear();
            
            }catch(Exception ex){
                //un msg d erreur
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setContentText("Verifiez le montant"+ex);
                alert.show();
            }       
    }
    
}
