package com.store.stationary.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class TestUtil {
    public static String load(String jsonFile) throws IOException {
        return IOUtils.toString(
                TestUtil.class.getResourceAsStream(String.format("/fixtures/%s.json", jsonFile)),
                "UTF-8"
        );
    }
}
