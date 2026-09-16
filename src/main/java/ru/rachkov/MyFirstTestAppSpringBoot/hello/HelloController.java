package ru.rachkov.MyFirstTestAppSpringBoot.hello;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private ArrayList<String> arrayList;
    private HashMap<Integer, String> hashMap;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name",
                        defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/update-array")
    public synchronized void updateArrayList(@RequestParam("s") String s) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(s);
    }

    @GetMapping("/show-array")
    public synchronized ArrayList<String> showArrayList() {
        return arrayList == null ? new ArrayList<>() : new ArrayList<>(arrayList);
    }

    @GetMapping("/update-map")
    public synchronized void updateHashMap(@RequestParam("s") String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(hashMap.size(), s);
    }

    @GetMapping("/show-map")
    public synchronized HashMap<Integer, String> showHashMap() {
        return hashMap == null ? new HashMap<>() : new HashMap<>(hashMap);
    }

    @GetMapping(value = "/show-all-lenght", produces = "text/plain;charset=UTF-8")
    public synchronized String showAllLenght() {
        int arraySize = arrayList == null ? 0 : arrayList.size();
        int mapSize = hashMap == null ? 0 : hashMap.size();
        return "Количество элементов в ArrayList: " + arraySize
                + ", в HashMap: " + mapSize;
    }
}
