package com.theironyard.charlotte;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static User user; // by default this is going to be null
    public static ArrayList<String> messages = new ArrayList<>(); // move into my user class
    public static HashMap<String, String> p = new HashMap(); // string and user as value,

    public static void main(String[] args) {

        p.put("Jennifer", "a");
        p.put("Alice", "b");
        p.put("Ben", "c");

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) { // checking if user is null.
                        return new ModelAndView(m, "index.html"); // if user is null, present them with login page
                    } else {
                        m.put("name", user.name); //if user is not null, take hashmap, take user name in that name key
                        m.put("messageList", messages);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post( // give server information.  info in this case is name of user trying to log in
                "/create-user", // when post data to this page, we're going to be doing what is below
                ((request, response) -> {
                    String name = request.queryParams("loginName"); // name variable assigning to loginName.  login Name
                    String password = request.queryParams("password");
                    // string matches up name of field that is part of the form
                    user = new User(name, password); // setting static user to new user object that has that name
                    p.put(user.name, user.getPassword());
                    if(p.get(user.name).equals(user.getPassword())) {
                        response.redirect("/");
                    } else {
                        user = null;
                        response.redirect("/");
                    }// then we're redirecting back to the homepage
                    return "";
                })
        );

        Spark.post( // give server information.  info in this case is name of user trying to log in
                "/create-message", // when post data to this page, we're going to be doing what is below
                (request, response) -> {
                    messages.add(request.queryParams("message")); // name variable assigning to loginName.  login Name
                    // string matches up name of field that is part of the form
                    //String messageString = messages.toString();
//                    for(int i = 0; i < messages.size(); i++) {
//                        m.put("messageList", messages.get(i));
//                    } // this doesn't work, because it replaces the previous messages
                    // m.put("messageList", String.join(System.lineSeparator(), messages));
                    response.redirect("/"); // then we're redirecting back to the homepage
                    return "";
                }
        );

//logout route to main.java, set user to null anchor tag in html

    }

}
