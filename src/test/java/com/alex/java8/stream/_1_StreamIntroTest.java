package com.alex.java8.stream;

import com.alex.java8.stream.entity.Trader;
import com.alex.java8.stream.entity.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _1_StreamIntroTest {
    private List<Transaction> transactions;

    @BeforeMethod
    private void setUp() {
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
    public void findAllTransactionSince2011_sortedByValueLowToHigh() {
        List<Transaction> collect =
                transactions.stream()
                .filter(trans -> trans.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());


        collect.forEach(e -> System.out.println(e));
    }

    @Test
    public void findCitiesTradersHaveLivedIn() {
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        collect.forEach(c -> System.out.println(c));
    }

    @Test
    public void findAllTraderFromCambridge_SortByName() {
        Stream<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName));

        cambridgeTraders.forEach(System.out::println);
    }

    @Test
    public void findAllTraderNameAsString_SortedByLetter() {
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName().toString())
                .distinct()
                .sorted()
                .reduce("", (a1, a2) -> a1 + a2);

        System.out.println(reduce);
    }

    @Test
    public void findTraderWhoWorkInMilan() {
        List<Trader> milan = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Milan"))
                .collect(Collectors.toList());
        milan.forEach(System.out::println);
    }

    @Test
    public void findAllTransactionsValueWhichMadeByTraderLiveInCambridge() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void findMaxValueInTransactions() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.naturalOrder());

        System.out.println(max);
    }

    @Test
    public void findMinValueInTransactions() {
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .min(Comparator.naturalOrder());
        System.out.println(min);
    }

    @Test
    public void findMinValueInTransactions_2() {
        Optional<Integer> integer = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .map(Transaction::getValue);

        System.out.println(integer);
    }

    @Test
    public void findMinValueInTransactions_3() {
        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce((n1, n2) -> n1 < n2 ? n1 : n2);

        System.out.println(reduce);
    }


































}
