package com.example.demo.models;

import com.example.demo.enums.Color;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * xml Entity
 * <p>
 * for xml mapping
 */
public class ReferenceX {

    @JacksonXmlProperty(
            isAttribute = true, namespace = "reference", localName = "name")
    private String numReference;
    @JacksonXmlProperty(
            isAttribute = true, localName = "color")
    private Color color;
    @JacksonXmlProperty(
            isAttribute = true, localName = "price")
    private Float price;
    @JacksonXmlProperty(
            isAttribute = true, localName = "size")
    private Integer size;

    public ReferenceX() {
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
