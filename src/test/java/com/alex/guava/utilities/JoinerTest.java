package com.alex.guava.utilities;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static com.google.common.collect.ImmutableMap.of;
import static org.testng.Assert.*;

public class JoinerTest {

    private final List<String> stringList = Arrays.asList("Google", "Java", "Scala", "Kafka");

    private final List<String> stringListWithNullValue = Arrays.asList("Google", "Java", "Scala", null);

    private final Map<String, String> stringMap = of("Hello", "Guava", "Java", "Scala");

    private final String targetFileName = "/Users/zhixihu/IdeaProjects/GuavaExercise/src/test/java/com/alex/utilities/JoinerTest.txt";
    private final String targetFileNameToMap = "/Users/zhixihu/IdeaProjects/GuavaExercise/src/test/java/com/alex/utilities/JoinerTest2.txt";

    @Test
    public void testJoinOn_Join() {
        String result = Joiner.on("#").join(stringList);
        assertEquals(result,"Google#Java#Scala#Kafka");
    }

    @Test
    public void testJoinOn_JoinWithNullValue() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertEquals(result,"Google#Java#Scala");
    }

    @Test
    public void testJoinOn_JoinWithNullValue_UseDefaultValue() {
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        assertEquals(result,"Google#Java#Scala#DEFAULT");
    }

    @Test
    public void testJoinOn_AppendToStringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder stringBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder, stringListWithNullValue);
        assertSame(builder, stringBuilder);
        assertEquals(stringBuilder.toString(), "Google#Java#Scala#DEFAULT");
        assertEquals(builder.toString(),"Google#Java#Scala#DEFAULT");
    }

    @Test
    public void testJoinOn_AppendToWriter() {
        try {
            FileWriter writer = new FileWriter(new File(targetFileName));
            Joiner.on("#").useForNull("DEFAULT").appendTo(writer, stringListWithNullValue);
            assertTrue(Files.isFile().test(new File(targetFileName)));

        } catch (IOException e) {
            fail("append to writer occur fetal error");
        }
    }

    @Test
    public void testJoiningByStream_SkipNullValues() {
        String result = stringListWithNullValue.stream()
                .filter(item -> item != null && !item.isEmpty())
                .collect(Collectors.joining("#"));
        assertEquals(result, "Google#Java#Scala");
    }

    @Test
    public void testJoiningByStream_WithDefaultValue() {
        String result = stringListWithNullValue.stream()
                .map(item -> item == null || item.isEmpty() ? "DEFAULT" : item).collect(Collectors.joining("#"));

        assertEquals(result, "Google#Java#Scala#DEFAULT");
    }

    @Test
    public void testJoiningByStream_WithDefaultValue_FunctionalStyle() {
        String result = stringListWithNullValue.stream().map(this::defaultValue).collect(Collectors.joining("#"));

        assertEquals(result, "Google#Java#Scala#DEFAULT");
    }
    private String defaultValue(final String item) {
        return item == null || item.isEmpty() ? "DEFAULT" : item;
    }

    @Test
    public void testJoinOnWithMap() {
        String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
        assertEquals(result,"Hello=Guava#Java=Scala");
    }

    @Test
    public void testJoinOnWithMapToAppendable() {
        try {
            FileWriter writer = new FileWriter(new File(targetFileNameToMap));
            Joiner.on("#").withKeyValueSeparator("=").appendTo(writer, stringMap);
            assertTrue(Files.isFile().test(new File(targetFileNameToMap)));
        } catch (IOException e) {
            fail("append to writer occur fetal error");
        }
    }
}
