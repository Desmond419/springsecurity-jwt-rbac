package com.desmond.utils;

import java.util.UUID;

public class GenUUID {

    private GenUUID() {}

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
