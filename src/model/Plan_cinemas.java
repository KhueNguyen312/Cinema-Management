/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author stari
 */
public class Plan_cinemas {
    private int id;
    private Date show_date;
    private Time time_begin;
    private int price_ticket;
    private int movie_id;
    private int room_id;
    private int type_projector_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getShow_date() {
        return show_date;
    }

    public void setShow_date(Date show_date) {
        this.show_date = show_date;
    }

    public Time getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(Time time_begin) {
        this.time_begin = time_begin;
    }

    public int getPrice_ticket() {
        return price_ticket;
    }

    public void setPrice_ticket(int price_ticket) {
        this.price_ticket = price_ticket;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getType_projector_id() {
        return type_projector_id;
    }

    public void setType_projector_id(int type_projector_id) {
        this.type_projector_id = type_projector_id;
    }
    
}
