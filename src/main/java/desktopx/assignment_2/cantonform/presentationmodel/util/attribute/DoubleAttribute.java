package desktopx.assignment_2.cantonform.presentationmodel.util.attribute;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Dieter Holz
 */
public class DoubleAttribute extends Attribute<DoubleProperty, Double, DoubleAttribute> {

    public DoubleAttribute() {
        this(0.0);
    }

    public DoubleAttribute(double initialValue) {
        super(new SimpleDoubleProperty(initialValue), new SimpleDoubleProperty(initialValue));

        // sehr einfache und wenig leistungsfaehige Konvertierung zwischen dem User praesentierten String und dem vom Attribut verwalteten Value
        valueAsTextProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setValid(true);
                setValidationMessage("OK");            //todo: wie koennte man diesen String abhaengig von der eingestellten Sprache machen?
                setValue(Double.valueOf(newValue));
            } catch (NumberFormatException e) {
                setValid(false);
                setValidationMessage("Not a Double");
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> setValueAsText(String.valueOf(newValue)));
    }

    @Override
    public String asString() {
        return asString("%f");
    }
}
