package com.example.demo.services.impl;

import com.example.demo.converter.ReferenceConverter;
import com.example.demo.enums.Color;
import com.example.demo.exceptions.CustomConfigParameterException;
import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;
import com.example.demo.services.DemoService;
import com.example.demo.singleton.SingletonBean;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DemoServiceImpl implements DemoService {

    private static SingletonBean singletonBean = new SingletonBean();
    private static ReferenceConverter referenceConverter;
    final Logger logger = Logger.getLogger(DemoServiceImpl.class);

    /**
     * get datas from csv
     * map to List<ReferenceJ> (json mapping objects)
     *
     * @return List<ReferenceJ>
     */
    public List<ReferenceJ> buildReferenceJ() {
        logger.info("readEntryfile start");
        List<ReferenceJ> referenceJS = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(singletonBean.getConfigProperty("entry.path")), ';', '"', 0);
            String[] nextRecord;
            int i = 0;
            while ((nextRecord = csvReader.readNext()) != null) {

                try {
                    i += 1;
                    final ReferenceJ referenceJ = new ReferenceJ();
                    referenceJ.setNumReference(nextRecord[0]);
                    referenceJ.setColor(Color.valueOf(nextRecord[1]));
                    referenceJ.setPrice(Float.valueOf(nextRecord[2]));
                    referenceJ.setSize(Integer.valueOf(nextRecord[3]));
                    referenceJS.add(referenceJ);

                } catch (Exception ex) {
                    logger.error("readEntryfile error at line " + i);
                }
            }
            logger.info("readEntryfile success");
        } catch (CustomConfigParameterException ex) {
            logger.error("buildReferenceJ error : " + ex.getMessage());
            return null;
        } catch (IOException ex) {
            logger.error("buildReferenceJ error : " + ex.getMessage());
            return null;
        }
        return referenceJS;
    }

    /**
     * convert List<ReferenceJ> List<ReferenceX>
     * (xml mapping objects)
     *
     * @return List<ReferenceX>
     */
    //List could have empty values
    public List<ReferenceX> buildReferenceX(List<ReferenceJ> referenceJS) {
        logger.info("buildReferenceX start");
        referenceConverter = new ReferenceConverter();
        logger.info("buildReferenceX success");
        return referenceJS.stream().map(referenceJ -> {
                    final Optional<ReferenceX> referenceX = referenceConverter.convertToReferenceX(referenceJ);
                    if (referenceX.isPresent()) {
                        return referenceX.get();
                    }
                    return null;
                }
        ).collect(Collectors.toList());
    }

    /**
     * serialize List<ReferenceJ>
     * into json file
     *
     * @return void
     */
    public void writeJsonFile(List<ReferenceJ> referenceJS) {
        logger.info("writeJsonFile start");
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(singletonBean.getConfigProperty("json.out.path")), referenceJS);
            logger.info("writeJsonFile success");
        } catch (JsonGenerationException ex) {
            logger.error("writeJsonFile error : " + ex.getMessage());
        } catch (JsonMappingException ex) {
            logger.error("writeJsonFile error : " + ex.getMessage());
        } catch (IOException ex) {
            logger.error("writeJsonFile error : " + ex.getMessage());
        }
    }

    /**
     * serialize List<ReferenceX>
     * into xml file
     *
     * @return void
     */
    public void writeXmlFile(List<ReferenceX> referenceXES) {
        logger.info("writeXmlFile start");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(singletonBean.getConfigProperty("xml.out.path")), referenceXES);
            logger.info("writeXmlFile success");
        } catch (IOException ex) {
            logger.error("writeXmlFile error : " + ex.getMessage());
        }
    }
}
