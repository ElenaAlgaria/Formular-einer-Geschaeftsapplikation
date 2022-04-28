package desktopx.assignment_2.cantonform.presentationmodel.util.attribute;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Basisklasse fuer die verschiedenen konkreten Attributes.
 *
 * Einfache Implementierung des Attribute-Konzepts.
 *
 * BITTE BEACHTEN SIE: Diese Implementierung ist fuer Ausbildungszwecke gedacht. Sie kann als Basis fuer
 * fuer eine Implementierung in Produktionsqualitaet dienen.
 *
 * Insbesondere fehlt die Umsetzung eines leistungsfaehigen Validierungskonzepts.
 *
 * @author Dieter Holz
 */
public abstract class Attribute<P extends Property, E, A extends Attribute> {
    private static final Locale CH = new Locale("de", "CH");
    private static final String ELLIPSIS = "...";

    private final Map<Locale, String> captions = new HashMap<>();

    private final P value;
    private final P persistentValue;

    private final StringProperty  valueAsText       = new SimpleStringProperty();
    private final StringProperty  caption           = new SimpleStringProperty();
    private final BooleanProperty mandatory         = new SimpleBooleanProperty(false);
    private final BooleanProperty readOnly          = new SimpleBooleanProperty(false);
    private final BooleanProperty valid             = new SimpleBooleanProperty(true);
    private final StringProperty  validationMessage = new SimpleStringProperty();
    private final BooleanProperty changed           = new SimpleBooleanProperty(false);

    protected Attribute(P valueProperty, P persistentValueProperty) {
        value = valueProperty;
        persistentValue = persistentValueProperty;

        //changed ist abhaengig davon ob value und persistentValue gleich sind. changed kann nicht direkt gesetzt werden
        changedProperty().bind(Bindings.createBooleanBinding(() -> !persistentValueProperty().getValue().equals(valueProperty().getValue()),
                                                             valueProperty(), persistentValueProperty()));

    }

    //Methoden, die nur zur Konfiguration eines Attributs aufgerufen werden

    public A caption(Locale lang, String label) {
        captions.put(lang, label);

        return (A) this;
    }

    public A mandatory(boolean mandatory) {
        setMandatory(mandatory);

        return (A) this;
    }

    public A readOnly(boolean readOnly) {
        setReadOnly(readOnly);

        return (A) this;
    }

    //**********************************************************************************************************
    // Methoden, die typischerweise als Reaktion auf eine Benutzer-Aktion aufgerufen werden

    public void setLanguage(Locale lang) {
        setCaption(captions.getOrDefault(lang, ELLIPSIS));
    }

    /**
     * Wird typischerweise nach einem erfolgreichen "Save" verwendet.
     *
     * Der persistierte Wert wird auf den aktuellen Wert gesetzt. Damit wird das Attribut wieder "not changed".
     */
    public void rebase() {
       persistentValue.setValue(value.getValue());
    }

    /**
     * Nimmt die Wertaenderung seit dem letzten Save zurueck
     */
    public void revert(){
        value.setValue(persistentValue.getValue());
        setValueAsText(String.valueOf(getValue()));
    }

    public abstract String asString();

    protected String asString(String formatPattern) {
        return String.format(CH, formatPattern, getValue());
    }

    //todo: um weitere nuetzliche Methoden ergaenzen, z.B. "revert", die eine Aenderungen zuruecknimmt

    //**********************************************************************************************************
    // die getter und setter

    public String getCaption() {
        return caption.get();
    }

    public StringProperty captionProperty() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    public E getValue() {
        return (E) value.getValue();
    }

    public P valueProperty() {
        return value;
    }

    public void setValue(E value) {
        this.value.setValue(value);
    }

    public E getPersistentValue() {
        return (E) persistentValue.getValue();
    }

    public P persistentValueProperty() {
        return persistentValue;
    }

    public boolean isMandatory() {
        return mandatory.get();
    }

    public BooleanProperty mandatoryProperty() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory.set(mandatory);
    }

    public boolean isValid() {
        return valid.get();
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }

    public String getValidationMessage() {
        return validationMessage.get();
    }

    public StringProperty validationMessageProperty() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage.set(validationMessage);
    }

    public boolean isChanged() {
        return changed.get();
    }

    public BooleanProperty changedProperty() {
        return changed;
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public BooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public String getValueAsText() {
        return valueAsText.get();
    }

    public StringProperty valueAsTextProperty() {
        return valueAsText;
    }

    public void setValueAsText(String valueAsText) {
        this.valueAsText.set(valueAsText);
    }
}
