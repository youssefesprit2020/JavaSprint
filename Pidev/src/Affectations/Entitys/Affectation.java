/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.Entitys;

import java.sql.Date;

/**
 *
 * @author SIRINE
 */
public class Affectation {
    
    private int id;
    private String remarque;
    private Date date;

    public Affectation() {
    }
    
    public Affectation(String remarque, Date date) {
        this.remarque = remarque;
        this.date = date;
    }

    public Affectation(int id, String remarque, Date date) {
        this.id = id;
        this.remarque = remarque;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getRemarque() {
        return remarque;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setDate(Date date) {
        this.date = date;
    }
        
    
}
