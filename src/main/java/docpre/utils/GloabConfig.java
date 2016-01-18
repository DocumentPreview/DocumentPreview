package docpre.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by chengke on 16/1/18.
 */
public class GloabConfig {
    private static Properties properties = new Properties();
    public static void init() throws IOException {
        properties.load(GloabConfig.class.getResourceAsStream("/docpre.properties"));
    }

    public static String get(String name) {
        return properties.getProperty(name);
    }

}
