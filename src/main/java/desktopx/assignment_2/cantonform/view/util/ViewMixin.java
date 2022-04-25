package desktopx.assignment_2.cantonform.view.util;

import java.util.List;

import javafx.scene.text.Font;

public interface ViewMixin {

    default void init() {
        initializeSelf();
        initializeParts();
        layoutParts();
        setupEventHandlers();
        setupValueChangedListeners();
        setupBindings();
    }

    default void initializeSelf() {
    }

    void initializeParts();

    void layoutParts();

    default void setupEventHandlers() {
    }

    default void setupValueChangedListeners() {
    }

    default void setupBindings() {
    }

    default void loadFonts(String... font){
        for(String f : font){
            Font.loadFont(getClass().getResourceAsStream(f), 0);
        }
    }

    default void addStylesheetFiles(String... stylesheetFile) {
        for (String file : stylesheetFile) {
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }

    List<String> getStylesheets();

}