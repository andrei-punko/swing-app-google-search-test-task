package by.andd3dfx.searchapp.search.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SearchResult {

    private final List<SearchResultItem> searchResultItems;
}
