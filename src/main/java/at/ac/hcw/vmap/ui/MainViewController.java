package at.ac.hcw.vmap.ui;

import at.ac.hcw.vmap.export.CsvExportService;
import at.ac.hcw.vmap.scanner.IcmpScanner;
import at.ac.hcw.vmap.util.LogHandlerCsvBuffer;
import at.ac.hcw.vmap.util.Loggable;
import at.ac.hcw.vmap.util.LoggingConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MainViewController implements Loggable {

    @FXML private TextField fldTarget;
    @FXML private TextField fldPort;
    @FXML private ListView<String> lstLog;
    private final ObservableList<String> logItems = FXCollections.observableArrayList();
    public static final Logger LOG = Logger.getLogger(MainViewController.class.getName());
    private IcmpScanner icmpScanner;
    private LogHandlerCsvBuffer csvBuffer;
    private CsvExportService exportService;

    public void initialize(){

        lstLog.setItems(logItems);
        logItems.add("---Start Logging---");
        logItems.add("Bitte Ordner C:\\Temp\\Export anlegen");
        LoggingConfig.connectListView(logItems, lstLog); //Darf nur einmal aufgerufen werden sonst doppelte Registrierung von Handler

    }

    @FXML
    private void startScanClicked() {
        // TODO scan starten
        //Test
        icmpScanner = new IcmpScanner();
        icmpScanner.scanIcmp();
    }

    @FXML
    private void exportCsvClicked() throws IOException {
        // TODO export starten

        LogHandlerCsvBuffer csvBuffer = findMyHandler();

        if(csvBuffer == null){
            logWarn("CSV Buffer Handler nicht gefunden");
            return;
        }

        List<LogRecord> records = csvBuffer.exportList();
        exportService = new CsvExportService(records);
        exportService.csvExport();


        boolean csvExportSuccessful = exportService.isExportWasSuccessful();
        if(csvExportSuccessful){
            logInfo("---Export was Successful---");
        }
    }
    private LogHandlerCsvBuffer findMyHandler(){

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for(int i = 0; i < handlers.length; i++){
            if(handlers[i] instanceof LogHandlerCsvBuffer){
                return (LogHandlerCsvBuffer) handlers[i];
            }
        }
        return null;
    }
}
