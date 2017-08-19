package com.mongodb.lessons.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.lessons.week2.DocumentTest.printJson;

public class FindWithSortSkipLimitTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("test");
        coll.drop();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coll.insertOne(new Document("x", i).append("y", j));
            }
        }

        Bson projection = fields(include("x", "y"), excludeId());
        //Bson sort = new Document("x", 1).append("y", -1);
        Bson sort = orderBy(ascending("x"), descending("y"));

        List<Document> all = coll.find()
                .projection(projection)
                .sort(sort)
                .skip(10)
                .limit(20)
                .into(new ArrayList<>());

        for (Document document : all) {
            printJson(document);
        }
    }
}
