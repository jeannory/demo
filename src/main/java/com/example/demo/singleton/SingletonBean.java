package com.example.demo.singleton;

import com.example.demo.exceptions.CustomFileException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * load config.properties
 * which contains entry/out path
 */
@Scope("singleton")
public class SingletonBean {

    final static Logger logger = Logger.getLogger(SingletonBean.class);

    private static Reader validateInputStreamParameter() {
        try {
            FileReader input = new FileReader("C:\\Users\\jeannory.phou\\gpDoc\\perso\\Hardis-group-test\\demo\\src\\main\\resources\\config.properties");

            if (input == null) {
                throw new CustomFileException("file not found");
            }
            return input;
        } catch (IOException ex) {
            throw new CustomFileException("file not found");
        }
    }

    /**
     * load config file : config.properties
     * which contains in/out files path
     *
     * @return file path(@String)
     */
    public String getConfigProperty(String entry) {

        logger.info("getConfigProperty start");
        try {
            Reader input = validateInputStreamParameter();
            Properties prop = new Properties();
            prop.load(input);
            logger.info("getConfigProperty end " + prop);
            input.close();
            return prop.getProperty(entry);
        } catch (IOException ex) {
            throw new CustomFileException("IOException");
        } catch (CustomFileException ex) {
            logger.error("getConfigProperty error : " + ex.getMessage());
            logger.error("DemoApplication end");
            logger.error("*********************");
        }
        return null;
    }

}
