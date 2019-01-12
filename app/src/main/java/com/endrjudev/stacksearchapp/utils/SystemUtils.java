package com.endrjudev.stacksearchapp.utils;

import com.endrjudev.stacksearchapp.model.StackResponse;

public class SystemUtils {

    private SystemUtils(){
        // Empty construction to assure this class is encapsulated
    }

    public static boolean isResponseOK(StackResponse response) {
        return response != null;
    }
}
