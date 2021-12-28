package com.alex.java8.comparator;

import com.alex.java8.lambda.A;
import com.alex.java8.stream.entity.Trader;
import com.alex.java8.stream.entity.Transaction;
import com.google.common.collect.Lists;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class ComparatorTest {
    private List<Integer> numbers;
    private List<Character> letters;
    private List<Transaction> transactions;

    @BeforeMethod
    public void setup() {
        numbers = Arrays.asList(13, 3, 4, 5, 6, 7, 1, 10);
        letters = Arrays.asList('A', 'C', 'D', 'E', 'B');
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void comparatorTest_Numbers() {
        Collections.sort(numbers);
        System.out.println(numbers);

        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o2 - o1;
            }
        });
        System.out.println(numbers);
    }

    @Test
    public void comparatorTest_letters() {
        Collections.sort(letters);
        System.out.println(letters);

        Collections.sort(letters, (a, b) -> b - a);
        System.out.println(letters);
    }

    @Test
    public void comparatorTest_Transactions() {
        Collections.sort(transactions,(a,b)-> a.getValue() - b.getValue());
        transactions.forEach(transaction -> System.out.println(transaction.getValue()));

        Collections.sort(transactions,(a,b)-> b.getValue() - a.getValue());
        transactions.forEach(transaction -> System.out.println(transaction.getValue()));

    }





}
