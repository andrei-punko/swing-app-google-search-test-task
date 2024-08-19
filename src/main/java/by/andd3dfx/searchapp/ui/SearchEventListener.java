package by.andd3dfx.searchapp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

public class SearchEventListener implements ActionListener {

    private final Runnable runnable;

    public SearchEventListener(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CompletableFuture.runAsync(runnable);
    }
}
