package desktopx.assignment_2.cantonform.view;

import java.util.HashMap;
import java.util.Map;

import desktopx.assignment_2.cantonform.view.util.rectangularimageview.RectangularImageView;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
    private RectangularImageView picture;

    private final Map<String, ImageView> coatOfArms = new HashMap<>();

    Header(Switzerland switzerland) {
        this.switzerland = switzerland;

        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("summary");

        coatOfArms.put("AG", createImageView("AG", "Aargau"));
        coatOfArms.put("AI", createImageView("AI", "Appenzell Innerrhoden"));
        coatOfArms.put("AR", createImageView("AR", "Appenzell Ausserrhoden"));
        coatOfArms.put("BE", createImageView("BE", "Bern"));
        coatOfArms.put("BL", createImageView("BL", "Basel-Landschaft"));
        coatOfArms.put("BS", createImageView("BS", "Basel-Stadt"));
        coatOfArms.put("FR", createImageView("FR", "Freiburg"));
        coatOfArms.put("GE", createImageView("GE", "Genf"));
        coatOfArms.put("GL", createImageView("GL", "Glarus"));
        coatOfArms.put("GR", createImageView("GR", "Graubünden"));
        coatOfArms.put("JU", createImageView("JU", "Jura"));
        coatOfArms.put("LU", createImageView("LU", "Luzern"));
        coatOfArms.put("NE", createImageView("NE", "Neuenburg"));
        coatOfArms.put("NW", createImageView("NW", "Nidwalden"));
        coatOfArms.put("OW", createImageView("OW", "Obwalden"));
        coatOfArms.put("SG", createImageView("SG", "St. Gallen"));
        coatOfArms.put("SH", createImageView("SH", "Schaffhausen"));
        coatOfArms.put("SO", createImageView("SO", "Solothurn"));
        coatOfArms.put("SZ", createImageView("SZ", "Schwyz"));
        coatOfArms.put("TG", createImageView("TG", "Thurgau"));
        coatOfArms.put("TI", createImageView("TI", "Tessin"));
        coatOfArms.put("UR", createImageView("UR", "Uri"));
        coatOfArms.put("VD", createImageView("VD", "Waadt"));
        coatOfArms.put("VS", createImageView("VS", "Wallis"));
        coatOfArms.put("ZG", createImageView("ZG", "Zug"));
        coatOfArms.put("ZH", createImageView("ZH", "Zürich"));
    }

    @Override
    public void initializeParts() {
        nameLabel = new Label();
        nameLabel.getStyleClass().add("heading");

        hauptortLabel = new Label();
        hauptortLabel.getStyleClass().add("subheading");

        einwohnerDichteLabel = new Label();
        einwohnerDichteLabel.getStyleClass().add("subheading");

        picture = new RectangularImageView();
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

        picture.imageURLProperty().bind(canton.getKuerzel().valueProperty());

        nameLabel.textProperty().bind(canton.getKanton().valueProperty()
                                            .concat(", ")
                                            .concat(canton.getKuerzel().valueProperty()));
        hauptortLabel.textProperty().bind(canton.getHauptort().valueProperty());

        einwohnerDichteLabel.textProperty().bind(canton.getEinwohnerdichte().valueProperty().asString("%.1f Einw./km\u00B2"));
    }

    private ImageView createImageView(String canton, String cantonName) {
        ImageView imageView = new ImageView(new Image(Header.class.getResourceAsStream("/wappen/" + canton + ".png")));

        Tooltip.install(imageView, new Tooltip(cantonName));

        return imageView;
    }
}
