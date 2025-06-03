package com.org;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char p1 = s.charAt(i), p2 = t.charAt(i);
            if (!map.containsKey(p1)) {
                if (!map.containsValue(p2)) {
                    map.put(p1, p2);
                } else {
                    return false;
                }
            } else {
                char mappedValue = map.get(p1);
                if (mappedValue != p2) {
                    return false;
                }
            }
        }
        return true;
    }
}















