package com.mongodb.lessons.week2;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "MongoDB Hello")
                .append("int", 42)
                .append("l", 1L)
                .append("double", 1.1)
                .append("bool", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("document", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3, 4, 5));
        printJson(document);
    }

    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                                               new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
