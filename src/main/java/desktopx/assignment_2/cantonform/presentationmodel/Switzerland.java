package desktopx.assignment_2.cantonform.presentationmodel;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import desktopx.assignment_2.cantonform.service.CantonService;

public class Switzerland {
    private final StringProperty applicationTitle = new SimpleStringProperty("Canton Form");
    private final CantonService  service;

    private CantonPM currentCanton;


    public Switzerland(CantonService service) {
        this.service = service;

        long id = new Random().nextInt(26) + 1;
        currentCanton = CantonPM.of(service.get(id));
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

}
