/*
 * Copyright (c) 2020 JarCasting
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jarcasting.commons.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey B. Kurgan
 * Date: 29.05.14
 * Time: 13:42
 * Класс сериализации BigDecimal
 */
public class DoubleSerializator extends JsonSerializer<Double> {
    private static DecimalFormat df2 = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ROOT));


    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        if (value != null)
            jgen.writeNumber(df2.format(value));

    }
}
