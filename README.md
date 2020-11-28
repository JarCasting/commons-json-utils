# Commons Json Utils
Utilities for serialize and deserialize from JSON using Jackson

[![javadoc](https://javadoc.io/badge2/com.jarcasting/commons-json-utils/javadoc.svg)](https://javadoc.io/doc/com.jarcasting/commons-json-utils)



##Examples




### JSON to POJO

* #### json to Custom Object
    ```java
  
        class MyClass {
            int a;
            int b;
        }
  
  
        MyClass result = 
            JsonUtils.fromJson(
                   "{\"a\":1,\"b\":2}", 
                   MyClass.class
            );
    ```
  

* #### json to Enum
    ```java

        enum MyEnum {
            A, B
        }
        enum MyEnum2 {
    
            A(87),
            B(89);
    
            int myField;
    
            MyEnum2(int myField) {
                this.myField=myField;
            }
    
        }
    
        public void jsonToEnum(){
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

    ```
  
* #### json to Generic Object
    ```java
        static class MyGenericClass<T> {
            T value;
        }
        
        public void jsonToGenericObject() throws Exception {
    
            MyGenericClass<MySimpleClass> result = JsonUtils.fromJson(
                    "{\"value\":{\"a\":1,\"b\":2}}",
                    new TypeReference<MyGenericClass<MySimpleClass>>() { }
            );
    
            Assert.assertEquals(JsonUtils.toJson2(result), "{\"value\":{\"a\":1,\"b\":2}}");
    
        }
    ```

* #### json to HashTable
    ```java
        Hashtable<String, Integer> result =
                JsonUtils.fromJson("{\"a\":1,\"b\":2}",
                        new TypeReference<Hashtable<String, Integer>>(){}
                );
    ```

* #### convert json to Key Value Pairs
    ```java
        // TODO

    ```

* #### json keyset
    ```java
        Set<String> result = 
            JsonUtils.jsonKeySet("{\"a\":1,\"b\":2}");
    ```

* #### json keys
    ```java
        String[] result = 
            JsonUtils.jsonKeys("{\"a\":1,\"b\":2}");
    ```



###Override Object.toString()

```java
    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
```


