/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewExtraction {

    /**
     * @param args
     */
    static ArrayList<String> reviews;

    static String getUrlSource(String url) throws IOException {
        System.setProperty("http.proxyHost", "172.31.1.4");
        System.setProperty("http.proxyPort", "8080");
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            a.append(inputLine);
            a.append("\n");
        }
        in.close();

        return a.toString();
    }

    static void GetReviews(String PID, String pageNo) throws IOException {
         String url = "http://www.amazon.com/product-reviews/" + PID
               + "/?ie=UTF8&showViewpoints=0&pageNumber=" + pageNo
             + "&sortBy=bySubmissionDateDescending";
        //String url = "http://www.amazon.in/product-reviews/" + PID + "/ref=cm_cr_dp_see_all_summary?ie=UTF8&showViewpoints=" + pageNo + "&sortBy=byRankDescending";
        System.out.println(url);
        String source = getUrlSource(url);
        //System.out.println(source);
    	/*String source = "<div class=\"reviewText\">Not much to say other than I LOVE this camera! :) " +
         "I got it when it was on sale around Christmas last year for super cheap. " +
         "So happy with it!</div><div class=\"reviewText\">Not much</div>";*/
        //Pattern pattern = Pattern.compile("<div class=\"reviewText\">(.*)?</div>");
        Pattern pattern = Pattern.compile("<div class=\"reviewText\">(.*?)</div>");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            reviews.add(matcher.group(1) + "\n");

        }
        
    }

    public void file(String s1) throws IOException {
        System.out.println(s1);
        reviews = new ArrayList<String>();
        for (int i = 1; i <= 4; i++)
            GetReviews(s1, Integer.toString(i));
        for (int i = 0; i < reviews.size(); i++) {
            //System.out.println(reviews.get(i));
            File file1 = new File("C:\\Users\\shubham_15294\\Desktop\\review.txt");



            // if file doesnt exists, then create it
            if (!file1.exists()) {
                file1.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(file1, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(reviews.get(i));
            bufferWritter.newLine();
            bufferWritter.newLine();
            bufferWritter.close();
            // bufferWritter.close();
        }
    }

public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
}
}
