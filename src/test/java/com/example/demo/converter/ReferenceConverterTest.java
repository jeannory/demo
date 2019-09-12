package com.example.demo.converter;

import com.example.demo.enums.Color;
import com.example.demo.exceptions.CustomConverterException;
import com.example.demo.models.ReferenceJ;
import com.example.demo.models.ReferenceX;
import com.example.demo.utils.BuilderUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReferenceConverterTest {

    @InjectMocks
    ReferenceConverter referenceConverter;

    @Mock
    ModelMapper modelMapper;

    @Spy
    ReferenceJ referenceJ;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_convertToReferenceX_should_return_result() {
        //given
        referenceJ = BuilderUtils.buildReferenceJ("4567", Color.B,15.5f,45);
        final ReferenceX referenceX =BuilderUtils.buildReferenceX("4567", Color.B,15.5f,45);
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenReturn(referenceX);

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals("4567", result.get().getNumReference());
        Assert.assertEquals(Color.B, result.get().getColor());
        Assert.assertTrue(15.5f==result.get().getPrice());
        Assert.assertTrue(45== result.get().getSize());
    }

    @Test
    public void test_convertToReferenceX_when_referenceJ_is_null_then_throw_exception(){
        //given
        referenceJ = null;
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }

    @Test
    public void test_convertToReferenceX_when_referenceJ_is_empty_then_throw_exception(){
        //given
        referenceJ = new ReferenceJ();
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }

    @Test
    public void test_convertToReferenceX_when_color_is_null_then_throw_exception(){
        //given
        referenceJ = BuilderUtils.buildReferenceJ("4567", null,15.5f,45);
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }

    @Test
    public void test_convertToReferenceX_when_price_is_null_then_throw_exception(){
        //given
        referenceJ = BuilderUtils.buildReferenceJ("4567", Color.B,null,45);
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }

    @Test
    public void test_convertToReferenceX_when_numReference_is_null_then_throw_exception(){
        //given
        referenceJ = BuilderUtils.buildReferenceJ(null, Color.B,15.5f,45);
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }

    @Test
    public void test_convertToReferenceX_when_size_is_null_then_throw_exception(){
        //given
        referenceJ = BuilderUtils.buildReferenceJ("4567", Color.B,15.5f,null);
        Mockito.when(modelMapper.map(referenceJ, (Type) ReferenceX.class)).thenThrow(new CustomConverterException());

        //when
        final Optional<ReferenceX> result = referenceConverter.convertToReferenceX(referenceJ);

        //then
        Assert.assertEquals(Optional.empty(), result);
    }
}
