package com.knoldus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFileTransformation {

    String datafile;

    public static void main(String[] args) {
        ReadFileTransformation readFileTransformation = new ReadFileTransformation();
        System.out.println(readFileTransformation.frequency());
    }

    public Map<String, Integer> frequency() {
        try {
            datafile = Files.lines(Paths.get("src/com/knoldus/DataFile.csv"))
                    .collect(Collectors.joining());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        List<String> strings =
                Stream.of(datafile)
                        .map(w -> w.split("[^\\w]"))
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList());

        Map<String, Integer> frequency = new HashMap<>();
        strings.forEach(count -> {
            frequency.putIfAbsent(count, 0);
            frequency.put(count, frequency
                    .get(count) + 1);
        });
        return frequency;
    }
}