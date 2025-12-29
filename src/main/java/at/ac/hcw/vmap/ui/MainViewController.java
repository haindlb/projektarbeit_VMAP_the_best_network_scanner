package at.ac.hcw.vmap.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainViewController {

    @FXML private TextField fldTarget;
    @FXML private TextField fldPort;
    @FXML private ListView<String> logList;

    @FXML
    private void startScanClicked() {
        // TODO scan starten
    }

    @FXML
    private void exportCsvClicked() {
        // TODO export starten
    }
}
