package com.example.lab4_2.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Plane {
    public Double A;
    public Double B;
    public Double C;
    public Double D;

    public Plane(String[] list) {
        List<Double> doubleList = Arrays.stream(list).map(Double::parseDouble).toList();
        this.A = doubleList.get(0);
        this.B = doubleList.get(1);
        this.C = doubleList.get(2);
        this.D = doubleList.get(3);

    }

    public Plane(Map<String, String> map) {
        this.A = Double.valueOf(map.get("A"));
        this.B = Double.valueOf(map.get("B"));
        this.C = Double.valueOf(map.get("C"));
        this.D = Double.valueOf(map.get("D"));
    }
}
