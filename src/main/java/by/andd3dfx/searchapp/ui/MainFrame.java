package by.andd3dfx.searchapp.ui;

import by.andd3dfx.searchapp.search.model.SearchResult;
import by.andd3dfx.searchapp.search.model.SearchResultItem;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

    private static final String WINDOW_TITLE = "Google search application (test task)";
    private static final String SEARCH_BUTTON_LABEL = "Search";
    private static final int SEARCH_FIELD_COLUMNS_COUNT = 50;
    private static final int RESULTS_PER_PAGE = 10;

    public MainFrame() {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable searchResultTable = new JTable(new DefaultTableModel());
        JTextField searchStringTextField = new JTextField(SEARCH_FIELD_COLUMNS_COUNT);
        JButton searchButton = new JButton(SEARCH_BUTTON_LABEL);

        SearchEventListener searchEventListener = new SearchEventListener(
                () -> searchStringTextField.getText(),
                (SearchResult searchResult) -> updateTable(searchResult, searchResultTable),
                RESULTS_PER_PAGE
        );
        searchStringTextField.addActionListener(searchEventListener);
        searchButton.addActionListener(searchEventListener);

        JPanel upperPanel = new JPanel();
        upperPanel.add(searchStringTextField);
        upperPanel.add(searchButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(upperPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(searchResultTable), BorderLayout.CENTER);

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
        setVisible(true);
        setResizable(false);
    }

    private Void updateTable(SearchResult searchResult, JTable searchResultTable) {
        DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
        tableModel.setRowCount(0);

        Vector<Vector> rows = new Vector<>();
        for (SearchResultItem searchResultItem : searchResult.getSearchResultItems()) {
            Vector<String> row = new Vector<>();
            row.add(searchResultItem.getUrl());
            rows.add(row);
        }

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Search results");
        tableModel.setDataVector(new Vector<>(rows), columnNames);
        return null;
    }
}
