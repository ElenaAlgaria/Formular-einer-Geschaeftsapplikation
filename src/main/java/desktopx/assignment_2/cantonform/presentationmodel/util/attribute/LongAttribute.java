package desktopx.assignment_2.cantonform.presentationmodel.util.attribute;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 * @author Dieter Holz
 */
public class LongAttribute extends Attribute<LongProperty, Long, LongAttribute> {

    public LongAttribute() {
        this(0L);
    }

    public LongAttribute(long initialValue) {
        super(new SimpleLongProperty(initialValue), new SimpleLongProperty(initialValue));

        // sehr einfache und wenig leistungsfaehige Konvertierung zwischen dem User praesentierten String und dem vom Attribut verwalteten Value
        valueAsTextProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setValid(true);
                setValidationMessage("OK");
                setValue(Long.valueOf(newValue));
            } catch (NumberFormatException e) {
                setValid(false);
                setValidationMessage("Not a Long");
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> setValueAsText(String.format("%d", newValue.longValue())));
    }

    @Override
    public String asString() {
        return asString("%d");
    }
}
