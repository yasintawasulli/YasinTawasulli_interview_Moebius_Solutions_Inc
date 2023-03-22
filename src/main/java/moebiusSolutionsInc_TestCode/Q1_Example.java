package moebiusSolutionsInc_TestCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q1_Example {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 20, 5, 30, 40, 7));
        System.out.println(removeBigNumbers(list));
        System.out.println(removeBigNumbers2(list));
    }

    // if we try to remove an element from Collection remove method inside for loop,
    // it throws java.util.ConcurrentModificationException
    public static List<Integer> removeBigNumbers(List<Integer> data) {
        for (Integer i : data) {
            if (i > 10) {
                data.remove(i);
            }
        }
        return data;
    }

    // we can fix this using removeIf method
    public static List<Integer> removeBigNumbers2(List<Integer> data) {
        data.removeIf(num -> num > 10);
        return data;
    }

    // or using stream
    public static List<Integer> removeBigNumbers3(List<Integer> data) {
        return data.stream().filter(n -> n <= 10).collect(Collectors.toList());
    }
}