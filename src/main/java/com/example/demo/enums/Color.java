package com.example.demo.enums;

public enum Color {
    R("red"), G("green"), B("blue");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
