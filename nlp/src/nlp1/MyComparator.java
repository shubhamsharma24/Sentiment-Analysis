/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp1;

/**
 *
 * @author shubham_15294
 */
import java.util.Comparator;


public class MyComparator implements Comparator<MyEntity>{
	  public int compare(MyEntity ob1, MyEntity ob2){
		   return ob2.val - ob1.val ;
	  }
}