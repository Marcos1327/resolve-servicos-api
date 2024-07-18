package com.resolveservicos.utils;

import org.springframework.stereotype.Component;

@Component
public class Util {

    public boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }


}
