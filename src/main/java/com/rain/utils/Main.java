package com.rain.utils;

import com.rain.utils.core.StrUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String format = StrUtils.format("hello \\${} ${}", "world", "rain");
        System.out.println(format);
    }
}
