package com.alex.java8.stream;

import com.alex.java8.stream.entity.Dish;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _2_NumericalStreamTest {
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

    @Test
    public void generatePythagoreanNumbers() {
        int max = 100;

        Stream<int[]> pythagoreanNumbers = IntStream.rangeClosed(1, max).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, max)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanNumbers
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }

    @Test
    public void generatePythagoreanNumbers_1() {
        int max = 100;
        /*先生成三元组，再筛选，只需要算一次平方根,复杂度 n^2 -> n*/
        Stream<double[]> pythagoreanNumbers = IntStream.rangeClosed(1, max).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, max)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);

        pythagoreanNumbers.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }


}
