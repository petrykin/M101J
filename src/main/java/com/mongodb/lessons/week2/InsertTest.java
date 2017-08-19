package com.mongodb.lessons.week2;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("crudTest");
        coll.drop();

        Document one = new Document("name", "Sergey")
                .append("age", 31)
                .append("profession", "programmer");
        coll.insertOne(one);

        Document two = new Document("name", "Ann")
                .append("age", 28)
                .append("profession", "teacher");

        Document three = new Document("name", "Artem")
                .append("age", 40)
                .append("profession", "masseur");

        coll.insertMany(asList(two, three));
    }
}
