package com.example.demo.singleton;

import com.example.demo.utils.BuilderUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;


/**
 * backup config.properties before launching junit test
 *
 *
 */
public class SingletonBeanTest {

      private static SingletonBean singletonBean;

    @Before
    public void setUp() throws Exception {
        singletonBean = new SingletonBean();
    }

    @Test
    public void test_getConfigProperty_should_return_results_bis(){
        BuilderUtils.configPropertiesBuilder("data.test=hello world");
        String result = singletonBean.getConfigProperty("data.test");
        Assert.assertEquals("hello world", result);
    }

    @Test
    public void test_getConfigProperty_when_parameter_not_found(){
        BuilderUtils.configPropertiesBuilder("data.test=hello world");
        String result = singletonBean.getConfigProperty("data.failed");
        Assert.assertNull("return null", result);
    }

    @Test
    public void test_getConfigProperty_when_2_sames_parameters_should_return_last_line(){
        BuilderUtils.configPropertiesBuilder("data.test=hello world 1\ndata.test=hello world 2");
        String result = singletonBean.getConfigProperty("data.test");
        Assert.assertNotEquals("hello world 1", result);
        Assert.assertEquals("hello world 2", result);
    }

    @Test
    public void test_getConfigProperty_when_config_properties_not_found(){
        try {
            File file = new File("C:\\Users\\jeannory.phou\\gpDoc\\perso\\Hardis-group-test\\demo\\src\\main\\resources\\config.properties");
//        File file = new File("/home/jeanno/Hardis-group-test/demo/src/main/resources/config.properties");
            file.delete();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        String result = singletonBean.getConfigProperty("data.test");
        Assert.assertNull("return null", result);
    }
}
