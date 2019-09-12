package com.example.demo.services;

import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;

import java.util.List;

public interface DemoService {

    List<ReferenceJ> buildReferenceJ();

    List<ReferenceX> buildReferenceX(List<ReferenceJ> referenceJS);

    void writeJsonFile(List<ReferenceJ> referenceJS);

    void writeXmlFile(List<ReferenceX> referenceXES);
}
