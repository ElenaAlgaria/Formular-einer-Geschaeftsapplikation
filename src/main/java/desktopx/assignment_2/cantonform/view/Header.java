package desktopx.assignment_2.cantonform.view;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import desktopx.assignment_2.cantonform.presentationmodel.CantonPM;
import desktopx.assignment_2.cantonform.presentationmodel.Switzerland;
import desktopx.assignment_2.cantonform.view.util.ViewMixin;


class Header extends GridPane implements ViewMixin {

    private final Switzerland switzerland;

    private Label     nameLabel;
    private Label     hauptortLabel;
    private Label     einwohnerDichteLabel;
    private ImageView picture;

    private final Map<String, Image> coatOfArms = new HashMap<>();

    Header(Switzerland switzerland) {
        this.switzerland = switzerland;

        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("summary");

        coatOfArms.put("AG", createImage("AG", "Aargau"));
        coatOfArms.put("AI", createImage("AI", "Appenzell Innerrhoden"));
        coatOfArms.put("AR", createImage("AR", "Appenzell Ausserrhoden"));
        coatOfArms.put("BE", createImage("BE", "Bern"));
        coatOfArms.put("BL", createImage("BL", "Basel-Landschaft"));
        coatOfArms.put("BS", createImage("BS", "Basel-Stadt"));
        coatOfArms.put("FR", createImage("FR", "Freiburg"));
        coatOfArms.put("GE", createImage("GE", "Genf"));
        coatOfArms.put("GL", createImage("GL", "Glarus"));
        coatOfArms.put("GR", createImage("GR", "Graubünden"));
        coatOfArms.put("JU", createImage("JU", "Jura"));
        coatOfArms.put("LU", createImage("LU", "Luzern"));
        coatOfArms.put("NE", createImage("NE", "Neuenburg"));
        coatOfArms.put("NW", createImage("NW", "Nidwalden"));
        coatOfArms.put("OW", createImage("OW", "Obwalden"));
        coatOfArms.put("SG", createImage("SG", "St. Gallen"));
        coatOfArms.put("SH", createImage("SH", "Schaffhausen"));
        coatOfArms.put("SO", createImage("SO", "Solothurn"));
        coatOfArms.put("SZ", createImage("SZ", "Schwyz"));
        coatOfArms.put("TG", createImage("TG", "Thurgau"));
        coatOfArms.put("TI", createImage("TI", "Tessin"));
        coatOfArms.put("UR", createImage("UR", "Uri"));
        coatOfArms.put("VD", createImage("VD", "Waadt"));
        coatOfArms.put("VS", createImage("VS", "Wallis"));
        coatOfArms.put("ZG", createImage("ZG", "Zug"));
        coatOfArms.put("ZH", createImage("ZH", "Zürich"));
    }

    @Override
    public void initializeParts() {
        nameLabel = new Label();
        nameLabel.getStyleClass().add("heading");

        hauptortLabel = new Label();
        hauptortLabel.getStyleClass().add("subheading");

        einwohnerDichteLabel = new Label();
        einwohnerDichteLabel.getStyleClass().add("subheading");

        picture = new ImageView();
    }

    @Override
    public void layoutParts() {
        VBox spacerCol = new VBox();
        setHgrow(spacerCol, Priority.ALWAYS);

        setHalignment(picture, HPos.CENTER);
        setValignment(einwohnerDichteLabel, VPos.BOTTOM);

        add(nameLabel   , 0, 0);
        add(spacerCol   , 1, 0, 1, 3);
        add(picture     , 2, 0, 1, 3);
        add(hauptortLabel, 0, 1);
        add(einwohnerDichteLabel, 0, 2);
    }

    @Override
    public void setupBindings() {
        CantonPM canton = switzerland.getCurrentCanton();

        picture.imageProperty().bind(Bindings.createObjectBinding(() -> coatOfArms.get(canton.getKuerzel()), canton.kuerzelProperty()));

        nameLabel.textProperty().bind(canton.kantonProperty()
                                            .concat(", ")
                                            .concat(canton.kuerzelProperty()));
        hauptortLabel.textProperty().bind(canton.hauptortProperty());

        einwohnerDichteLabel.textProperty().bind(canton.einwohnerdichteProperty().asString("%.1f Einw./km\u00B2"));
    }

    private Image createImage(String canton, String cantonName) {
        return new Image(Header.class.getResourceAsStream("/wappen/" + canton + ".png"));
    }
}
