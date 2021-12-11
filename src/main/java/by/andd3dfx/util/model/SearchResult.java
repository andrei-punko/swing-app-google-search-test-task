package by.andd3dfx.util.model;

import java.util.List;

public class SearchResult {

    private List<SearchResultItem> searchResultItems;

    public SearchResult(List<SearchResultItem> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    public List<SearchResultItem> getSearchResultItems() {
        return searchResultItems;
    }
}
