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
package com.jarcasting.commons.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import com.jarcasting.commons.json.jackson.BigDecimalDeserializator;
import com.jarcasting.commons.json.jackson.BigDecimalSerializator;
import com.jarcasting.commons.json.jackson.DoubleSerializator;
import jakarta.json.JsonValue;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class JsonUtils {

    public static final String UUID_PATTERN = "\\{?\\p{XDigit}{8}-\\p{XDigit}" +
            "{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}\\}?";


    public static ObjectMapper myObjectMapper;
    public static ObjectMapper myObjectMapperNoPretty;
    public static ObjectMapper myObjectMapperToObjects;

    static {
        myObjectMapper = new ObjectMapper();
        myObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        myObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(indenter);
        prettyPrinter.indentObjectsWith(indenter);
        myObjectMapper.setDefaultPrettyPrinter(prettyPrinter);
        //myObjectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        myObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        myObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        myObjectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        myObjectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        myObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        myObjectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        //myObjectMapper.disable(DeserializationFeature.);
        myObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        myObjectMapper.setVisibility(
                myObjectMapper.getSerializationConfig()
                        .getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.ANY)
                        .withSetterVisibility(JsonAutoDetect.Visibility.ANY)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.ANY)
        );

        final SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BigDecimalSerializator());
        module.addDeserializer(BigDecimal.class, new BigDecimalDeserializator());
        myObjectMapper.registerModule(module);

        final SimpleModule module1 = new SimpleModule();
        module.addSerializer(Double.class, new DoubleSerializator());
        myObjectMapper.registerModule(module1);

        myObjectMapper.registerModule(new JavaTimeModule());
        myObjectMapper.registerModule(new JSR353Module());
        myObjectMapperNoPretty = myObjectMapper.copy();
        myObjectMapperNoPretty.configure(SerializationFeature.INDENT_OUTPUT, false);

        myObjectMapperToObjects = myObjectMapper.copy();


    }

    public static String toJson(Object obj) {
        try {
            return myObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson(Object obj, File file) {
        try {
            myObjectMapper.writeValue(file, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson(Object obj, Writer writer) {
        try {
            myObjectMapper.writeValue(writer, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson(Object obj, OutputStream out) {
        try {
            myObjectMapper.writeValue(out, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson(Object obj, DataOutput out) {
        try {
            myObjectMapper.writeValue(out, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static String toJson2(Object obj) {
        try {
            return myObjectMapperNoPretty.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson2(Object obj, File file) {
        try {
            myObjectMapperNoPretty.writeValue(file, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson2(Object obj, Writer writer) {
        try {
            myObjectMapperNoPretty.writeValue(writer, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson2(Object obj, OutputStream out) {
        try {
            myObjectMapperNoPretty.writeValue(out, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static void toJson2(Object obj, DataOutput out) {
        try {
            myObjectMapperNoPretty.writeValue(out, obj);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }











    public static <T> T fromJson(JsonNode jsonNode, Class<T> tClass) {
        try {
            return myObjectMapper.convertValue(jsonNode, tClass);
        } catch (Exception var5) {
            throw new RuntimeException("Error", var5);
        }
    }


    public static <T> T fromJson(JsonNode jsonNode, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.convertValue(jsonNode, valueTypeRef);
        } catch (Exception var5) {
            throw new RuntimeException("Error", var5);
        }
    }


    public static <T> T fromJson(JsonNode jsonNode, JavaType valueType) {
        try {
            return myObjectMapper.convertValue(jsonNode, valueType);
        } catch (Exception var5) {
            throw new RuntimeException("Error", var5);
        }
    }


    public static JsonNode fromJsonToJsonNode(String string) {
        try {
            return myObjectMapper.readTree(string);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static JsonNode fromJsonToJsonNode(byte[] json) {
        try {
            return myObjectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static JsonNode fromJsonToJsonNode(InputStream src) {
        try {
            return myObjectMapper.readTree(src);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static JsonNode fromJsonToJsonNode(File file) {
        try {
            return myObjectMapper.readTree(file);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static JsonNode fromJsonToJsonNode(Reader reader) {
        try {
            return myObjectMapper.readTree(reader);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static JsonNode fromJsonToJsonNode(URL url) {
        try {
            return myObjectMapper.readTree(url);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }





    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(json, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(String json, JavaType valueType) {
        try {
            return myObjectMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }




    public static <T> T fromJson(byte[] json, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(json, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(byte[] json, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(byte[] json, JavaType valueType) {
        try {
            return myObjectMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }



    public static <T> T fromJson(InputStream src, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(src, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(InputStream src, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(src, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(InputStream src, JavaType valueType) {
        try {
            return myObjectMapper.readValue(src, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }



    public static <T> T fromJson(File file, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(file, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(File file, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(file, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(File file, JavaType valueType) {
        try {
            return myObjectMapper.readValue(file, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }



    public static <T> T fromJson(Reader reader, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(reader, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(Reader reader, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(reader, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(Reader reader, JavaType valueType) {
        try {
            return myObjectMapper.readValue(reader, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(URL url, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(url, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(URL url, TypeReference<T> valueTypeRef) {
        try {
            return myObjectMapper.readValue(url, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(URL url, JavaType valueType) {
        try {
            return myObjectMapper.readValue(url, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(DataInput dataInput, Class<T> tClass) {
        try {
            return myObjectMapper.readValue(dataInput, tClass);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static <T> T fromJson(DataInput dataInput, JavaType valueType) {
        try {
            return myObjectMapper.readValue(dataInput, valueType);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }









    public static Map<String, Object> jsonToMap(String json) {

        TypeReference<LinkedHashMap<String, Object>> typeRef
                = new TypeReference<LinkedHashMap<String, Object>>() {
        };

        try {
            return myObjectMapperToObjects.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static ArrayList<String> jsonToStringList(String json) {

        TypeReference<ArrayList<String>> typeRef
                = new TypeReference<ArrayList<String>>() {
        };

        try {
            return myObjectMapper.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static ArrayList<Long> jsonToLongList(String json) {

        TypeReference<ArrayList<Long>> typeRef
                = new TypeReference<ArrayList<Long>>() {
        };

        try {
            return myObjectMapperToObjects.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static Hashtable<String, Object> jsonToHashtable(String json) {
        TypeReference<Hashtable<String, Object>> typeRef
                = new TypeReference<Hashtable<String, Object>>() {
        };
        try {
            return myObjectMapperToObjects.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static HashMap<String, Object> jsonToHashMap(String json) {

        TypeReference<LinkedHashMap<String, Object>> typeRef
                = new TypeReference<LinkedHashMap<String, Object>>() {
        };
        try {
            return myObjectMapperToObjects.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static LinkedHashMap<String, Object> jsonToLinkedHashMap(String json) {

        TypeReference<LinkedHashMap<String, Object>> typeRef
                = new TypeReference<LinkedHashMap<String, Object>>() {};
        try {
            return myObjectMapperToObjects.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static Set<String> jsonKeySet(String json) {

        TypeReference<LinkedHashMap<String, Object>> typeRef
                = new TypeReference<LinkedHashMap<String, Object>>() {};
        try {
            LinkedHashMap<String, Object> linkedHashMap = myObjectMapperToObjects.readValue(json, typeRef);
            return linkedHashMap.keySet();
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }
    public static String[] jsonKeys(String json) {
        TypeReference<LinkedHashMap<String, Object>> typeRef
                = new TypeReference<LinkedHashMap<String, Object>>() {};
        try {
            LinkedHashMap<String, Object> map = myObjectMapperToObjects.readValue(json, typeRef);
            return map.keySet().toArray(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }


    public static ObjectNode toJsonNode(Object obj) {
        return myObjectMapper.convertValue(obj, ObjectNode.class);
    }






    public static JsonValue jsonToJakartaJsonValue(String string) {
        try {
            return myObjectMapper.readValue(string, JsonValue.class);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static JsonValue jsonToJakartaJsonValue(InputStream string) {
        try {
            return myObjectMapper.readValue(string, JsonValue.class);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }
    public static javax.json.JsonValue jsonToJsonObject(String string) {
        try {
            return myObjectMapper.readValue(string, javax.json.JsonValue.class);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public static javax.json.JsonValue jsonToJsonObject(InputStream string) {
        try {
            return myObjectMapper.readValue(string, javax.json.JsonValue.class);
        } catch (Exception e) {
            throw new RuntimeException(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    /**
     * Determines if a string is a uuid.
     *
     * @param uuid the uuid to check
     * @return true if the string is a uuid
     */
    public static boolean isUuid(String uuid) {
        boolean bIsUuid = false;
        uuid = removeCurlies(uuid);
        if (uuid.length() == 36) {
            String[] aParts = uuid.split("-");
            if (aParts.length == 5) {
                if ((aParts[0].length() == 8) && (aParts[1].length() == 4) &&
                        (aParts[2].length() == 4) && (aParts[3].length() == 4) &&
                        (aParts[4].length() == 12)) {
                    bIsUuid = true;
                }
            }
        }
        return bIsUuid;
    }

    /**
     * Removes curly braces {} from a uuid.
     *
     * @param uuid the uuid to modify
     * @return the modified uuid
     */
    public static String removeCurlies(String uuid) {
        uuid = chkStr(uuid);
        if (uuid.length() > 0) {
            if (uuid.startsWith("{")) uuid = uuid.substring(1);
            if (uuid.endsWith("}")) uuid = uuid.substring(0, uuid.length() - 1);
        }
        return uuid;
    }

    /**
     * Check a string value.
     *
     * @param s the string to check
     * @return the checked string (trimmed, zero length if the supplied String was null)
     */
    public static String chkStr(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }


}
