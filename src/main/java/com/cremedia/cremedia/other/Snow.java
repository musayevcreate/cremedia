package com.cremedia.cremedia.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snow {



    private static void permute(List<Integer> arr, int num, List<List<Integer>> permutations) {
        for (int i = num; i < arr.size(); i++) {
            Collections.swap(arr, i, num);
            permute(arr, num + 1, permutations);
            Collections.swap(arr, num, i);
        }
        if (num == arr.size() - 1) {
            permutations.add(new ArrayList<>(arr));
        }
    }

    private static boolean isValidCombination(List<Integer> combination) {

        List<Integer> validCombination1 = List.of(5, 6, 1, 2, 3, 4);
        List<Integer> validCombination2 = List.of(1, 6, 5, 4, 3, 2);

        return combination.equals(validCombination1) || combination.equals(validCombination2);
    }
}










//    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<>();
//        Collections.addAll(numbers, 1, 2, 3, 4, 5, 6);
//
//        List<List<Integer>> permutations = new ArrayList<>();
//        permute(numbers, 0, permutations);
//
//        for (List<Integer> perm : permutations) {
//            if (isValidCombination(perm)) {
//                System.out.println("Geçerli kombinasyon: " + perm);
//            }
//        }
//    }