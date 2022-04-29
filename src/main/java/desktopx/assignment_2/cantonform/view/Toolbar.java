package desktopx.assignment_2.cantonform.view;

import desktopx.assignment_2.cantonform.presentationmodel.CantonPM;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.view.util.ViewMixin;

import java.util.Locale;

class Toolbar extends ToolBar implements ViewMixin {
    private static final String SAVE_ICON = "\uf0c7";
    private static final String UNDO_ICON = "\uf0e2";
    private static final String   CH_ICON = "\ue001";
    private static final String   UK_ICON = "\ue000";

    private Button saveButton;
    private Button cancelButton;
    private Button languageDEButton;
    private Button languageUKButton;

    private final Switzerland switzerland;

    Toolbar(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("tool-bar");
    }

    @Override
    public void initializeParts() {
        saveButton = new Button(SAVE_ICON);
        saveButton.getStyleClass().add("fontawesome");

        cancelButton = new Button(UNDO_ICON);
        cancelButton.getStyleClass().add("fontawesome");

        languageDEButton = new Button(CH_ICON);
        languageDEButton.getStyleClass().add("flaticon");

        languageUKButton = new Button(UK_ICON);
        languageUKButton.getStyleClass().add("flaticon");
    }

    @Override
    public void layoutParts() {
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getItems().addAll(saveButton, cancelButton, spacer, languageDEButton, languageUKButton);
    }

    @Override
    public void setupEventHandlers() {
        saveButton  .setOnAction(event -> switzerland.save());
        cancelButton.setOnAction(event -> switzerland.revert());

        languageDEButton.setOnAction(event -> switzerland.setCurrentLocale(Locale.GERMANY));
         languageUKButton     .setOnAction(event -> switzerland.setCurrentLocale(Locale.UK));
    }

    @Override
    public void setupBindings() {
         languageUKButton     .disableProperty().bind(switzerland.currentLocaleProperty().isEqualTo(Locale.UK));
        languageDEButton.disableProperty().bind(switzerland.currentLocaleProperty().isEqualTo(Locale.GERMANY));

        CantonPM mountain = switzerland.getCurrentCanton();

        saveButton.disableProperty().bind((mountain.changedProperty().not())
            .or(mountain.validProperty().not()));
        cancelButton.disableProperty().bind(mountain.changedProperty().not()
            .and(mountain.validProperty()));
    }

}
