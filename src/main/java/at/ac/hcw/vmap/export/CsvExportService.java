package at.ac.hcw.vmap.export;

import at.ac.hcw.vmap.util.Loggable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CsvExportService implements Loggable {

    public static final Logger LOG = Logger.getLogger(CsvExportService.class.getName());
    private final List<LogRecord> csvBuffer;
    private final String EXPORTPATH = "C:\\Temp\\Export";
    private final String FILENAME = "vmap_export.csv";
    private final String SEPARATOR = ",";
    private final String NEWLINE = System.lineSeparator();
    private boolean exportWasSuccessful;
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());

    public CsvExportService(List<LogRecord> csvBuffer){
        this.csvBuffer = csvBuffer;
        this.exportWasSuccessful = false;
    }

    public void csvExport() throws IOException {

        File exportFile = new File(EXPORTPATH,FILENAME);

        try(FileWriter writer = new FileWriter(exportFile, false)){

            writer.write("timestamp,level,message,exceptionType,exceptionMessage");

            for(int i = 0; i < csvBuffer.size(); i++){

                LogRecord row  = csvBuffer.get(i);

                String time = TS.format(Instant.ofEpochMilli(row.getMillis())); //Formatierung Zeitstempel in Config oben

                String level = "";
                if(row.getLevel() != null){
                    level = row.getLevel().getName();
                }

                String message = "";
                if(row.getLevel() != null){
                    message = row.getMessage();
                }

                String exType = "";
                String exMsg = "";

                if(row.getThrown() != null) {
                    exType = row.getThrown().getClass().getSimpleName();

                    if (row.getThrown() != null) {
                        exMsg = row.getThrown().getMessage();
                    }
                }

                writer.write(csv(time) + SEPARATOR + csv(level) + SEPARATOR + csv(message) + SEPARATOR + csv(exType) + SEPARATOR + csv(exMsg) + NEWLINE);
            }
            writer.flush();
            writer.close();

            exportWasSuccessful = true;
        }catch(Exception e){
            logError("Fehler bei Export von CSV", e);
        }
    }

    private String csv(String string){

        if(string == null){
            string = "";
        }

        string = string.replace("\"", "\"\"");
        return "\"" + string + "\"";
    }

    public boolean isExportWasSuccessful() {
        return exportWasSuccessful;
    }

    public String getEXPORTPATH() {
        return EXPORTPATH;
    }
}
