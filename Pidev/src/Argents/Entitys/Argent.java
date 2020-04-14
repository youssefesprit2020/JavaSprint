/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Argents.Entitys;

import java.sql.Date;

/**
 *
 * @author SIRINE
 */
public class Argent {
    
    private int id;
    private float montant;
    private Date date;

    public Argent() {
    }

    public Argent(int id, float montant, Date date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }
    
    public Argent(float montant, Date date) {
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public float getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setDate(Date date) {
        this.date = date;
    }
       
}
