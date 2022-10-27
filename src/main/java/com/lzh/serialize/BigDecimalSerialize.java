package com.lzh.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author lzh
 * @description 去掉金额小数点后多余0
 */
public class BigDecimalSerialize extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(bigDecimal != null){
            jsonGenerator.writeObject(new BigDecimal(bigDecimal.stripTrailingZeros().toPlainString()));
        }
    }
}
