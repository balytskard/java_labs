package com.example.lab4_2.dto;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Line {
    public Double x0;
    public Double y0;
    public Double z0;
    public Double a1;
    public Double a2;
    public Double a3;

    public Line(String[] list) {
        List<Double> doubleList = Arrays.stream(list).map(Double::parseDouble).toList();
        this.x0 = doubleList.get(0);
        this.y0 = doubleList.get(1);
        this.z0 = doubleList.get(2);
        this.a1 = doubleList.get(3);
        this.a2 = doubleList.get(4);
        this.a3 = doubleList.get(5);

    }

    public Line(Map<String, String> map) {
        this.x0 = Double.valueOf(map.get("x0"));
        this.y0 = Double.valueOf(map.get("y0"));
        this.z0 = Double.valueOf(map.get("z0"));
        this.a1 = Double.valueOf(map.get("a1"));
        this.a2 = Double.valueOf(map.get("a2"));
        this.a3 = Double.valueOf(map.get("a3"));
    }
}
