/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author stari
 */
public class Seat extends JFXButton{
    private int status; // 0. empty, 1. selecting, 2. booked

    public Seat(int status) {
        this.status = status;
    }
    public Seat() {
        this.status = 0;
        decorateSeat();
    }

    public Seat(int status, String text) {
        super(text);
        this.status = status;
        decorateSeat();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        checkSeatSatus();
    }
    private void decorateSeat(){
        this.setMinSize(50, 50);
        this.setMaxSize(45, 45);
        this.setWrapText(true);
        this.setFont(new Font(12) );
        this.setTextFill(Color.WHITE);
        checkSeatSatus();
        //this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-font-weight: bold"));
        this.setOnMouseClicked(e -> {
            this.status = this.status==0?1:0;
            checkSeatSatus();
        });
    }
    private void checkSeatSatus(){
        switch (status) {
            case 0:
                this.setStyle("-fx-background-color: #3A78C3;-fx-font-weight: bold");
                break;
            case 1:
                this.setStyle("-fx-background-color: #91268F;-fx-font-weight: bold");
                break;
            default:
                this.setStyle("-fx-background-color: #472B34;-fx-font-weight: bold");
                this.setDisable(true);
                break;
        }
    }
}
