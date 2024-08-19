package by.andd3dfx.searchapp;

import by.andd3dfx.searchapp.search.SearchHelper;
import by.andd3dfx.searchapp.ui.MainFrame;

public class MainApp {

    public static void main(String[] args) {
        new MainFrame(new SearchHelper());
    }
}
