/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class LoginViewController implements Initializable {

    @FXML
    private JFXTextField tbUserName;
    @FXML
    private JFXPasswordField passwordBox;
    @FXML
    private JFXButton btnLogIn;
    @FXML
    private Label lbMessange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (tbUserName.getText().equals("Syaoran") && passwordBox.getText().equals("1")){
         ((Node)event.getSource()).getScene().getWindow().hide();
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
         Parent parent = fxmlLoader.load();
         MainViewController main = fxmlLoader.<MainViewController>getController();
         main.SetUser(tbUserName.getText());
         Stage stage = new Stage();
         Scene scene = new Scene(parent);
         stage.setScene(scene);
         stage.setTitle("Cinema Management");
         stage.show();
        }
        else{
            lbMessange.setText("Username or Password invalid");
        }
    }
    
}
