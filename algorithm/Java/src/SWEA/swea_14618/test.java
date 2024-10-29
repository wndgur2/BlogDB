package SWEA.swea_14618;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String word = "abc";
        String res = "";
        char[] wordChar = word.toCharArray();
        for(char c: wordChar){
            res = String.join("", Character.toString(c), res);
        }
        System.out.println(res);
    }
}
