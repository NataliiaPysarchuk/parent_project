package util.readers;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

import static java.lang.String.format;

@Slf4j
public final class PropertiesReader {

    private PropertiesReader() {
    }

    private static final String CONFIG_PROPERTIES = "config.properties";

    private static Properties PROPERTIES;

    @Synchronized
    public static String getProperty(final String propertyName) {
        if (PROPERTIES == null) {
            try (var reader = util.readers.PropertiesReader.class
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_PROPERTIES)) {

                var properties = new Properties();
                properties.load(reader);
                PROPERTIES = properties;
            } catch (Exception ex) {
                throw new IllegalStateException(format("An issue: '%s' occurred during reading config.properties file !!", ex));
            }
        }

        var systemProperty = System.getProperty(propertyName);

        return systemProperty == null
                ? PROPERTIES.getProperty(propertyName, "Wrong config.properties name was set !!")
                : systemProperty;
    }
}
