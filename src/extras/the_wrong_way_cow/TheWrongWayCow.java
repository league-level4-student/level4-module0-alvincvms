//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.HashMap;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	
    	HashMap<Integer, Integer> N = new HashMap<Integer, Integer>(), S = new HashMap<Integer, Integer>(), E = new HashMap<Integer, Integer>(), W = new HashMap<Integer, Integer>();
    	//check for cows facing east or west
        for(int i = 0; i < field.length; i++) {
        	char[] s = field[i];
//        	System.out.println(new String(s));
        	for(int j = 0; j < s.length - "cow".length() + 1; j++) {
        		for(int n = 0; n < "cow".length(); n++) {
        			if(s[j + n] != "cow".charAt(n)) {
        				break;
        			}
        			if(n == "cow".length() - 1) {
        				W.put(j, i);
        				j += "cow".length();
        				break;
        			}
        		}
        	}
        	for(int j = s.length - 1; j >= "cow".length() - 1; j--) {
        		for(int n = 0; n < "cow".length(); n++) {
        			if(s[j - n] != "cow".charAt(n)) {
        				break;
        			}
        			if(n == "cow".length() - 1) {
        				E.put(j, i);
        				j -= "cow".length();
        				break;
        			}
        		}
        	}
        }
        
        //check for cows facing north or south
        for(int i = 0; i < field[0].length; i++) {
        	for(int j = 0; j < field.length - "cow".length() + 1; j++) {
        		for(int n = 0; n < "cow".length(); n++) {
        			if(field[j + n][i] != "cow".charAt(n)) {
        				break;
        			}
        			if(n == "cow".length() - 1) {
        				N.put(i, j);
        				j += "cow".length();
        				break;
        			}
        		}
        	}
        	for(int j = field.length - 1; j >= "cow".length() - 1; j--) {
        		for(int n = 0; n < "cow".length(); n++) {
        			if(field[j - n][i] != "cow".charAt(n)) {
        				break;
        			}
        			if(n == "cow".length() - 1) {
        				S.put(i, j);
        				j -= "cow".length();
        				break;
        			}
        		}
        	}
        }
//      System.out.println(wCows + ", " + eCows + ", " + sCows + ", " + nCows);
        if(N.size() == 1) {
        	System.out.println((int) N.keySet().toArray()[0] + ", " + (int) N.values().toArray()[0]);
        	return new int[] {(int) N.keySet().toArray()[0], (int) N.values().toArray()[0]};
        }
        if(S.size() == 1) {
        	return new int[] {(int) S.keySet().toArray()[0], (int) S.values().toArray()[0]};
        }
        if(E.size() == 1) {
        	return new int[] {(int) E.keySet().toArray()[0], (int) E.values().toArray()[0]};
        }
        if(W.size() == 1) {
        	return new int[] {(int) W.keySet().toArray()[0], (int) W.values().toArray()[0]};
        }
        return new int[] {-1, -1};
    }
}
