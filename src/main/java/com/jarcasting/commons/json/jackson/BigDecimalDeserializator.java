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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey B. Kurgan
 * Date: 29.05.14
 * Time: 13:58
 * Класс десериализации BigDecimal
 */
public class BigDecimalDeserializator extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String strValue = jp.getValueAsString();
        if (StringUtils.isNotEmpty(strValue)) {
            try {
                return new BigDecimal(strValue);
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
