package desktopx.assignment_2.cantonform.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.view.util.ViewMixin;

public class RootPane extends BorderPane implements ViewMixin {
    private final Switzerland switzerland;

    private Node toolbar;
    private Node summary;
    private Node form;

    public RootPane(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        loadFonts("/fonts/Lato/Lato-Lig.ttf", "/fonts/fontawesome-webfont.ttf", "/fonts/flaticon.ttf");
        addStylesheetFiles("style.css");

        getStyleClass().add("root-pane");
    }

    @Override
    public void initializeParts() {
        toolbar = new Toolbar(switzerland);
        summary = new Header(switzerland);
        form = new CantonForm(switzerland);
    }

    @Override
    public void layoutParts() {
        setTop(toolbar);

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        setCenter(new VBox(summary, scrollPane));
    }

}
