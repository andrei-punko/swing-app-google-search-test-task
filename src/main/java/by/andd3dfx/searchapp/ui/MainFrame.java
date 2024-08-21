package by.andd3dfx.searchapp.ui;

import by.andd3dfx.searchapp.search.SearchHelper;
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
import java.util.List;

public class MainFrame extends JFrame {

    private static final String WINDOW_TITLE = "Google search application (test task)";
    private static final String SEARCH_BUTTON_LABEL = "Search";
    private static final int SEARCH_FIELD_COLUMNS_COUNT = 50;
    private static final int RESULTS_PER_PAGE = 10;

    public MainFrame(SearchHelper searchHelper) {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable searchResultTable = new JTable(new DefaultTableModel());
        JPanel upperPanel = buildJPanel(searchHelper, searchResultTable);

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

    private JPanel buildJPanel(SearchHelper searchHelper, JTable searchResultTable) {
        JTextField searchStringTextField = new JTextField(SEARCH_FIELD_COLUMNS_COUNT);
        JButton searchButton = new JButton(SEARCH_BUTTON_LABEL);

        var eventListener = buildEventListener(searchHelper, searchResultTable, searchStringTextField);
        searchStringTextField.addActionListener(eventListener);
        searchButton.addActionListener(eventListener);

        JPanel upperPanel = new JPanel();
        upperPanel.add(searchStringTextField);
        upperPanel.add(searchButton);
        return upperPanel;
    }

    private SearchEventListener buildEventListener(
            SearchHelper searchHelper, JTable searchResultTable, JTextField searchStringTextField) {
        return new SearchEventListener(() -> {
            var searchString = searchStringTextField.getText();
            var searchResult = searchHelper.search(searchString, RESULTS_PER_PAGE);
            updateTable(searchResult, searchResultTable);
        });
    }

    private void updateTable(List<SearchResultItem> searchResult, JTable searchResultTable) {
        DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
        tableModel.setRowCount(0);

        Vector<Vector> rows = new Vector<>();
        for (var searchResultItem : searchResult) {
            Vector<String> row = new Vector<>();
            row.add(searchResultItem.getUrl());
            rows.add(row);
        }

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Search results");
        tableModel.setDataVector(new Vector<>(rows), columnNames);
    }
}
