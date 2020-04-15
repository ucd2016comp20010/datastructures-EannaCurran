package projectCode20280.Practical7;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class wordCounter {

	public static void main(String[] args) {

		ChainHashMap<String, Integer> wordHashMap = new ChainHashMap<>();
		ArrayList<String> words = new ArrayList();
		InputStream input = wordCounter.class.getResourceAsStream("/sample_text.txt");
		Scanner scanner = new Scanner(input);

		while(scanner.hasNext()){

			String temp = scanner.next();

			if(wordHashMap.get(temp) == null){
				wordHashMap.put(temp, 1);
				words.add(temp);
			}

			else{
				Integer oldCount = wordHashMap.get(temp);
				wordHashMap.put(temp, oldCount+1);
			}
		}

		for(int i =0; i < 10; i++){

			String tempString = "";
			int tempIndex = 0;

			for(String temp: words){
				if(wordHashMap.get(temp) > tempIndex){
					tempIndex = wordHashMap.get(temp);
					tempString = temp;
				}
			}

			System.out.println("Word: " + tempString + " - Occurrences: " + tempIndex);
			wordHashMap.remove(tempString);
			words.remove(tempString);
		}
	}
}
