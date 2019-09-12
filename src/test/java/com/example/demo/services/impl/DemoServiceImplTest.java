package com.example.demo.services.impl;

import com.example.demo.enums.Color;
import com.example.demo.models.ReferenceJ;
import com.example.demo.services.DemoService;
import com.example.demo.utils.BuilderUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * backup config.properties before launching junit test
 */
public class DemoServiceImplTest {

    private static DemoService demoService;

    @Before
    public void setUp() throws Exception {
        demoService = new DemoServiceImpl();
        BuilderUtils.configPropertiesBuilder("entry.path=C:\\\\files\\\\entry\\\\reference.txt\n" +
                "json.out.path=C:\\\\files\\\\out\\\\reference.json\n" +
                "xml.out.path=C:\\\\files\\\\out\\\\reference.xml");
    }

    @Test
    public void test_buildReferenceJ_should_return_results() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;45.12;27\n" +
                        "1460900848;G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(4, results.size());
        Assert.assertEquals("1460100040", results.get(0).getNumReference());
        Assert.assertEquals(Color.R, results.get(0).getColor());
        Assert.assertTrue(45.12f == results.get(0).getPrice());
        Assert.assertTrue(27 == results.get(0).getSize());
    }

    @Test
    public void test_buildReferenceJ_when_file_not_found_throw_exception() {
        //given
        try {
            File file = new File("C:\\files\\entry\\reference.txt");
            file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertNull("return null", results);
    }

    @Test
    public void test_buildReferenceJ_when_numReference_doesnt_exit_should_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;45.12;27\n" +
                        "G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_color_doesnt_exit_should_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;45.12;27\n" +
                        "1460900848;G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_price_doesnt_exit_should_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;45.12;27\n" +
                        "1460900848;G;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_size_doesnt_exit_should_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;45.12;27\n" +
                        "1460900848;G;12.0\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_color_not_a_enum_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;ZZ;45.12;27\n" +
                        "1460900848;G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_price_not_a_float_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;fake;27\n" +
                        "1460900848;G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void test_buildReferenceJ_when_size_not_an_integer_return_list_without_line() {
        //given
        BuilderUtils.entryFileBuilder(
                "1460100040;R;45.12;fake\n" +
                        "1460900848;G;12.0;145\n" +
                        "1462100044;G;5.56;19\n" +
                        "1462100403;B;105.23;97");

        //when
        List<ReferenceJ> results = demoService.buildReferenceJ();

        //then
        Assert.assertEquals(3, results.size());
    }

    //toDo
    @Test
    public void test_buildReferenceJ_when_columns_have_wrong_order_should_throw_exception_and_stop_application() {

    }

    @Test
    public void test_buildReferenceJ_when_the_number_of_columns_are_not_equals_to_5_should_throw_exception_and_stop_application() {
    }

    @Test
    public void test_buildReferenceJ_when_the_file_extension_is_not_txt_should_throw_exception_and_stop_application() {
    }

    @Test
    public void test_buildReferenceJ_when_separator_are_not_semi_colon_should_throw_exception_and_stop_application() {
    }

    @Test
    public void test_buildReferenceX_should_return_result() {

    }

    @Test
    public void test_buildReferenceX_when_conversion_failed_should_go_to_next_conversion() {
    }

    @Test
    public void test_buildReferenceX_when_numReference_doesnt_exit_should_return_list_without_line() {
    }

    @Test
    public void test_buildReferenceX_when_color_doesnt_exit_should_return_list_without_line() {
    }

    @Test
    public void test_buildReferenceX_when_price_doesnt_exit_should_return_list_without_line() {
    }

    @Test
    public void test_buildReferenceX_when_size_doesnt_exit_should_return_list_without_line() {
    }

    @Test
    public void test_writeJsonFile_when_operation_succeed() {
    }

    @Test
    public void test_writeJsonFile_when_operation_failed() {
    }

    @Test
    public void test_writeXmlFile_when_operation_succeed() {
    }

    @Test
    public void test_writeXmlFile_when_operation_failed() {
    }
}
