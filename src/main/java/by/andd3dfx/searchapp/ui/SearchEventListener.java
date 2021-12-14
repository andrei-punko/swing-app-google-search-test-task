package by.andd3dfx.searchapp.ui;

import by.andd3dfx.searchapp.search.SearchHelper;
import by.andd3dfx.searchapp.search.model.SearchResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SearchEventListener implements ActionListener {

    private final Supplier<String> searchTextSupplier;
    private final Consumer<SearchResult> searchResultConsumer;
    private final int maxRecordsAmount;

    private final SearchHelper searchHelper;

    public SearchEventListener(Supplier<String> searchTextSupplier, Consumer<SearchResult> searchResultConsumer, int maxRecordsAmount) {
        this.searchTextSupplier = searchTextSupplier;
        this.searchResultConsumer = searchResultConsumer;
        this.maxRecordsAmount = maxRecordsAmount;

        this.searchHelper = new SearchHelper();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = searchTextSupplier.get();

        CompletableFuture.runAsync(() -> {
            SearchResult result = searchHelper.search(searchText, maxRecordsAmount);
            searchResultConsumer.accept(result);
        });
    }
}
