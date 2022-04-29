package desktopx.assignment_2.cantonform.presentationmodel.util.attribute;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerAttribute extends Attribute<IntegerProperty, Integer, IntegerAttribute>{

    public IntegerAttribute() {
        this(0);
    }

    public IntegerAttribute(int initialValue) {
        super(new SimpleIntegerProperty(initialValue), new SimpleIntegerProperty(initialValue));

        // sehr einfache und wenig leistungsfaehige Konvertierung zwischen dem User praesentierten String und dem vom Attribut verwalteten Value
        valueAsTextProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setValid(true);
                setValidationMessage("OK");            //todo: wie koennte man diesen String abhaengig von der eingestellten Sprache machen?
                setValue(Integer.valueOf(newValue));
            } catch (NumberFormatException e) {
                setValid(false);
                setValidationMessage("Not a Double");
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> setValueAsText(String.valueOf(newValue)));
    }

    @Override
    public String asString() {
        return asString("%d");
    }
}
