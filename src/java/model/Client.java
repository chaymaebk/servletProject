/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author TechAima
 */
public class Client {

    protected int id;
    protected String cin;
    protected String name;
    protected String email;
    protected String location;
    protected String phone;
    protected Timestamp time;

    public Client() {
    }

    public Client(String cin, String name, String email, String location, String phone) {
        this.cin = cin;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
    }

    public Client(int id, String cin, String name, String email, String location, String phone, Timestamp time) {
        this.id = id;
        this.cin = cin;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
