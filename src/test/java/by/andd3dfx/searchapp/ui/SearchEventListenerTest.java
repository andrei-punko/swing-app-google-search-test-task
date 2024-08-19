package by.andd3dfx.searchapp.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchEventListenerTest {

    private SearchEventListener eventListener;

    @Mock
    private Runnable runnable;

    @Before
    public void setUp() {
        eventListener = new SearchEventListener(runnable);
    }

    @Test
    public void actionPerformed() {
        var actionEvent = new ActionEvent(new Object(), 1, "command");

        eventListener.actionPerformed(actionEvent);

        verify(runnable).run();
    }

    @Test
    public void actionPerformedWhenNoEventArrived() {
        verify(runnable, never()).run();
    }
}
