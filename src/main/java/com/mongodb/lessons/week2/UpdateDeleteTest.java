package com.mongodb.lessons.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.lessons.week2.DocumentTest.printJson;

public class UpdateDeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("test");
        coll.drop();

        for (int i = 0; i < 8; i++) {
            coll.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i)
                    .append("y", true));
        }

        /*coll.replaceOne(eq("x", 5),
                new Document("x", 20).append("updated", true));*/
        /*coll.updateOne(eq("x",5), new Document("$set",
                new Document("x", 20).append("updated", true)));*/
        coll.updateOne(eq("_id", 9), combine(set("x", 20), set("updated", true)),
                new UpdateOptions().upsert(true));
        coll.deleteMany(combine(gt("_id", 2), lt("_id", 6)));

        for (Document document : coll.find().into(new ArrayList<>())) {
            printJson(document);
        }
    }
}
