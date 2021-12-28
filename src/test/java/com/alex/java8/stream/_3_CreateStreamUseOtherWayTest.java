package com.alex.java8.stream;

import org.testng.annotations.Test;

import java.util.stream.Stream;

public class _3_CreateStreamUseOtherWayTest {

    @Test
    public void createStreamFromStaticParams() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(s -> System.out.println(s));
    }


}
