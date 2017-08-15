package com.mongodb.lessons;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Map<String, Object> fruitMap = new HashMap<>();
                    fruitMap.put("fruits", Arrays.asList("apple", "orange", "peach", "banana"));
                    Template fruitTemplate = configuration.getTemplate("fruits.ftl");

                    fruitTemplate.process(fruitMap, writer);
                    System.out.println(writer);
                    return writer;
                } catch (Exception e) {
                    halt(500);
                    return null;
                }
            }
        });

        Spark.post("/favourite_fruit", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Why don't you pick one?";
                } else {
                    return "Your favourite fruit is " + fruit;
                }
            }
        });
    }
}
