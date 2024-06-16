package com.cremedia.cremedia.other;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

public class Other {


    public static void main(String[] args) {
        Other convert = new Other();
        System.out.println(convert.romanToInt("LC"));
    }




    public int romanToInt(String roman) {
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put(' ', 0);

        int result = 0;

        for (int i = 0; i < roman.length(); i++) {
            if (i > 0 && map.get(roman.charAt(i)) > map.get(roman.charAt(i - 1))) {

                result += map.get(roman.charAt(i)) - 2 * map.get(roman.charAt(i - 1));
            } else {
                result += map.get(roman.charAt(i));
            }
        }

        return result;
    }

}
