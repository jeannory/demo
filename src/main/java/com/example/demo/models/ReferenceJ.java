package com.example.demo.models;

import com.example.demo.enums.Color;
import com.opencsv.bean.CsvBindByName;

/**
 * json Entity
 * <p>
 * for json mapping
 */
public class ReferenceJ {
    @CsvBindByName
    private String numReference;
    @CsvBindByName
    private Color color;
    @CsvBindByName
    private Float price;
    @CsvBindByName
    private Integer size;

    public ReferenceJ() {
    }

    public String getNumReference() {
        return numReference;
    }

    public void setNumReference(String numReference) {
        this.numReference = numReference;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
