package main.java.paquetes_java.dia2;

import main.util.LectorArchivos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Dia2 {
    public static void main(String arg[]) {

        var Dia2 = new Dia2();
        var input = LectorArchivos.getArchivo("dia2Archivo.txt");
        Logger.getAnonymousLogger().info("The sum of all possible games is: " + Dia2.findSolution2(input));
    }

    private int findSolution2(List<String> games) {
        var sumOfPossibleGames = 0;
        for (var game : games) {
            var gameComponents = game.split(":"); // ["Game 1"]["1 blue; 2 red, 6 green"]
            var handfuls = gameComponents[1].split(";"); // ["1 blue"] ["2 red, 6 green"]

            sumOfPossibleGames += getPowerOfGame(handfuls);
        }

        return sumOfPossibleGames;
    }

    private int getPowerOfGame(String[] handfuls) {
        var blueCubes = new ArrayList<Integer>();
        var redCubes = new ArrayList<Integer>();
        var greenCubes = new ArrayList<Integer>();

        for (var handful : handfuls) {
            var cubes = handful.split(","); // ["2 red"]  ["6 green"]
            for (var cube : cubes) {
                var cubeDetails = cube.trim().split(" ");
                var cubeAmount = cubeDetails[0];
                var cubeColour = Colours.valueOf(cubeDetails[1].toUpperCase());

                switch (cubeColour) {
                    case BLUE -> blueCubes.add(Integer.valueOf(cubeAmount));
                    case RED -> redCubes.add(Integer.valueOf(cubeAmount));
                    case GREEN -> greenCubes.add(Integer.valueOf(cubeAmount));
                }
            }
        }

        var maxBlue = blueCubes.stream().max(Comparator.naturalOrder());
        //Another possibility for obtaining max...
//        blueCubes.sort(Comparator.naturalOrder());
//        blueCubes.get(blueCubes.size()-1);
        var maxRed = redCubes.stream().max(Comparator.naturalOrder());
        var maxGreen = greenCubes.stream().max(Comparator.naturalOrder());

        return maxBlue.orElse(1) * maxRed.orElse(1) * maxGreen.orElse(1);
    }

    public Integer findSolution(List<String> games) {
        var sumOfPossibleGames = 0;
        for (var game : games) {
            var gameComponents = game.split(":"); // ["Game 1"]["1 blue; 2 red, 6 green"]
            var gameIdentifier = Integer.valueOf(gameComponents[0].substring(5)); // "1"
            var handfuls = gameComponents[1].split(";"); // ["1 blue"] ["2 red, 6 green"]

            if (isValidHandful(handfuls)) {
                sumOfPossibleGames += gameIdentifier;
            }
        }

        return sumOfPossibleGames;
    }

    private static boolean isValidHandful(String[] handfuls) {
        var isValid = false;

        for (var handful : handfuls) {
            var cubes = handful.split(","); // ["2 red"]  ["6 green"]
            isValid = areAllCubesValid(cubes);
            if (!isValid) {
                break;
            }
        }
        return isValid;
    }

    private static boolean areAllCubesValid(String[] cubes) {
        boolean isValid = false;

        for (var cube : cubes) {
            var cubeDetails = cube.trim().split(" ");
            var cubeAmount = cubeDetails[0];
            var cubeColour = Colours.valueOf(cubeDetails[1].toUpperCase());

            isValid = switch (cubeColour) {
                case BLUE -> Integer.parseInt(cubeAmount) <= BLUE.maxValue;
                case RED -> Integer.parseInt(cubeAmount) <= RED.maxValue;
                case GREEN -> Integer.parseInt(cubeAmount) <= GREEN.maxValue;
            };

            if (!isValid) {
                break;
            }
        }
        return isValid;
    }

    enum Colours {
        BLUE(14),
        RED(12),
        GREEN(13);

        final int maxValue;

        Colours(int maxValue) {
            this.maxValue = maxValue;
        }
        //necesario para el push
    }
}


