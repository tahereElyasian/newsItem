package org.example;

import org.example.model.NewsItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.in;

public class NewsAnalyzer {

    private static final Set<String> POSITIVE_WORDS = Set.of("up", "rise", "good", "success", "high");
    private static Stream<String> news = Stream.of("up", "rise", "good", "success", "high");
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("News Analyzer listening on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new NewsHandler(socket)).start();
        }
    }


    static class NewsHandler implements Runnable {
        private Socket socket;

        public NewsHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                //Process incoming messages here (see below)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private final Queue<NewsItem> recentNews = new LinkedList<>();



        String line ;

    /*while((line!=null) {
            String[] parts = line.split(",");
            String headline = parts[0];
            int priority = Integer.parseInt(parts[1]);
            boolean positive = isPositive(headline);
            if (positive) {
                recentNews.offer(new NewsItem(headline, priority));
            }

            //Summary every 10 seconds
            try {
                TimeUnit.SECONDS.sleep(10);
                summarizeNews(recentNews);
                recentNews.clear();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }*/


        private static boolean isPositive(String headline) {
            String[] words = headline.split("\s+");
            int positiveCount = 0;
            for (String word : words) {
                if (POSITIVE_WORDS.contains(word)) {
                    positiveCount++;
                }
            }

            double v = words.length / 2.0;
            return false;
           // return ((boolean) positiveCount);
        }


        private static void summarizeNews(Queue queue) {

        List<NewsItem> newsItems;
            long positiveCount = POSITIVE_WORDS.stream().count();

         //    POSITIVE_WORDS.stream().sorted(Comparator.comparingInt(NewsItem::getPriority)).distinct().limit(3).collect(Collectors.toList());


            System.out.println("Positive news items in last 10 seconds: " + positiveCount);
           // System.out.println("Top 3 headlines: " + POSITIVE_WORDS.stream().map(NewsItem::getHeadline)).collect(Collectors.joining(", "));
        }
    }
    }



