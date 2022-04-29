package desktopx.assignment_2.cantonform.view;

import desktopx.assignment_2.cantonform.presentationmodel.CantonPM;
import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.view.util.SimpleAttributeControl;
import desktopx.assignment_2.cantonform.view.util.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

//todo: warum ist diese Klasse nicht public?

class CantonForm extends GridPane implements ViewMixin {

    private final Switzerland switzerland;

    private Label kantonLabel;
    private Label kuerzelLabel;
    private Label kantonsNumLabel;
    private Label standesstimmeLabel;
    private Label beitrittLabel;
    private Label hauptortLabel;
    private Label einwohnerLabel;
    private Label auslaenderLabel;
    private Label flaecheLabel;
    private Label einwohnerdichteLabel;
    private Label gemeindenLabel;
    private Label amtspracheLabel;

    private SimpleAttributeControl kantonField;
    private SimpleAttributeControl kuerzelField;
    private SimpleAttributeControl kantonsNumField;
    private SimpleAttributeControl standesstimmeField;
    private SimpleAttributeControl beitrittField;
    private SimpleAttributeControl hauptortField;
    private SimpleAttributeControl einwohnerField;
    private SimpleAttributeControl auslaenderField;
    private SimpleAttributeControl flaecheField;
    private SimpleAttributeControl einwohnerdichtField;
    private SimpleAttributeControl gemeindenField;
    private SimpleAttributeControl amtspracheField;

    CantonForm(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("form");
    }

    @Override
    public void initializeParts() {
        kantonLabel = new Label();
        kuerzelLabel = new Label();
        kantonsNumLabel = new Label();
        standesstimmeLabel = new Label();
        beitrittLabel = new Label();
        hauptortLabel = new Label();
        einwohnerLabel = new Label();
        auslaenderLabel = new Label();
        flaecheLabel = new Label();
        einwohnerdichteLabel = new Label();
        gemeindenLabel = new Label();
        amtspracheLabel = new Label();

        CantonPM canton = switzerland.getCurrentCanton();

        kantonField = new SimpleAttributeControl(canton.getKanton());
        kuerzelField = new SimpleAttributeControl(canton.getKuerzel());
        kantonsNumField = new SimpleAttributeControl(canton.getKantonsnummer());
        standesstimmeField = new SimpleAttributeControl(canton.getStandesstimme());
        beitrittField = new SimpleAttributeControl(canton.getBeitritt());
        hauptortField = new SimpleAttributeControl(canton.getHauptort());
        einwohnerField = new SimpleAttributeControl(canton.getEinwohner());
        auslaenderField = new SimpleAttributeControl(canton.getAuslaender());
        flaecheField = new SimpleAttributeControl(canton.getFlaeche());
        einwohnerdichtField = new SimpleAttributeControl(canton.getEinwohnerdichte());
        gemeindenField = new SimpleAttributeControl(canton.getGemeinden());
        amtspracheField = new SimpleAttributeControl(canton.getAmtssprache());
    }

    @Override
    public void layoutParts() {
        ColumnConstraints firstLabelCol = new ColumnConstraints();
        firstLabelCol.setMaxWidth(ConstraintsBase.CONSTRAIN_TO_PREF);
        firstLabelCol.setMinWidth(30);
        firstLabelCol.setPrefWidth(110);

        ColumnConstraints firstFieldCol = new ColumnConstraints();
        firstFieldCol.setMinWidth(100);
        firstFieldCol.setFillWidth(true);
        firstFieldCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints secondLabelCol = new ColumnConstraints();
        secondLabelCol.setMaxWidth(ConstraintsBase.CONSTRAIN_TO_PREF);
        secondLabelCol.setMinWidth(30);
        secondLabelCol.setPrefWidth(110);

        ColumnConstraints secondFieldCol = new ColumnConstraints();
        secondFieldCol.setMinWidth(100);
        secondFieldCol.setFillWidth(true);
        secondFieldCol.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(firstLabelCol, firstFieldCol, new ColumnConstraints(), secondLabelCol, secondFieldCol);

        Region spacer = new Region();
        spacer.getStyleClass().add("spacer");

        addRow(0, kantonLabel, kantonField, spacer      , kuerzelLabel, kuerzelField);
        addRow(1, einwohnerLabel, einwohnerField, new Region(), flaecheLabel, flaecheField);
        addRow(2, auslaenderLabel, auslaenderField, new Region(), einwohnerdichteLabel, einwohnerdichtField);
        addRow(3, kantonsNumLabel, kantonsNumField, new Region(), standesstimmeLabel, standesstimmeField);
        addRow(4, beitrittLabel, beitrittField, new Region(), hauptortLabel, hauptortField);

        add(amtspracheLabel, 0, 5);
        add(amtspracheField, 1, 5, 4, 1);
        add(gemeindenLabel,  0, 6);
        add(gemeindenField,  1, 6, 4, 1);
    }

    @Override
    public void setupBindings() {
        CantonPM canton = switzerland.getCurrentCanton();

        // es werden nur die Beschriftungen an die entsprechenden Labels gebunden
        // todo: warum muessen hier keine Bindings z.B zum Value der Attribute definiert werden?
        kantonLabel.textProperty()           .bind(canton.getKanton().captionProperty());
        kuerzelLabel.textProperty()         .bind(canton.getKuerzel().captionProperty());
        kantonsNumLabel.textProperty()           .bind(canton.getKantonsnummer().captionProperty());
        standesstimmeLabel.textProperty()         .bind(canton.getStandesstimme().captionProperty());
        beitrittLabel.textProperty()        .bind(canton.getBeitritt().captionProperty());
        hauptortLabel.textProperty()          .bind(canton.getHauptort().captionProperty());
        einwohnerLabel.textProperty()      .bind(canton.getEinwohner().captionProperty());
        auslaenderLabel.textProperty() .bind(canton.getAuslaender().captionProperty());
        flaecheLabel.textProperty()     .bind(canton.getFlaeche().captionProperty());
        einwohnerdichteLabel.textProperty().bind(canton.getEinwohnerdichte().captionProperty());
        gemeindenLabel.textProperty()        .bind(canton.getGemeinden().captionProperty());
        amtspracheLabel.textProperty()       .bind(canton.getAmtssprache().captionProperty());
    }
}
