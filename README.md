### Base64 Encoding

Reference: [Base64 Encoding](https://effectivesquid.tistory.com/entry/Base64-%EC%9D%B8%EC%BD%94%EB%94%A9%EC%9D%B4%EB%9E%80)

Usage
```java
void encode() {
    //given
    Base64 sut = SimpleBase64.getInstance()
    String input = "f-lab";

    //when
    String actual = sut.encode(input, StandardCharsets.UTF_8);

    //then
    Assertions.assertEquals("Zi1sYWI=", actual);
}

void decode() {
    //given
    Base64 sut = SimpleBase64.getInstance()
    String input = "Zi1sYWI=";

    //when
    String actual = sut.decode(input, StandardCharsets.UTF_8);

    //then
    Assertions.assertEquals("f-lab", actual);
}
```