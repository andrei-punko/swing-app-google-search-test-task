package by.andd3dfx.searchapp.search.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(exclude = {"title"})
@Getter
@RequiredArgsConstructor
public class SearchResultItem {

    private final String url;
    private final String title;
}
