package kz.asumc.common;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
}
