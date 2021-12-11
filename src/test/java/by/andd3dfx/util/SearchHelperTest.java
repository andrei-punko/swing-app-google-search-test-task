package by.andd3dfx.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.util.model.SearchResult;
import org.junit.Test;

public class SearchHelperTest {

    @Test
    public void search() {
        SearchResult result = SearchHelper.search("Andrei", 10);
        assertThat(result.getSearchResultItems().size(), is(10));
    }
}
