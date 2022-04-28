package desktopx.assignment_2.cantonform.view;

import desktopx.assignment_2.cantonform.presentationmodel.CantonPM;
import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.view.util.SimpleAttributeControl;
import desktopx.assignment_2.cantonform.view.util.ViewMixin;
import desktopx.module04.mountainform_attributebased_solution.presentationmodel.MountainPM;
import desktopx.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import desktopx.module04.mountainform_attributebased_solution.view.util.SimpleAttributeControl;
import desktopx.module04.mountainform_attributebased_solution.view.util.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

//todo: warum ist diese Klasse nicht public?

class CantonForm extends GridPane implements ViewMixin {

    private final Switzerland switzerland;

    private Label nameLabel;
    private Label heightLabel;
    private Label typeLabel;
    private Label regionLabel;
    private Label cantonsLabel;
    private Label rangeLabel;
    private Label isolationLabel;
    private Label isolationPointLabel;
    private Label prominenceLabel;
    private Label prominencePointLabel;
    private Label captionLabel;
    private Label imageURLLabel;

    private SimpleAttributeControl nameField;
    private SimpleAttributeControl heightField;
    private SimpleAttributeControl typeField;
    private SimpleAttributeControl regionField;
    private SimpleAttributeControl cantonsField;
    private SimpleAttributeControl rangeField;
    private SimpleAttributeControl isolationField;
    private SimpleAttributeControl isolationPointField;
    private SimpleAttributeControl prominenceField;
    private SimpleAttributeControl prominencePointField;
    private SimpleAttributeControl captionField;
    private SimpleAttributeControl imageURLField;

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
        nameLabel            = new Label();
        heightLabel          = new Label();
        typeLabel            = new Label();
        regionLabel          = new Label();
        cantonsLabel         = new Label();
        rangeLabel           = new Label();
        isolationLabel       = new Label();
        isolationPointLabel  = new Label();
        prominenceLabel      = new Label();
        prominencePointLabel = new Label();
        captionLabel         = new Label();
        imageURLLabel        = new Label();

        CantonPM canton = switzerland.getCurrentCanton();

        nameField            = new SimpleAttributeControl(canton.nameAttribute());
        heightField          = new SimpleAttributeControl(canton.heightAttribute());
        typeField            = new SimpleAttributeControl(canton.typeAttribute());
        regionField          = new SimpleAttributeControl(canton.regionAttribute());
        cantonsField         = new SimpleAttributeControl(canton.cantonsAttribute());
        rangeField           = new SimpleAttributeControl(canton.rangeAttribute());
        isolationField       = new SimpleAttributeControl(canton.isolationAttribute());
        isolationPointField  = new SimpleAttributeControl(canton.isolationPointAttribute());
        prominenceField      = new SimpleAttributeControl(canton.prominenceAttribute());
        prominencePointField = new SimpleAttributeControl(canton.prominencePointAttribute());
        captionField         = new SimpleAttributeControl(canton.imageCaptionAttribute());
        imageURLField        = new SimpleAttributeControl(canton.imageURLAttribute());
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

        addRow(0, nameLabel          , nameField          , spacer      , heightLabel         , heightField);
        addRow(1, isolationLabel     , isolationField     , new Region(), prominenceLabel     , prominenceField);
        addRow(2, isolationPointLabel, isolationPointField, new Region(), prominencePointLabel, prominencePointField);
        addRow(3, typeLabel          , typeField          , new Region(), regionLabel         , regionField);
        addRow(4, cantonsLabel       , cantonsField       , new Region(), rangeLabel          , rangeField);

        add(imageURLLabel, 0, 5);
        add(imageURLField, 1, 5, 4, 1);
        add(captionLabel,  0, 6);
        add(captionField,  1, 6, 4, 1);
    }

    @Override
    public void setupBindings() {
        CantonPM canton = switzerland.getCurrentCanton();

        // es werden nur die Beschriftungen an die entsprechenden Labels gebunden
        // todo: warum muessen hier keine Bindings z.B zum Value der Attribute definiert werden?
        nameLabel.textProperty()           .bind(canton.nameAttribute().captionProperty());
        heightLabel.textProperty()         .bind(canton.heightAttribute().captionProperty());
        typeLabel.textProperty()           .bind(canton.typeAttribute().captionProperty());
        regionLabel.textProperty()         .bind(canton.regionAttribute().captionProperty());
        cantonsLabel.textProperty()        .bind(canton.cantonsAttribute().captionProperty());
        rangeLabel.textProperty()          .bind(canton.rangeAttribute().captionProperty());
        isolationLabel.textProperty()      .bind(canton.isolationAttribute().captionProperty());
        isolationPointLabel.textProperty() .bind(canton.isolationPointAttribute().captionProperty());
        prominenceLabel.textProperty()     .bind(canton.prominenceAttribute().captionProperty());
        prominencePointLabel.textProperty().bind(canton.prominencePointAttribute().captionProperty());
        captionLabel.textProperty()        .bind(canton.imageCaptionAttribute().captionProperty());
        imageURLLabel.textProperty()       .bind(canton.imageURLAttribute().captionProperty());
    }
}
