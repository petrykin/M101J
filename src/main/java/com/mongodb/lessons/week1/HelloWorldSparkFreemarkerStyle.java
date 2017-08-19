package com.mongodb.lessons.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "Freemarker");
                    helloTemplate.process(helloMap, writer);
                    System.out.println(writer);
                    return writer;
                } catch (Exception e) {
                    halt(500);
                    return null;
                }
            }
        });

        Spark.get("/test", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Test page";
            }
        });

        Spark.get("/echo/:thing", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return request.params(":thing");
            }
        });
    }
}
