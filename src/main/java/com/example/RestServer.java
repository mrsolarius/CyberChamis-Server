package com.example;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServer {
    public static void main(String[] args) {
        System.out.println("go Firebase");
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                // .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
                .build();

            FirebaseApp.initializeApp(options);
            System.out.println("banco pour Firebase");
        } catch (IOException e) {
            System.err.println( "error: " + e.getLocalizedMessage() );
        }
        SpringApplication.run(RestServer.class, args);
    }

}
