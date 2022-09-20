package chillrain.launcher.util;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

/**
 * @author ChillRain 2022 09 06
 */
public class Config {
    public static File config = new File("resources/config.properties");
    public static Properties properties = new Properties();

    public static void gameDirPathWrite(String key, String mess) throws IOException {
        InputStream in = Files.newInputStream(config.toPath());
        properties.load(in);
        properties.setProperty(key, mess);
        OutputStream write = Files.newOutputStream(config.toPath());
        properties.store(write, key);
        in.close();
        write.close();
    }

    public static Properties propertiesRead() throws IOException {
        FileInputStream stream = null;
//        存在配置文件 并读取
        if (config.exists()){
            try{
                stream = new FileInputStream(config);
                properties.load(stream);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }finally{
                stream.close();
            }
        }
        return properties;
    }
}
