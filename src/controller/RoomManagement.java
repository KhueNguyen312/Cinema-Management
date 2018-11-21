/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.MovieManagementController.autoFitTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Room;
import model.RoomDAO;

/**
 *
 * @author MyPC
 */
public class RoomManagement implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Room> tbRoom;
    @FXML
    private TableColumn<Room, Integer> colSerial;
    @FXML
    private TableColumn<Room, String> colName;
    private RoomDAO rooms;
    ObservableList<Room> list = FXCollections.observableArrayList();
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        rooms = new RoomDAO();
        list =  rooms.getListRoom();
        setValueFactory();
        tbRoom.setItems(list);
        autoFitTable(tbRoom);
    }
    @FXML
    private void BtnNewClick(ActionEvent event) {
        tfID.setText(null);
        tfID.setDisable(true);
        tfName.setText("");
    }

    @FXML
    private void btnSaveClick(ActionEvent event) {
        Room room = new Room();
        int id = 0;
        room.setName(tfName.getText());
        if (tfID.getText() == null) {
            id = list.size() + 1;
            room.setId(id);
            rooms.AddRooms(room);
            list.add(room);
        } else {
            id = Integer.parseInt(tfID.getText());
            room.setId(id);
            rooms.UpdateRooms(room);
            for (Room movie : list) {
                id = movie.getId();
                movie = room;
            }
        }
        rooms = new RoomDAO();
        list = (ObservableList<Room>) rooms.getListRoom();
        setValueFactory();
        tbRoom.setItems(list);
        autoFitTable(tbRoom);
    }

    @FXML
    private void btnDeleteClick(ActionEvent event) {
        Room room = tbRoom.getSelectionModel().getSelectedItem();
        rooms.DeleteRooms(room.getId());
        rooms = new RoomDAO();
        list = (ObservableList<Room>) rooms.getListRoom();
        setValueFactory();
        tbRoom.setItems(list);
        autoFitTable(tbRoom);
    }

    @FXML
    private void TableClick(MouseEvent event) {
        if (MouseButton.PRIMARY == event.getButton() && event.getClickCount() == 1) {
            Room room = tbRoom.getSelectionModel().getSelectedItem();
            tfID.setText(String.valueOf(room.getId()));
            tfName.setText(room.getName());
            int row = tbRoom.getSelectionModel().getSelectedIndex();
        }

    }

    void setValueFactory() {
        colSerial.setCellValueFactory(new PropertyValueFactory<Room, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
    }
}
