package com.acc.helper;

import java.util.Random;

public class RandStrGenerator {
	public static String getRandomString() {
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();
        while (randomString.length() < 10) { 
            int index = (int) (rnd.nextFloat() * allChars.length());
            randomString.append(allChars.charAt(index));
        }
        String result = randomString.toString();
        return result;

    }

}
