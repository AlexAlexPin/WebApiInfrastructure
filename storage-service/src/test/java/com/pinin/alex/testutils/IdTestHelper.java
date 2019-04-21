package com.pinin.alex.testutils;

import java.util.UUID;

public final class IdTestHelper {
    public static String newUuid() {
        return UUID.randomUUID().toString();
    }
}
