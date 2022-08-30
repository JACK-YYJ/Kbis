package org.springblade.modules.user.tool.utils;

import java.util.UUID;

public class UUIDUtil {
    public static final String getId(){


        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
