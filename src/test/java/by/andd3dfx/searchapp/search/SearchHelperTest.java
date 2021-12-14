package by.andd3dfx.searchapp.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.searchapp.search.model.SearchResult;
import org.junit.Before;
import org.junit.Test;

public class SearchHelperTest {

    private SearchHelper searchHelper;

    @Before
    public void setUp() {
        searchHelper = new SearchHelper();
    }

    @Test
    public void search() {
        SearchResult result = searchHelper.search("Andrei", 10);

        assertThat(result.getSearchResultItems().size(), is(10));
    }
}
