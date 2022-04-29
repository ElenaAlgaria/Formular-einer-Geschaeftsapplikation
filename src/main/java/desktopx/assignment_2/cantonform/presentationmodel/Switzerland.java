package desktopx.assignment_2.cantonform.presentationmodel;

import java.util.Locale;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import desktopx.assignment_2.cantonform.service.CantonService;

public class Switzerland {
    private final StringProperty applicationTitle = new SimpleStringProperty();
    private final ObjectProperty<Locale> currentLocale    = new SimpleObjectProperty<>(Locale.GERMANY);
    private final CantonService  service;
    private final BooleanProperty changed          = new SimpleBooleanProperty();


    private CantonPM currentCanton;


    public Switzerland(CantonService service) {
        this.service = service;

        long id = new Random().nextInt(26) + 1;
        currentCanton = CantonPM.of(service.get(id));

        setupValueChangedListeners();
        setupBindings();

        translateEverything();
    }

    public void save() {
        service.save(currentCanton.toDTO());
        currentCanton.rebase();
    }

    public void revert() {
        currentCanton.revert();
    }

    private void setupValueChangedListeners() {
        currentLocale.addListener((observable, oldValue, newValue) -> translateEverything());
    }

    private void setupBindings(){
        changed.bind(currentCanton.changedProperty());
    }

    private void translateEverything(){
        setApplicationTitle(getCurrentLocale().equals(Locale.GERMANY) ? "Canton Formular" : "Canton Editor");
        currentCanton.setLanguage(getCurrentLocale());
    }


    public CantonPM getCurrentCanton() {
        return currentCanton;
    }

    // alle Getter und Setter
    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public Locale getCurrentLocale() {
        return currentLocale.get();
    }

    public ObjectProperty<Locale> currentLocaleProperty() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale.set(currentLocale);
    }

    public void setChanged(boolean changed) {
        this.changed.set(changed);
    }

    public void setCurrentCanton(CantonPM currentCanton) {
        this.currentCanton = currentCanton;
    }
}
