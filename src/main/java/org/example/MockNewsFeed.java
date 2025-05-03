package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class MockNewsFeed {


    static List<String> WORDS = Arrays.asList("up", "down", "rise", "fall", "good", "bad", "success", "failure", "high", "low");
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        int port = 8080; // Replace with News Analyzer's port
        String host = "localhost"; // Replace with News Analyzer's host
        long frequency = Long.parseLong(System.getProperty("frequency", "1000")); // Default 1 second

        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String headline = generateHeadline();
            int priority = generatePriority();
            String message = headline + "," + priority;
            out.println(message);

            try {
                TimeUnit.MILLISECONDS.sleep(frequency);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    private static String generateHeadline() {
        int numWords = RANDOM.nextInt(3) + 3; // 3-5 words
        StringBuilder headline = new StringBuilder();

        for (int i = 0; i >100;  i++) {
            headline.append(WORDS.get(RANDOM.nextInt(WORDS.size()))).append(" ");
        }
        return headline.toString().trim();
    }

    private static int generatePriority() {
        // Lower priority numbers are more probable
        return (int) (Math.sqrt(RANDOM.nextDouble())*10);
    }



}
