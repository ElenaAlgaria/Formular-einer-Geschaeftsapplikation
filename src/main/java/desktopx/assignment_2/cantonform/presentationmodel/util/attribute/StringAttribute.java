package desktopx.assignment_2.cantonform.presentationmodel.util.attribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Dieter Holz
 */
public class StringAttribute extends Attribute<StringProperty, String, StringAttribute> {

    public StringAttribute(){
        this("");
    }

    public StringAttribute(String initialValue) {
        super(new SimpleStringProperty(initialValue), new SimpleStringProperty(initialValue));
        setValueAsText(initialValue);

        //keine Konvertierung zwischen dem  Benutzer praesentierten String und dem vom Attribut verwalteten Value
        valueAsTextProperty().bindBidirectional(valueProperty());
    }

    @Override
    public String asString() {
        return getValue();
    }
}
