package com.alex.java8.stream;

import com.alex.java8.stream.entity.Dish;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericalStreamTest {
    private List<Dish> menu;

    @BeforeMethod
    public void setup() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
    }

    @Test
    /*包含拆装箱成本*/
    public void calculateCalories() {
        Integer reduce = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    /*先转换成int流，再计算则没有拆装箱成本*/
    @Test
    public void calculateCalories_2() {
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);
    }

    @Test
    public void intStreamToOrdinaryStream() {
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);

        Stream<Integer> boxedStream = intStream.boxed();
    }

    @Test
    public void generateEvenNumberFrom_1to100_AndCounted() {
        IntStream intStream = IntStream.rangeClosed(1, 100);
        long count = intStream.filter(n -> n % 2 == 0)
                .count();
        System.out.println(count);
    }








}
