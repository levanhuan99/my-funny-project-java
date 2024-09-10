package it.must.be.funny.config;

import it.must.be.funny.model.KafkaConfigProperties;
import it.must.be.funny.model.SnakeYml;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class SnakeYmlConfig {
    private SnakeYml snakeYml;
    private static final String configPath = "config.yml";
    //todo
    public void loadConfig(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(configPath);
        Map<String, SnakeYml> obj = yaml.load(inputStream);
        System.out.println(obj);
    }

//    public SnakeYml getSnakeYml(){
//
//    }
}
