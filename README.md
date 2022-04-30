# Commons Json Utils
Utilities for serialize and deserialize from JSON using Jackson

[![javadoc](https://javadoc.io/badge2/com.jarcasting/commons-json-utils/javadoc.svg)](https://javadoc.io/doc/com.jarcasting/commons-json-utils)
![Maven Central](https://img.shields.io/maven-central/v/com.jarcasting/commons-json-utils?style=flat-square)


## Maven

Functionality of this package is contained in Java package `com.fasterxml.jackson.databind`, and can be used using following Maven dependency:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.jarcasting</groupId>
    <artifactId>commons-json-utils</artifactId>
    <version>1.0.4</version>
  </dependency>
  ...
</dependencies>
```

Depends on: 

* `com.fasterxml.jackson.core : jackson-databind`
* `com.fasterxml.jackson.module : jackson-module-parameter-names`
* `com.fasterxml.jackson.datatype : jackson-datatype-jdk8`
* `com.fasterxml.jackson.datatype : jackson-datatype-jsr310`
* `com.fasterxml.jackson.datatype : jackson-datatype-jsr353`
* `org.apache.commons : commons-lang3`










## Examples




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



### Override Object.toString()

```java
    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
```


License
=======
Licensed under the [Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)

---

[JarCasting.ru - поиск по центральному Maven-репозиторию](https://jarcasting.ru)

[JarCasting.com - Unofficial Maven Central Search Engine](https://jarcasting.com)