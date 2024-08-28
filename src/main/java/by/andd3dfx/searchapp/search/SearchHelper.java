package by.andd3dfx.searchapp.search;

import by.andd3dfx.searchapp.search.model.SearchResultItem;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
public class SearchHelper {

    private final String SEARCH_URL = "https://www.google.com/search?q=%s&start=%d";
    private final String CHARSET = "UTF-8";
    private final String USER_AGENT = "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0";

    /**
     * For search implementation next link was useful:
     * <p>
     * <a href="http://stackoverflow.com/questions/3727662/how-can-you-search-google-programmatically-java-api">Call Google API programmatically</a>
     */
    public List<SearchResultItem> search(String searchString, int maxResults) {
        try {
            var resultItems = new LinkedHashSet<SearchResultItem>();
            int offset = 0;
            while (resultItems.size() < maxResults) {
                Elements elements = searchLinks(searchString, offset);
                if (elements.isEmpty()) {
                    break;
                }
                resultItems.addAll(batchSearch(elements));
                offset += maxResults;
            }

            return resultItems.stream()
                    .limit(maxResults)
                    .toList();
        } catch (Exception e) {
            log.error("Error during search occurs", e);
            return List.of();
        }
    }

    private List<SearchResultItem> batchSearch(Elements elements) {
        List<SearchResultItem> result = new ArrayList<>();
        for (Element link : elements) {
            SearchResultItem searchResultItem = extractSearchResultItemFromLink(link);
            if (searchResultItem != null) {
                result.add(searchResultItem);
            }
        }
        return result;
    }

    private Elements searchLinks(String searchString, int offset) throws Exception {
        String keyword = URLEncoder.encode(searchString, CHARSET);
        String url = String.format(SEARCH_URL, keyword, offset);
        return Jsoup
                .connect(url)
                .userAgent(USER_AGENT)
                .get().select(".g a");
    }

    private SearchResultItem extractSearchResultItemFromLink(Element link) {
        String title = link.text();

        String absoluteUrl = link.absUrl("href");
        if (absoluteUrl.startsWith("https://translate.google.com")) {
            return null;
        }

        return new SearchResultItem(absoluteUrl, title);
    }
}
