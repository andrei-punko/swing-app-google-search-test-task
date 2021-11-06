package by.andd3dfx.search;

import by.andd3dfx.search.dto.SearchResultItem;
import java.util.List;

public interface PostSearchActionRunner {

    void searchPerformed(List<SearchResultItem> searchResultItems);
}
