package by.andd3dfx.ui;

import by.andd3dfx.util.SearchHelper;
import by.andd3dfx.util.model.SearchResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class SearchEventListener implements ActionListener {

    private static final int RESULTS_PER_PAGE = 10;
    private final Supplier<String> searchTextSupplier;
    private final Function<SearchResult, Void> searchResultConsumer;

    public SearchEventListener(Supplier<String> searchTextSupplier, Function<SearchResult, Void> searchResultConsumer) {
        this.searchTextSupplier = searchTextSupplier;
        this.searchResultConsumer = searchResultConsumer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = searchTextSupplier.get();

        CompletableFuture.runAsync(() -> {
            SearchResult result = SearchHelper.search(searchText, RESULTS_PER_PAGE);
            searchResultConsumer.apply(result);
        });
    }
}
