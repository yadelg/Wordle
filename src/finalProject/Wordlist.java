package finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Wordlist {
	private String[] list;
	private List<String> listOfWords; 

	/*
	 * Scans through and generates WordList from AllWords file and stores it in a list.
	 * The file was taken from the NYT wordlist.
	 */
	public  Wordlist() {
		File file = new File("C:\\Users\\gbky2\\eclipse-workspace\\finalProject\\src\\finalProject\\AllWords\\");
        Scanner sc;
        list = new String[2309];
		
        	try {
        		sc = new Scanner(file);
        		int i = 0;
					while(sc.hasNextLine()) {	
						list[i] = sc.nextLine().substring(3, 8);
						i++;
					}

					listOfWords = Arrays.asList(list);
        	} 
        	catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}
        	
        	
	
	}
	
	
	public List<String> getListofWords() {
		return listOfWords;
	}
	




	
	
}
