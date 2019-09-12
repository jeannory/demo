package com.example.demo;

import com.example.demo.exceptions.CustomBuildReferenceJException;
import com.example.demo.exceptions.CustomConfigParameterException;
import com.example.demo.exceptions.CustomFileException;
import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;
import com.example.demo.services.DemoService;
import com.example.demo.services.impl.DemoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    final static Logger logger = Logger.getLogger(DemoServiceImpl.class);
    private static DemoService demoService = new DemoServiceImpl();

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

        try {
            logger.info("*********************");
            logger.info("DemoApplication start");
            logger.info("*********************");
            List<ReferenceJ> referenceJS = demoService.buildReferenceJ();
            demoService.writeJsonFile(referenceJS);
            List<ReferenceX> referenceXES = demoService.buildReferenceX(referenceJS);
            demoService.writeXmlFile(referenceXES);
            logger.info("*********************");
            logger.info("DemoApplication end");
            logger.info("*********************");
        } catch (CustomFileException ex) {
            logger.error("application error : " + ex.getMessage());
            logger.error("DemoApplication end");
            logger.error("*********************");
        } catch (CustomConfigParameterException ex) {
            logger.error("application error : " + ex.getMessage());
            logger.error("DemoApplication end");
            logger.error("*********************");
        } catch (CustomBuildReferenceJException ex) {
            logger.error("application error : " + ex.getMessage());
            logger.error("DemoApplication end");
            logger.error("*********************");
        }

    }

}
