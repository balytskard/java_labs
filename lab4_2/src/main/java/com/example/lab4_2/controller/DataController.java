package com.example.lab4_2.controller;

import com.example.lab4_2.dto.Line;
import com.example.lab4_2.dto.Plane;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@RestController
public class DataController {

    @PostMapping(path = "/line", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String value(@RequestBody MultiValueMap<String, String> multiValueMap) {
        if (multiValueMap.getFirst("D").isEmpty()) {
            multiValueMap.set("D", "0");
        }
        if (multiValueMap.toSingleValueMap().containsValue("") || multiValueMap.toSingleValueMap().containsValue(null)) {
            return "Please, enter all values!";
        }
        Line line = new Line(multiValueMap.toSingleValueMap());
        Plane plane = new Plane(multiValueMap.toSingleValueMap());
        String lang = multiValueMap.getFirst("lang");
        return getAnswer(line, plane, lang);
    }


    @PostMapping(path = "/file")
    public String file(@ModelAttribute MultipartFile file) throws IOException {
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".txt")) {
            return "Incorrect file type. Must be .txt";
        }
        if (file.isEmpty()) {
            return "File is empty";
        }
        byte[] bytes = file.getBytes();
        String data = new String(bytes);
//        System.out.println(data);
        data = data.replaceAll("[^0-9.]", " ");
        data = data.replaceAll("\\s+", " ");
        data = data.replaceAll("\\r\\n|\\r|\\n", "");
        String[] list = data.split(" ");
//        System.out.println(Arrays.toString(list));
//        System.out.println(Arrays.stream(Arrays.copyOfRange(list, 0, 6)).toList());
//        System.out.println(Arrays.stream(Arrays.copyOfRange(list, 6, 10)).toList());
        if (list.length < 10) {
            return "Incorrect data in file!";
        }

        Line line = new Line(Arrays.copyOfRange(list, 0, 6));
        Plane plane = new Plane(Arrays.copyOfRange(list, 6, 10));
        return getAnswer(line, plane, "Ukrainian");
    }

    private static String getAnswer(Line line, Plane plane, String lang) {
        if (plane.A * line.a1 + plane.B * line.a2 + plane.C * line.a3 == 0) {
            if (plane.A * line.x0 + plane.B * line.y0 + plane.C * line.z0 + plane.D == 0) {
                return lang.equals("Ukrainian") ? "Пряма належить площині." : "The line belongs to the plane.";
            }
            return lang.equals("Ukrainian") ? "Пряма паралельна площині" : "A line parallel to a plane";
        }
        if (line.a1 / plane.A == line.a2 / plane.B && line.a1 / plane.A == line.a3 / plane.C) {
            return lang.equals("Ukrainian") ? "Пряма перпендикулярна до площини" : "A line is perpendicular to a plane";
        }
        return lang.equals("Ukrainian") ? "Пряма перетинає площину" : "A line crosses a plane";
    }

}
