package com.jarcasting.commons.json;


import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Set;

public class JsonUtilsTest {

    static class MySimpleClass {

        int a;
        int b;

    }


    /**
     * json to Custom Object
     */
    @Test
    public void jsonToCustomObject() {

        MySimpleClass result = JsonUtils.fromJson("{\"a\":1,\"b\":2}", MySimpleClass.class);
        Assert.assertEquals(JsonUtils.toJson2(result), "{\"a\":1,\"b\":2}");

    }


    static class MyGenericClass<T> {
        T value;
    }


    /**
     * json to Generic Object
     */
    @Test
    public void jsonToGenericObject() {

        MyGenericClass<MySimpleClass> result = JsonUtils.fromJson(
                "{\"value\":{\"a\":1,\"b\":2}}",
                new TypeReference<MyGenericClass<MySimpleClass>>() {
                }
        );

        Assert.assertEquals(JsonUtils.toJson2(result), "{\"value\":{\"a\":1,\"b\":2}}");

    }


    enum MyEnum {
        A, B
    }

    enum MyEnum2 {

        A(87),
        B(89);

        int myField;

        MyEnum2(int myField) {
            this.myField = myField;
        }

    }

    /**
     * json to Enum
     */
    @Test
    public void jsonToEnum() {
        MyEnum result = JsonUtils.fromJson(
                "\"A\"",
                MyEnum.class
        );
        MyEnum2 result2 = JsonUtils.fromJson(
                "\"B\"",
                MyEnum2.class
        );
        Assert.assertEquals(JsonUtils.toJson2(result), "\"A\"");
        Assert.assertEquals(JsonUtils.toJson2(result2), "\"B\"");
    }

    /**
     * json to HashTable
     */
    @Test
    public void jsonToHashtable() {

        Hashtable<String, Object> expect = new Hashtable<String, Object>();
        expect.put("a",1);
        expect.put("b",2);
        Hashtable<String, Object> result = JsonUtils.jsonToHashtable("{\"a\":1,\"b\":2}");

        Assert.assertEquals(expect, result);

    }
    @Test
    public void jsonToHashtableTyped() {

        Hashtable<String, Integer> expect = new Hashtable<String, Integer>();
        expect.put("a",1);
        expect.put("b",2);

        Hashtable<String, Integer> result =
                JsonUtils.fromJson("{\"a\":1,\"b\":2}",
                        new TypeReference<Hashtable<String, Integer>>(){}
                );

        Assert.assertEquals(expect, result);

    }

    /**
     * json keyset
     */
    @Test
    public void jsonKeySet() {
        Set<String> result = JsonUtils.jsonKeySet("{\"a\":1,\"b\":2}");
        Assert.assertEquals("[\"a\",\"b\"]", JsonUtils.toJson2(result));
    }

    /**
     * json keys
     */
    @Test
    public void jsonKeys() {
        String[] result = JsonUtils.jsonKeys("{\"a\":1,\"b\":2}");
        Assert.assertEquals("[\"a\",\"b\"]", JsonUtils.toJson2(result));
    }

    /**
     * json to nested class
     */
    @Test
    public void jsonToNestedClass() {

    }

    /**
     * json node to object
     */
    @Test
    public void jsonNodeToObject() {

    }

    /**
     * jsonnode to map
     */
    @Test
    public void jsonNodeToMap() {

    }

    /**
     * json to 2d array
     */
    @Test
    public void jsonTo2dArray() {

    }

    /**
     * json to Object
     */
    @Test
    public void jsonToObject() {

    }

    /**
     * json to map
     */
    @Test
    public void jsonToMap() {

    }

    /**
     * json to hashmap
     */
    @Test
    public void jsonToHashMap() {

    }

    /**
     * json file to map
     */
    @Test
    public void jsonFileToMap() {

    }

    /**
     * json to key value map
     */
    @Test
    public void jsonToKeyValueMap() {

    }

    /**
     * json to linkedhashmap
     */
    @Test
    public void jsonToLinkedHashMap() {

    }

    /**
     * json to map string string
     */
    @Test
    public void jsonToMapStringString() {

    }


    /**
     * json to map list
     */
    @Test
    public void jsonToMapList() {

    }





    /**
     * json to pojo
     */
    @Test
    public void jsonToPogo() {

    }


    /**
     * json file to pojo
     */
    @Test
    public void jsonFileToPojo() {

    }


    /**
     * json to java object
     */
    @Test
    public void jsonToJavaObject() {

    }

    /**
     * json file to object
     */
    @Test
    public void jsonFileToObject() {

    }

    /**
     * json to class
     */
    @Test
    public void jsonToClass() {

    }

    /**
     * json to dto
     */
    @Test
    public void jsonToDto() {

    }




    /**
     * json string to object
     */
    @Test
    public void jsonStringToObject() {

    }

    /**
     * json to list
     */
    @Test
    public void jsonToList() {

    }

    /**
     * json to array
     */
    @Test
    public void jsonToArray() {

    }

    /**
     * json to arraylist
     */
    @Test
    public void jsonToArrayList() {

    }

    /**
     * json to array of objects
     */
    @Test
    public void jsonToArrayOfObjects() {

    }

    /**
     * json to array string
     */
    @Test
    public void jsonToArrayString() {

    }

    /**
     * json array to arraylist
     */
    @Test
    public void jsonArrayToArrayList() {

    }

    /**
     * json array to map
     */
    @Test
    public void jsonArrayToMap() {

    }

    /**
     * json to list of objects
     */
    @Test
    public void jsonToListOfObjects() {

    }

    /**
     * json to list string
     */
    @Test
    public void jsonToListString() {

    }

    /**
     * json to object list
     */
    @Test
    public void jsonToObjectList() {

    }

    /**
     * json to string array
     */
    @Test
    public void jsonToStringArray() {

    }

    /**
     * json to date
     */
    @Test
    public void jsonToDate() {

    }

    /**
     * json to datetime
     */
    @Test
    public void jsonToDateTime() {

    }

    /**
     * json deserialize zoneddatetime
     */
    @Test
    public void zoneDateTimeToJson() {

    }

    /**
     * json serialize zoneddatetime
     */
    @Test
    public void jsonToZoneDateTime() {

    }

    /**
     * json to localdate
     */
    @Test
    public void jsonToLocalDate() {

    }

    /**
     * json to localdatetime
     */
    @Test
    public void jsonToLocalDatetime() {

    }

    /**
     * json to string
     */
    @Test
    public void jsonToString() {

    }

    /**
     * json to key value pairs
     */
    @Test
    public void jsonToKeyValuePairs() {

    }

    /**
     * parse json to key value
     */
    @Test
    public void jsonToKeyValue() {

    }

    /**
     * json to collection
     */
    @Test
    public void jsonToCollection() {

    }

    /**
     * json to list map
     */
    @Test
    public void jsonToListMap() {

    }

    /**
     * convert json to query string
     */
    @Test
    public void jsonToQueryString() {

    }

    /**
     * json to url parameters
     */
    @Test
    public void jsonToUrlParameters() {

    }

    /**
     * to json example
     */
    @Test
    public void pojoToJson() {

    }

}
