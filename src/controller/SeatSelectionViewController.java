/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.InvoiceDetail;
import model.InvoiceDetailDAO;
import model.Invoices;
import model.InvoicesDAO;
import model.Plan_cinemas;
import model.Product;
import model.ProductDAO;
import model.Tickets;
import model.TicketsDAO;

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
    @FXML
    private Label lbMovieName;
    private TicketsDAO ticketsDAO;
    @FXML
    private JFXButton btnNext;
    @FXML
    private ImageView imgMovie;
    @FXML
    private Label lbDatePlay;
    @FXML
    private Label lbRoom;
    @FXML
    private Label lbCost;
    @FXML
    private Label lbCombo;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbSchedule;
    @FXML
    private VBox pnShowSelectingSeat;
    @FXML
    private AnchorPane pnContent;
    @FXML
    private ScrollPane pnProductContent;
    private ProductDAO productDAO;
    private boolean isLoaded;
    private Plan_cinemas plan;
    private int userID = 0;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lbTheater;
    @FXML
    private AnchorPane pnDetail;
    @FXML
    private AnchorPane pnNotify;
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
        String seatName;
        List<Character> listLetter = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L');
        for(int j = 0;j< row;j++){
        for(int i = 1;i<=column;i++){
            seatName = listLetter.get(j)+String.valueOf(i);
            Seat seat = new Seat(0,seatName);
            seat.setOnAction(e->{ //Select a seat
                if(seat.getStatus()!=1){
                    JFXButton selectingSeat = new JFXButton(seat.getText());
                    selectingSeat.setPrefSize(100, 30);
                    selectingSeat.setFont(new Font(18) );
                    selectingSeat.setTextFill(Color.BLACK);
                    selectingSeat.setStyle("-fx-background-color: #f5f5f5;-fx-font-weight: bold");
                    pnShowSelectingSeat.getChildren().add(selectingSeat);
                    calculateTicketCost(true);
                    
                }
                else{ // Unselect a seat
                    for(int k = 0;k<pnShowSelectingSeat.getChildren().size();k++){
                        if(((JFXButton)pnShowSelectingSeat.getChildren().get(k)).getText() == seat.getText()){
                            pnShowSelectingSeat.getChildren().remove(k);
                            calculateTicketCost(false);
                        }
                    }
                }
            });
            seatPane.getChildren().add(seat);
        }
        pnProductContent.setVisible(false);
        }
    }
    
    public void setInfo(Image img,Plan_cinemas plan){
        imgMovie.setImage(img);
        lbDatePlay.setText(plan.getShow_date().toString());
        lbRoom.setText(String.valueOf(plan.getRoom_id()));
        lbSchedule.setText(plan.getTime_begin().toString());
        this.plan = plan;
    }
    public void setStatusSeat(String movieName,int planID){
         int status = 0; 
         String seatName;
         List<String> listBookedSeat = getListBookedSeat(planID);
         for(int i=0;i<seatPane.getChildren().size();i++){
             seatName = ((Seat)seatPane.getChildren().get(i)).getText();
             status = (listBookedSeat.contains(seatName))?2:0;
             ((Seat)seatPane.getChildren().get(i)).setStatus(status);
         }
         lbMovieName.setText(movieName);
    }
    public List<String> getListBookedSeat(int planID){
        ticketsDAO = new TicketsDAO();
        List<String> listBookedSeat = new ArrayList<>();
        List<Tickets> listTicket = ticketsDAO.getListTicket("SELECT * FROM `tickets` WHERE plan_cinema_id = "+ planID);
        for(Tickets ticket:listTicket){
            listBookedSeat.add(ticket.getSeat());
        }
        return listBookedSeat;
    }
    @FXML
    private void loadPreviousScreen(ActionEvent event) {
        if(pnContent.isVisible()){
            AnchorPane tmp = MainViewController.getInstance().rootPanel;
            tmp.getChildren().remove(2, tmp.getChildren().size());
            tmp.getChildren().get(1).toFront();
        }else{
            pnProductContent.setVisible(false);
            pnContent.setVisible(true);
        }
        
    }

    @FXML
    private void loadProduct(ActionEvent event) {
        if(pnContent.isVisible()){
            pnContent.setVisible(false);
            pnProductContent.setVisible(true);
            if(!isLoaded){
                pnProductContent.setStyle("-fx-background-color: transparent");
                setProductContent();
            }
        }else{
            pnProductContent.setVisible(false);
            pnContent.setVisible(true);
        }
        
    }
    private List<Product> listProduct;
    private void setProductContent(){
        productDAO = new ProductDAO();
        FlowPane pane = new FlowPane();
        pane.setHgap(200);
        pane.setVgap(10);
        pane.setPrefWidth(pnProductContent.getPrefWidth());
        listProduct = productDAO.getListProduct("SELECT * FROM `products`");
        for(Product p:listProduct){
            pane.getChildren().add(createProduct(p));
        }
        pnProductContent.setContent(pane);
        pnProductContent.setPannable(true);
        isLoaded = true;
    }
    private HBox createProduct(Product product){
        HBox parent = new HBox();
        ImageView img = new ImageView(new Image("/Imgs/"+product.getImg()));
        img.setFitHeight(200);
        img.setFitWidth(200);
        
        VBox content = new VBox();
        Label lbName = new Label(product.getName());
        lbName.setWrapText(true);
        lbName.setFont(Font.font("Arial", 22));
        lbName.setStyle("-fx-font-weight: bold");
        lbName.setTextFill(Color.WHITE);
        
        Label lbDes = new Label(product.getDes());
        lbDes.setWrapText(true);
        lbDes.setFont(Font.font("Arial", 20));
        lbDes.setTextFill(Color.WHITE);
        
        HBox quanityContainer = new HBox();
        
        Label lbQty = new Label("0");
        lbQty.setId("quanity");
        lbQty.setWrapText(true);
        lbQty.setFont(Font.font("Arial", 20));
        lbQty.setStyle("-fx-font-weight: bold");
        lbQty.setTextFill(Color.WHITE);
        
        ImageView graphic = new ImageView(new Image("/Imgs/minus.png"));
        graphic.setFitHeight(20);
        graphic.setFitWidth(20);
        JFXButton btnMinus = new JFXButton("",graphic);
        btnMinus.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnMinus.setStyle("-fx-background-color: #f5f5f5");
        btnMinus.setPrefSize(20, 20);
        btnMinus.setOnAction(e->{
            int n = Integer.parseInt(lbQty.getText());
            if(n>0){
                n-=1;
                lbQty.setText(String.valueOf(n));
                calculateProductCost(product, false);
            }
        });
        
        ImageView graphic_1 = new ImageView(new Image("/Imgs/plus.png"));
        graphic_1.setFitHeight(20);
        graphic_1.setFitWidth(20);
        JFXButton btnPlus = new JFXButton("",graphic_1);
        btnPlus.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnPlus.setStyle("-fx-background-color: #f5f5f5");
        btnPlus.setPrefSize(20, 20);
        btnPlus.setOnAction(e->{
            int n = Integer.parseInt(lbQty.getText());
            n+=1;
            lbQty.setText(String.valueOf(n));
            calculateProductCost(product, true);
        });
        
        quanityContainer.getChildren().addAll(btnMinus,lbQty,btnPlus);
        quanityContainer.setSpacing(20);
        quanityContainer.setAlignment(Pos.CENTER_LEFT);
        
        content.getChildren().addAll(lbName,lbDes,quanityContainer);
        content.setSpacing(10);
        content.setPrefSize(350, 400);
        parent.getChildren().addAll(img,content);
        parent.setSpacing(10);
        return parent;
    }
    private void calculateTicketCost(boolean isIncreased){
        String regex = "[^0-9]";
        int ticketCost = Integer.parseInt(lbCost.getText().replaceAll(regex, ""));
        int total = Integer.parseInt(lbTotal.getText().replaceAll(regex, ""));
        if(isIncreased){
            ticketCost += plan.getPrice_ticket();
            total += plan.getPrice_ticket();
        }else{
            ticketCost -= plan.getPrice_ticket();
            total -= plan.getPrice_ticket();
        }
        lbCost.setText(String.valueOf(ticketCost)+" VND");
        lbTotal.setText(String.valueOf(total)+" VND");
    }
    private void calculateProductCost(Product product,boolean isIncreased){
        String regex = "[^0-9]";
        int comboCost = Integer.parseInt(lbCombo.getText().replaceAll(regex, ""));
        int total = Integer.parseInt(lbTotal.getText().replaceAll(regex, ""));
        if(isIncreased){
            comboCost += product.getPrice();
            total += product.getPrice();
        }else{
            comboCost -= product.getPrice();
            total -= product.getPrice();
        }
        lbCombo.setText(String.valueOf(comboCost) +" VND");
        lbTotal.setText(String.valueOf(total)+" VND");
    }

    @FXML
    private void loadPurchaseConfirmation(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
	content.setHeading(new Text("Purchase Confirmation"));
        JFXButton button = new JFXButton("Confirm");
        if(!pnShowSelectingSeat.getChildren().isEmpty()){
            content.setPrefSize(800, 500);
            content.setBody(createModalContent());
        }else{
            content.setPrefSize(500,250);
            Label lbNotify = new Label("Seat not found to book!");
            lbNotify.setWrapText(true);
            lbNotify.setStyle("-fx-font-weight: bold");
            lbNotify.setFont(Font.font("Arial", 20));
            lbNotify.setTextFill(Color.BLACK);
            button.setText("Close");
            content.setBody(lbNotify);
        }
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        JFXDialog confirmModal = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.TOP);
        
        button.setFont(new Font(15) );
        button.setStyle("-fx-background-color: #000000;-fx-font-weight: bold");
        button.setTextFill(Color.WHITE);
        button.setPrefSize(100, 50);
        button.setOnAction(e -> {
            try {
                insertDataToDB(1,userID);
                GeneralFuntion.createNotifyDialog(pnNotify, "Notify", "booking tickets successfully",true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SeatSelectionViewController.class.getName()).log(Level.SEVERE, null, ex);
                GeneralFuntion.createNotifyDialog(pnNotify, "Error", "An error occured! Please try again",false);
            } catch (SQLException ex) {
                Logger.getLogger(SeatSelectionViewController.class.getName()).log(Level.SEVERE, null, ex);
                GeneralFuntion.createNotifyDialog(pnNotify, "Error", "An error occured! Please try again",false);
            }
            confirmModal.close();
        });
        content.setActions(button);
        rootPane.getChildren().add(stackPane);
        GeneralFuntion.FitChildContent(stackPane);
        confirmModal.show();
        confirmModal.setOnDialogClosed(e->{
            rootPane.getChildren().remove(rootPane.getChildren().size()-1);
        });
    }
    private VBox createModalContent(){
        VBox container = new VBox();
        container.setSpacing(15);
        Label lbHeader = new Label("Booking Confirmation");
        lbHeader.setWrapText(true);
        lbHeader.setFont(Font.font("Arial", 30));
        lbHeader.setStyle("-fx-font-weight: bold");
        lbHeader.setTextFill(Color.BLACK);
        HBox hHeader = new HBox(lbHeader);
        hHeader.setAlignment(Pos.CENTER);
        FlowPane movieName = addModalInfo("Movie: ",lbMovieName.getText());
        FlowPane theater = addModalInfo("Theater: ",lbTheater.getText());
        FlowPane datePlay = addModalInfo("Date Play: ",lbDatePlay.getText());
        FlowPane schedule = addModalInfo("Schedule: ",lbSchedule.getText());
        FlowPane room = addModalInfo("Room: ",lbRoom.getText());
        String listSeat = "";
        for(int i = 0;i<pnShowSelectingSeat.getChildren().size();i++){
            listSeat += ((JFXButton)pnShowSelectingSeat.getChildren().get(i)).getText() + " ";
        }
        FlowPane seats = addModalInfo("Selected Seat: ",listSeat);
        container.getChildren().addAll(hHeader,movieName,theater,datePlay,schedule,room,seats,addModalInfo("Product: ", " "));
        try{
            FlowPane pnProduct = ((FlowPane) (pnProductContent.getContent()));
            for (int i = 0; i < pnProduct.getChildren().size(); i++) {
                int quanity = getContentLabelQuanity(i);
                if (quanity > 0) {
                    container.getChildren().add(addModalInfo("       + ", getProductName(i) + ": " + String.valueOf(quanity)));
                }
        }}catch(Exception e){
            
        }
        FlowPane total = addModalInfo("Total: ",lbTotal.getText());
        HBox infoCustomer = new HBox();
        infoCustomer.setSpacing(10);
        Label lbId = new Label("Customer ID: ");
        lbId.setWrapText(true);
        lbId.setFont(Font.font("Arial", 20));
        lbId.setStyle("-fx-font-weight: bold");
        lbId.setTextFill(Color.BLACK);
        JFXTextField tbID = new JFXTextField("0");
        tbID.setPrefWidth(100);
        tbID.setId("tbUserID");
        tbID.textProperty().addListener((obs, oldText, newText) -> {
            String regex = "[^0-9]";
            userID = Integer.parseInt(newText.replaceAll(regex, ""));
        });
        infoCustomer.getChildren().setAll(lbId,tbID);
        container.getChildren().addAll(total,infoCustomer);
        return container;
    }
    private String getProductName(int i){
        String name ="";
        FlowPane pnProduct = ((FlowPane)(pnProductContent.getContent()));
        Node node = pnProduct.getChildren().get(i);
        if(node instanceof HBox){
            Node node_1 = ((HBox) node).getChildren().get(1);
            if(node_1 instanceof VBox){
                Node node_2 = ((VBox) node_1).getChildren().get(0);
                if(node_2 instanceof Label){
                    name = ((Label) node_2).getText();
                }
            }
        }
        return name;
    }
    private int getContentLabelQuanity(int i){
        int quanity = 0;
        FlowPane pnProduct = ((FlowPane)(pnProductContent.getContent()));
        Node node = pnProduct.getChildren().get(i);
        if(node instanceof HBox){
            Node node_1 = ((HBox) node).getChildren().get(1);
            if(node_1 instanceof VBox){
                Node node_2 = ((VBox) node_1).getChildren().get(2);
                if(node_2 instanceof HBox){
                    Node node_3 = ((HBox) node_2).getChildren().get(1);
                    if(node_3 instanceof Label){
                        quanity = Integer.parseInt(((Label) node_3).getText());
                    }
                }
            }
        }
        return quanity;
    }
    
    private FlowPane addModalInfo(String header,String content){
        FlowPane container = new FlowPane();
        container.setVgap(10);
        container.setHgap(10);
        Label lbHeader = new Label(header);
        lbHeader.setWrapText(true);
        lbHeader.setFont(Font.font("Arial", 20));
        lbHeader.setStyle("-fx-font-weight: bold");
        lbHeader.setTextFill(Color.BLACK);
        
        Label lbContent = new Label(content);
        lbContent.setWrapText(true);
        lbContent.setFont(Font.font("Arial", 16));
        lbContent.setTextFill(Color.BLACK);
        
        container.getChildren().addAll(lbHeader,lbContent);
        return container;
    }
    private Product getProductFromName(String name){
        for(Product p:listProduct){
            if(p.getName()==name){
                return p;
            }
        }
        return null;
    }
    private List<InvoiceDetail> createListInvoiceDetail(int invoiceID){
        List<InvoiceDetail> listInvoiceDetail = new ArrayList<>();
        try{
            FlowPane pnProduct = ((FlowPane) (pnProductContent.getContent()));
            for (int i = 0; i < pnProduct.getChildren().size(); i++) {
                int quanity = getContentLabelQuanity(i);
                if (quanity > 0) {
                    //container.getChildren().add(addModalInfo("       + ", getProductName(i) + ": " + String.valueOf(quanity)));
                    Product p = getProductFromName(getProductName(i));
                    listInvoiceDetail.add(new InvoiceDetail(invoiceID,p.getId(),quanity,p.getPrice()*quanity));
                }
        }}catch(Exception e){
            
        }
        return listInvoiceDetail;
    }
    private List<Tickets> createListTicket(int invoiceID){
        List<Tickets> list = new ArrayList<>();
        for(int i = 0;i<pnShowSelectingSeat.getChildren().size();i++){
            list.add(new Tickets(plan.getId(),((JFXButton)pnShowSelectingSeat.getChildren().get(i)).getText(),invoiceID));
        }
        return list;
    }
    private void insertDataToDB(int employeeID,int userID) throws ClassNotFoundException, SQLException{
        String regex = "[^0-9]";
        int total = Integer.parseInt(lbTotal.getText().replaceAll(regex, ""));
        InvoicesDAO invoiceDAO = new InvoicesDAO();
        InvoiceDetailDAO invoiceDetailDAO = new InvoiceDetailDAO();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        
        Invoices invoice = new Invoices(total,sqlDate,employeeID,userID,1);
        int invoiceId = invoiceDAO.createInvoice(invoice);
        
        for(Tickets t:createListTicket(invoiceId)){
            ticketsDAO.createTicket(t);
        }
        if (!createListInvoiceDetail(invoiceId).isEmpty()) {
            for (InvoiceDetail i : createListInvoiceDetail(invoiceId)) {
                invoiceDetailDAO.createInvoiceDetail(i);
            }
        }
        
    }
}
