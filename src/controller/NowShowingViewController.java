/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author stari
 */
public class NowShowingViewController implements Initializable {

    @FXML
    private ScrollPane pnShowInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VBox sampleFilm = CreateInfoFilm("TSUBASA CHORNICLE", "/imgs/avatar_1.jpg");
        VBox sampleFilm2 = CreateInfoFilm("YOUR NAME", "/imgs/YourName.jpg");
        FlowPane container = new FlowPane();
        container.setVgap(20);
        container.setHgap(50);
        container.setPrefWidth(pnShowInfo.getPrefWidth());
        container.getChildren().add(sampleFilm);
        container.getChildren().add(sampleFilm2);
        pnShowInfo.setContent(container);
        pnShowInfo.setPannable(true);
        
    }
    public VBox CreateInfoFilm(String name,String imgUrl){
        VBox InfoPanel = new VBox();
        Image img = new Image(imgUrl);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(250);
        imgView.setFitWidth(200);
        Label label = new Label(name);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", 18));
        InfoPanel.setPrefSize(200, 400);
        InfoPanel.getChildren().addAll(imgView,label);
        return InfoPanel;
    }
    
}
