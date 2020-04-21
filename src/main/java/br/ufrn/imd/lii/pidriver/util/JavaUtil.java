package br.ufrn.imd.lii.pidriver.util;

import java.util.Arrays;

public class JavaUtil {

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
