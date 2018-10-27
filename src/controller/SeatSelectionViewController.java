/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class SeatSelectionViewController implements Initializable {

    @FXML
    private JFXButton btnBack;
    @FXML
    private FlowPane seatPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        seatPane.setHgap(5);
        seatPane.setVgap(5);
        int row = 9;
        int column = 11;
        List<Character> listLetter = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L');
        for(int j = 0;j< row;j++){
        for(int i = 1;i<=column;i++){
            JFXButton seat = createSeat(listLetter.get(j)+String.valueOf(i));
            seatPane.getChildren().add(seat);
        }
        }
    }    

    @FXML
    private void loadPreviousScreen(ActionEvent event) {
        AnchorPane tmp = MainViewController.getInstance().rootPanel;
        tmp.getChildren().remove(2, tmp.getChildren().size());
        tmp.getChildren().get(1).toFront();
        
    }
    private JFXButton createSeat(String seatName){
        JFXButton btnSeat = new JFXButton(seatName);
        btnSeat.setMinSize(44, 40);
        btnSeat.setMaxSize(40, 40);
        btnSeat.setWrapText(true);
        btnSeat.setFont(new Font(12) );
        btnSeat.setTextFill(Color.WHITE);
        btnSeat.setStyle("-fx-background-color: #3A78C3;-fx-font-weight: bold");
        return btnSeat;
    }
            
}
