package desktopx.assignment_2.cantonform;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.service.CantonService;
import desktopx.assignment_2.cantonform.service.serviceimpl.CantonServiceMemoryBased;
import desktopx.assignment_2.cantonform.view.RootPane;

public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage){
        CantonService service     = new CantonServiceMemoryBased();
        Switzerland   switzerland = new Switzerland(service);
        Parent        rootPanel   = new RootPane(switzerland);

        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(switzerland.applicationTitleProperty());

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
