package mysearchengine;

import java.util.List;
import java.util.Scanner;

public class SimpleSearchEngine {
    public static void main(String[] args) {
    	Perform p = new Perform();
        String folderPath = "C:\\Users\\Perdorues\\eclipse-workspace\\SearchEngine\\documents";

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the search word: ");
        String searchWord = sc.nextLine();
        sc.close();

        List<SearchResult> searchResults = p.searchDocuments(folderPath, searchWord);
        p.displayResults(searchResults);

        p.saveResultsToFile(searchWord, searchResults);
    }

}

