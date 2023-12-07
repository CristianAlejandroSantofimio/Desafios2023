package main.java.paquetes_java.dia6;

import main.util.LectorArchivos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Dia6 {
    public static void main(String[] args) {

        var input = LectorArchivos.getArchivo("dia6Archivo.txt");
        System.out.println(input);
        /* part 1
        var times = getIntegers(input.get(0));
        var distances = getIntegers(input.get(1));

        var results = getRaceResults(times, distances);
        var answer = results.values().stream().reduce(1, (accu, elem) -> accu * elem);
        */
        var time = getAsLong(input.get(0));
        var distance = getAsLong(input.get(1));


        System.out.println("The result of the challenge is: " + getRaceResult(time, distance));



    }
    private static HashMap<Integer, Integer> getRaceResults(List<Integer> times, List<Integer> distances) {
        var results =  new HashMap<Integer, Integer>();
        for (int index = 0; index < times.size(); index++){
            var time = times.get(index);
            var validResults = 0;
            for (int speed = 1; speed < time; speed++) {
                var timeLeft = time - speed;
                var traveledDistance = speed * timeLeft;
                if (traveledDistance > distances.get(index)){
                    validResults++;
                }
            }
            results.put(index, validResults);
        }
        return results;
    }

    private static Integer getRaceResult(Long time, Long distance) {
        var validResults = 0;
        for (int speed = 1; speed < time; speed++) {
            var timeLeft = time - speed;
            var traveledDistance = speed * timeLeft;
            if (traveledDistance > distance){
                validResults++;
            }
        }
        return validResults;
    }


    private static List<Integer> getIntegers(String line) {
        return Arrays.stream(line.split(":")[1].trim().split(" "))
                .filter(it -> !it.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Long getAsLong(String line) {
        return Long.parseLong(Arrays.stream(line.split(":")[1].trim().split(" "))
                .filter(it -> !it.isBlank())
                .reduce("", String::concat));
    }



}
