package com.scp.CalculatorPlus.constants;

public enum ItemDirection {
    IN("in"),
    OUT("out");

    private String direction;

    private ItemDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
