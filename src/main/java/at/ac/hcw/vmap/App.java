package at.ac.hcw.vmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {


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
