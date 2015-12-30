/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shubham_15294
 */
public class Score {
    HashMap <String, Double> verb = new HashMap<String, Double>();
    HashMap <String, Double> adj = new HashMap<String, Double>();
    HashMap <String, Double> adv = new HashMap<String, Double>();
    
    public static void main(String []args) throws FileNotFoundException, IOException{
        Score obj = new Score();
        obj.ScoreCal();
        obj.sentencecalculate("C:\\Users\\shubham_15294\\Desktop\\nlp\\review.txt");
        
        
    }
    
    
public void ScoreCal() throws FileNotFoundException, IOException{
    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\shubham_15294\\Desktop\\nlp\\verb.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line);
                String word = (String) st.nextElement();
                Double count = Double.parseDouble((String) st.nextElement());
                //System.out.println(word + ":" + count);
                if (verb.get(word) == null)
                    verb.put(word, count);
                else if (verb.get(word) != null && verb.get(word) < count) verb.put(word, count);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        br = new BufferedReader(new FileReader("C:\\Users\\shubham_15294\\Desktop\\nlp\\adj.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line);
                String word = (String) st.nextElement();
                Double count = Double.parseDouble((String) st.nextElement());
                if (adj.get(word) == null)
                    adj.put(word, count);
                else if (adj.get(word) != null && adj.get(word) < count) adj.put(word, count);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        br = new BufferedReader(new FileReader("C:\\Users\\shubham_15294\\Desktop\\nlp\\adv.txt"));
        try {
            String line = br.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line);
                String word = (String) st.nextElement();
                Double count = Double.parseDouble((String) st.nextElement());
                if (adv.get(word) == null)
                    adv.put(word, count);
                else if (adv.get(word) != null && adv.get(word) < count) adv.put(word, count);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        /*Set set = verb.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            //System.out.print(me.getKey() + ": ");
            //System.out.println(me.getValue());
        }*/
    }    
    

public void sentencecalculate(String s) throws FileNotFoundException, IOException {
    
    BufferedReader br = new BufferedReader(new FileReader(s));
        
        double num = 0, den = 0;
        double ans = 0.2;
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer tr = new StringTokenizer(line);
            while (tr.hasMoreTokens()) {
                String word = tr.nextToken();
                if (word.contains("/")) {
                    String[] word1 = word.split("/");
                    if (word1[1].equals("JJ") || word1[1].equals("VB") || word1[1].equals("RB")) {
                        if (verb.containsKey(word1[0])) {
                            num = num + verb.get(word1[0]);
                            den = den + Math.abs(verb.get(word1[0]));
                        } else if(adj.containsKey(word1[0])) {
                            num = num + adj.get(word1[0]);
                            den = den + Math.abs(adj.get(word1[0]));
                        } else if(adv.containsKey(word1[0])) {
                            num = num + adv.get(word1[0]);
                            den = den + Math.abs(adv.get(word1[0]));
                        }
                        
                    }
                }
            }
       }
        //System.out.println(num + " " + den);
        ans += (num/den + 1.0) * (5 / 2.0);
        System.out.println(ans);
    }
}
