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
public class PrimitiveToObjectDeserializator extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return Long.valueOf(p.getText());
        } catch (NumberFormatException nfe) {
            // Not a Long
        }
        try {
            return Double.valueOf(p.getText());
        } catch (NumberFormatException nfe) {
            // Not a Double
        }
        if ("TRUE".equalsIgnoreCase(p.getText())
                || "FALSE".equalsIgnoreCase(p.getText())) {
            // Looks like a boolean
            return Boolean.valueOf(p.getText());
        }
        return String.valueOf(p.getText());
    }
}
