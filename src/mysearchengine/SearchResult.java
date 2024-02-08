package mysearchengine;

public class SearchResult {
    public final String documentName;
    public final int count;

    public SearchResult(String documentName, int count) {
        this.documentName = documentName;
        this.count = count;
    }

    public String getDocumentName() {
        return documentName;
    }

    public int getCount() {
        return count;
    }
}
