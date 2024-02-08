package mysearchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Perform {
	public List<SearchResult> searchDocuments(String folderPath, String searchWord) {
        List<SearchResult> searchResults = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        
        for (File file : files) {
            if (file.isFile()) {
            	SearchResult res = performTask(file, searchWord);
            	if (res != null)
            		searchResults.add(res);
            }
        }
        return searchResults;
    }

	public SearchResult performTask(File file, String searchWord) {
		int count = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            	//System.out.println("line: " + line);
            	//System.out.println("line.replaceAll: " + line.replaceAll("[^a-zA-Z ]", "") + "\n");
            	String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            	//String[] words = line.split("\\s+");
                for (String word : words) 
                    if (word.equals(searchWord))
                        count++;  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count > 0) 
            return new SearchResult(file.getName(), count);
        
		return null;
	}
	
    public void displayResults(List<SearchResult> searchResults) {
        System.out.println("Search Results:");
        if (searchResults.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (SearchResult result : searchResults) {
                System.out.println("Document: " + result.getDocumentName());
                System.out.println("Count: " + result.getCount());
                System.out.println("--------------------");
            }
        }
    }

    public void saveResultsToFile(String searchWord, List<SearchResult> searchResults) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results\\results.txt", true))) {
            writer.println("Search Word: " + searchWord);
            writer.println();

            if (searchResults.isEmpty()) {
                writer.println("No results found.");
                writer.println();
                writer.println("/////////////////");
                writer.println();
            } else {
                for (SearchResult result : searchResults) {
                    writer.println("Document: " + result.getDocumentName());
                    writer.println("Count: " + result.getCount());
                    writer.println("--------------------");
                }
                writer.println();
                writer.println("/////////////////");
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Search results appended to 'results.txt' file.");
    }
    
}
