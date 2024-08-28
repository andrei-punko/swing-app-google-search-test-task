package by.andd3dfx.searchapp.ui;

import lombok.RequiredArgsConstructor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class SearchEventListener implements ActionListener {

    private final Runnable runnable;

    @Override
    public void actionPerformed(ActionEvent e) {
        CompletableFuture.runAsync(runnable);
    }
}
