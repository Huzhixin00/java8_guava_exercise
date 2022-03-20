package com.alex.java8.stream;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class _3_CreateStreamUseOtherWayTest {

    @Test
    public void createStreamFromStaticParams() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(s -> System.out.println(s));
    }

    @Test
    public void createStreamFromArray() {
        int[] numbers = {2, 3, 4, 5, 6, 7};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    @Test
    public void createStreamFromFile() {
        Long uniqueWords = null;
        try {
            Stream<String> lines = Files.lines(Paths.get("/Users/zhixihu/IdeaProjects/GuavaExercise/src/main/resources/data.txt"), Charset.defaultCharset());
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split("")))
                    .distinct()
                    .count();
        } catch (IOException e) { }
        System.out.println("uniqueWords: " + uniqueWords);
    }


    /* infinite stream : Stream.iterate Stream.generate */
    @Test
    public void createStreamFromFunction_1() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void createStreamFromFunction_2() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }





}
