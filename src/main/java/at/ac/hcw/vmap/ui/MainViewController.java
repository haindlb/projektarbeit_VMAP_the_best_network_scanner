package at.ac.hcw.vmap.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainViewController {

    @FXML private TextField targetField;
    @FXML private TextField portField;
    @FXML private ListView<String> logList;

    @FXML
    private void onStartScanClicked() {
        // TODO scan starten
    }

    @FXML
    private void onExportCsvClicked() {
        // TODO export starten
    }
}
