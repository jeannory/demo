package com.example.demo.utils;

import com.example.demo.enums.Color;
import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;

import java.io.FileWriter;

public class BuilderUtils {

    public static ReferenceJ buildReferenceJ(String numReference, Color color, Float price, Integer size){
        final ReferenceJ referenceJ = new ReferenceJ();
        referenceJ.setNumReference(numReference);
        referenceJ.setColor(color);
        referenceJ.setPrice(price);
        referenceJ.setSize(size);
        return referenceJ;
    }

    public static ReferenceX buildReferenceX(String numReference, Color color, Float price, Integer size){
        final ReferenceX referenceX = new ReferenceX();
        referenceX.setNumReference(numReference);
        referenceX.setColor(color);
        referenceX.setPrice(price);
        referenceX.setSize(size);
        return referenceX;
    }

    public static void configPropertiesBuilder(String configParam){
        try{
            FileWriter fw=new FileWriter("C:\\Users\\jeannory.phou\\gpDoc\\perso\\Hardis-group-test\\demo\\src\\main\\resources\\config.properties");
//            FileWriter fw=new FileWriter("/home/jeanno/Hardis-group-test/demo/src/main/resources/config.properties");
            fw.write(configParam);
            fw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void entryFileBuilder(String datas){
        try{
            FileWriter fw=new FileWriter("C:\\files\\entry\\reference.txt");
//            FileWriter fw=new FileWriter("/home/jeanno/Hardis-group-test/demo/src/main/resources/files/entry/references.txt");
            fw.write(datas);
            fw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
