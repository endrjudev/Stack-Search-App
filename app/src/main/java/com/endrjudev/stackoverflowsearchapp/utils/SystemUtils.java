package com.endrjudev.stackoverflowsearchapp.utils;

import com.endrjudev.stackoverflowsearchapp.model.BaseResponse;

public class SystemUtils {

    private static final int POSITIVE_RESPONSE_CODE = 200;

    private SystemUtils(){
        // Empty construction to assure this class is encapsulated
    }

    public static boolean isResponseOK(BaseResponse baseResponse) {
        return baseResponse != null
                && baseResponse.getResponse() != null
                && baseResponse.getStatus().getCode() == POSITIVE_RESPONSE_CODE;
    }
}
