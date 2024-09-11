package it.must.be.funny.config;

import it.must.be.funny.model.ConfigProperties;
import it.must.be.funny.model.KafkaConfigProperties;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;


public class SnakeYmlConfigLoader {
//    private SnakeYml snakeYml;
    private static final String configPath = "config.yml";
    //todo pass config path when init app
    public static ConfigProperties loadConfig(){

        Yaml yaml = new Yaml();
        try (InputStream inputStream = SnakeYmlConfigLoader.class.getClassLoader().getResourceAsStream(configPath)) {
            ConfigProperties config = yaml.loadAs(inputStream, ConfigProperties.class);
            System.out.println(config.getKafkaConfigProperties().toString());
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
