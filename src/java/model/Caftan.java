/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author TechAima
 */
public class Caftan {

    private int itemId;
    private String itemType;
    private String description;
    private BigDecimal price;
    private String characteristics;
    private String availability;
    private String image;

    public Caftan(String itemType, String description, BigDecimal price, String characteristics, String availability, String image) {
        this.itemType = itemType;
        this.description = description;
        this.price = price;
        this.characteristics = characteristics;
        this.availability = availability;
        this.image = image;
    }

    public Caftan() {
    }

    public Caftan(int itemId, String itemType, String description, BigDecimal price, String characteristics, String availability, String image) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.description = description;
        this.price = price;
        this.characteristics = characteristics;
        this.availability = availability;
        this.image = image;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public String getAvailability() {
        return availability;
    }

    public String getImage() {
        return image;
    }

}
