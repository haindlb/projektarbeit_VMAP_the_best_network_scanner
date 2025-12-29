package at.ac.hcw.vmap.ui;

import at.ac.hcw.vmap.scanner.IcmpScanner;
import at.ac.hcw.vmap.util.LoggingConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainViewController {

    @FXML private TextField fldTarget;
    @FXML private TextField fldPort;
    @FXML private ListView<String> lstLog;
    private final ObservableList<String> logItems = FXCollections.observableArrayList();
    private IcmpScanner icmpScanner;

    public void initialize(){

        lstLog.setItems(logItems);
        logItems.add("---Start Logging---");
        LoggingConfig.connectListView(logItems, lstLog);

    }



    @FXML
    private void startScanClicked() {
        // TODO scan starten
        //Test
        icmpScanner = new IcmpScanner();
        icmpScanner.scanIcmp();
    }

    @FXML
    private void exportCsvClicked() {
        // TODO export starten
    }
}
