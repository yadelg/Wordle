package finalProject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Random;

public class Word {
	private String answer;
	
 public Word() {
	 this.answer = createWord();
 }
 
 public String getWord() {
	 return answer;
 }
 
 public static boolean isWord(String guess) {
	 Wordlist list = new Wordlist();
	 if (list.getListofWords().contains(guess)) {
		 return true;
	 }
	 return false;
 }


 private String createWord() {
	 answer = "";
	 Random rand = new Random();
	 Scanner sc;
	try {
		
		File file=new File("C:\\Users\\gbky2\\eclipse-workspace\\finalProject\\src\\finalProject\\AllWords\\");
        sc = new Scanner(file);
		int wordPosition = rand.nextInt(2309);
		//loop through until last word
		for(int i = 0; i < wordPosition; i++) {
			 if (i == wordPosition - 1) {
				 //update answer only if at the last word
				 answer = sc.nextLine();
			 }
			 else {
				 sc.nextLine();
			 }
		}
		
		answer = answer.substring(3, 8);
	} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
	

	 return answer;
 }
 
 
 public int getOccurences(String letter) {
	 int num = 0;
	 for(int i = 0; i < answer.length(); i++) {
		 if (String.valueOf(answer.charAt(i)).equals(letter)) {
			 num++;
		 }
	 }
	 return num;
 }
 // add key value and its occurences
 // [a->2, p->1, n-> 1, e->1]
 // [a, p, a, n, e]  answer
 // [a, p, a, n, a]  guess
 // [0, 0, 0, 0, X]  result
 // loop through the guess, if in right spot, make it 0 and decrement key (ONLY ONCE)
 //Compare answer to guess
 //Compare hashmap to guess
 //loop again to figure out any yellows
public ArrayList<Integer> correct(String guess) {
     HashMap<String, Integer> a = new HashMap<String, Integer>(); 
     ArrayList<Integer> b = new ArrayList<Integer>();
	 
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 a.put(current, getOccurences(String.valueOf(guess.charAt(i))));
	 }
	 
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 if (current.equals(String.valueOf(String.valueOf(answer.charAt(i))))) {
			 b.add(0);
				 a.replace(current, a.get(current) - 1);		 
		 }
		 else {
			 b.add(2);
		 }
	 }
	 
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 if (a.get(current) >= 1 && b.get(i) != 0) {
			 b.set(i, 1);
			 a.replace(current, a.get(current)- 1);
			}
		}
	 
	return b; 
 }
 
}
