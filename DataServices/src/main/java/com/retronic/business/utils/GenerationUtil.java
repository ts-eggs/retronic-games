package com.retronic.business.utils;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class GenerationUtil {

    private GenerationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateToken() {
        Integer randomNum = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        Long timeRandom = Calendar.getInstance().getTimeInMillis() / randomNum;
        return timeRandom.toString() + randomNum.toString();
    }
}
