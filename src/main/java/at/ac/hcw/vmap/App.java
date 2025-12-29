package at.ac.hcw.vmap;

import at.ac.hcw.vmap.util.LoggingConfig;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ListView<String> liveLog = new ListView<>();
        ObservableList<String> liveLogItems = FXCollections.observableArrayList();
        liveLog.setItems(liveLogItems);

        LoggingConfig.connectListView(liveLogItems,liveLog);

        FXMLLoader loader = new FXMLLoader(
                App.class.getResource("/at/ac/hcw/vmap/ui/main_view.fxml")
        );

        Scene scene = new Scene(loader.load(), 900, 700);
        stage.setTitle("VMAP");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
