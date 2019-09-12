package com.example.demo.converter;

import com.example.demo.exceptions.CustomConverterException;
import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;
import com.example.demo.services.impl.DemoServiceImpl;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * to convert ReferenceJ to Optional<ReferenceX>
 * when throw CustomConverterException return Optional.empty()
 *
 * @return !Optional.empty()
 */
public class ReferenceConverter {

    private static ModelMapper modelMapper;
    final Logger logger = Logger.getLogger(DemoServiceImpl.class);

    public Optional<ReferenceX> convertToReferenceX(ReferenceJ referenceJ) {
        modelMapper = new ModelMapper();
        try {
            ReferenceJ referenceJ2 = validateReferenceJ(referenceJ);
            return Optional.of(modelMapper.map(referenceJ, (Type) ReferenceX.class));
        } catch (CustomConverterException ex) {
            logger.error("convertToReferenceX error " + ex.getMessage());
            return Optional.empty();
        }
    }

    private ReferenceJ validateReferenceJ(ReferenceJ referenceJ) {

        if (referenceJ == null) {
            throw new CustomConverterException("referenceJ cannot be null");
        }
        if (referenceJ.getColor() == null ||
                referenceJ.getPrice() == null ||
                referenceJ.getSize() == null ||
                referenceJ.getNumReference() == null) {
            throw new CustomConverterException("referenceJ paramater cannot be null");
        }
        return referenceJ;
    }

}
