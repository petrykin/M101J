package com.mongodb.lessons.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.lessons.week2.DocumentTest.printJson;

public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("crudTest");

        System.out.println("Find one document in the crudTest collection:");
        Document first = coll.find().first();
        printJson(first);

        System.out.println("Find all documents in the crudTest collection:");
        List<Document> all = coll.find().into(new ArrayList<Document>());
        for (Document document : all) {
            printJson(document);
        }

        System.out.println("Find all documents in the crudTest collection with iteration:");
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                printJson(cursor.next());
            }
        } finally {
            cursor.close();
        }

        System.out.print("Collections size: ");
        long size = coll.count();
        System.out.println(size);
    }
}
