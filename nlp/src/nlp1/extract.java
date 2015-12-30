/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp1;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.FileNotFoundException;



public class extract {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
    }
    public void m(String get) throws FileNotFoundException, IOException, ClassNotFoundException {
        //System.out.println(get);
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\shubham_15294\\Desktop\\review.txt"));
        String line;
        String line1 = "", line2 = "";
        HashMap<String, Integer> m = new HashMap<String, Integer>();
        //System.out.println("dhruv");
        MaxentTagger tagger = new MaxentTagger("C:\\Users\\shubham_15294\\Downloads\\taggers\\left3words-wsj-0-18.tagger");
        while ((line = br.readLine()) != null) {
            line1 = "";
            StringTokenizer t = new StringTokenizer(line);
            while (t.hasMoreTokens()) {
                String word = t.nextToken();
                if (!word.contains("#")) {
                    //System.out.print(word+ " ");
                    line1 += word;
                    //System.out.println(line1+ " hello");
                    line1 += " ";
                }
            }
            line2 = line2 + line1;
            //System.out.println(line1);
            String tagged = tagger.tagString(line1);
            
            File file = new File("C:\\Users\\shubham_15294\\Desktop\\nlp\\review.txt");

                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fileWritter = new FileWriter(file, true);
                    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                    bufferWritter.write(tagged);
                    bufferWritter.newLine();
                    bufferWritter.close();
                    // bufferWritter.close();

            
            
            StringTokenizer tr = new StringTokenizer(tagged);

            while (tr.hasMoreTokens()) {
                String word = tr.nextToken();
                if (word.contains("/")) {
                    String[] word1 = word.split("/");
                    if (word1[1].equals("NN") || word1[1].equals("NNP")) {
                        if (m.containsKey(word1[0])) {
                            int zz = m.get(word1[0]);
                            m.remove(word1[0]);
                            m.put(word1[0], zz + 1);
                        } else {
                            m.put(word1[0], 1);
                        }


                        //System.out.println(word1[0]+ " " +m.get(word1[0])+ " ");
                    }
                }
            }
            //while(m.isEmpty()){
            //System.out.println("dhruv" + m.get("battery"));
            //}
            //System.out.println();
            //System.out.println(tagged);
        }
        List<MyEntity> list = new ArrayList<MyEntity>();
        Iterator entries = m.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            boolean flag = false;
            for(int i = 0; i < 10; i++)
            if(key.contains(Integer.toString(i)) || key.equalsIgnoreCase("Canon")){
                flag = true;
            }
            Integer value = (Integer) entry.getValue();
            if(flag == false)
            list.add(new MyEntity(value, key));
             //System.out.println("Key = " + key + ", Value = " + value);
        }
        Collections.sort(list, new MyComparator());
        Iterator entries1 = list.iterator();
        while (entries1.hasNext()) {
            MyEntity ob = (MyEntity) entries1.next();
            //System.out.println(ob.name + " "+ob.val);
        }

        br.close();
        List<String> list1 = new ArrayList<String>();
        Iterator entries2 = list.iterator();
        for (int i = 0; i < 5; i++) {
            if (entries2.hasNext()) {
                MyEntity ob = (MyEntity) entries2.next();
                list1.add(ob.name);
                //System.out.println(ob.name + " "+ob.val);
            }
        }
        String arr1 = "C:\\Users\\shubham_15294\\Desktop\\nlp\\";
        String[] arr = line2.split("\\.");
        //System.out.println(arr.length);
        for (String s : arr) {
            //System.out.println(s);

            for (int i = 0; i < 5; i++) {
                if (s.contains(list1.get(i))) {
                    File file = new File(arr1 + list1.get(i) + ".txt");

                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fileWritter = new FileWriter(file, true);
                    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                    bufferWritter.write(tagger.tagString(s));
                    bufferWritter.newLine();
                    bufferWritter.close();
                    // bufferWritter.close();

                }
            }
        }
        Print p = new Print();
        for (int i = 0; i < 5; i++) {
           p.text(list1.get(i));
        }
    }
}



