package org.web.page;

import org.web.page.analyzer.Analyzer;
import org.web.page.parser.Parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class of the program
 */
public class Application {
    public static void main(String[] args)
    {
        String fileName = "downloaded.html";

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> countedWords;
        Iterator<Map.Entry<String, Integer>> iterator;
        Analyzer analyzer;
        Parser parser;
        String url;
        String content ="";

        //Getting an URL and downloading a page
        parser = new Parser();
        while (true)  {
            System.out.println("\n Enter a Page's URL");
            url = scanner.nextLine();
            try {
                parser.DownloadPage(url,fileName);
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your link. \n Type 'q' to exit \n Press any key... ");
                if (scanner.nextLine().equals("q")) return;
            }
        }
        System.out.println("Downloaded");

        //Parsing the page
        System.out.println("Parsing the page...");
        while (true) {
            try {
                content = parser.Parse("Downloaded.html");
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your downloaded file. \n Type 'q' to exit \n Press any key... ");
                if (scanner.nextLine().equals("q")) return;
            }
            System.out.println(content);
            System.out.println("Counting...");
        }

        //Counting words
        analyzer = new Analyzer();
        countedWords = analyzer.CountWords(content);
        iterator = countedWords.entrySet().iterator();

        System.out.println("\nWords | Count");
        while (iterator.hasNext()){
            Map.Entry<String, Integer> element = iterator.next();
            System.out.println(element.getKey() +"-"+element.getValue());
        }
    }
}