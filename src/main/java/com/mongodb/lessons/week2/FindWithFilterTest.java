package com.mongodb.lessons.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.lessons.week2.DocumentTest.printJson;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("crudTest");

        //Bson filter = new Document("age", new Document("$lt", 40).append("$gt", 30));
        Bson filter = and(eq("name", "Sergey"),
                gt("age", 30),
                lt("age", 40));

        //Bson projection = new Document("name", 0).append("_id", 0);
        Bson projection = fields(excludeId(), include("age", "name"));

        System.out.print("Count of filtered documents: ");
        ArrayList<Document> into = coll.find(filter).projection(projection).into(new ArrayList<Document>());
        System.out.println(into.size());
        for (Document document : into) {
            printJson(document);
        }
    }
}
