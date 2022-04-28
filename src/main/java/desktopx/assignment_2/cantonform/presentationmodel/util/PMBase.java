package desktopx.assignment_2.cantonform.presentationmodel.util;

import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.Attribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.DoubleAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.IntegerAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.LongAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.StringAttribute;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * Basisklasse fuer alle konkreten PresentationModel-Klassen.
 *
 * Stellt die gemeinsame Grundfunktionalitaet zur Verfuegung, insbesonder ob das PresentationModel insgesamt valid ist
 * und ob es in einem seiner Attribute eine Aenderung gegenueber dem persistierten Zustand gibt.
 *
 * @author Dieter Holz
 */
public abstract class PMBase<DTO> {
    private static final String ELLIPSIS = "...";

    private final List<Attribute<?, ?, ?>> allAttributes = new ArrayList<>();

    private final BooleanProperty changed = new SimpleBooleanProperty(false);
    private final BooleanProperty valid   = new SimpleBooleanProperty(true);


    private final ChangeListener<Boolean> validStateListener = (observable, oldValue, newValue) -> checkValidState();
    private final ChangeListener<Boolean> changedStateListener = (observable, oldValue, newValue) -> checkDirtyState();

    /**
     * WICHTIG: diese Methode muss in den Subklassen, typischerweise im Konstruktor aufgerufen werden.
     */
    protected void init(){
        setupAttributeChangedListeners();

        // zur Verwaltung des changed- und den valid-States des gesamten PMs
        forAllAttributes(attribute -> attribute.changedProperty().addListener(changedStateListener));
        forAllAttributes(attribute -> attribute.validProperty().addListener(validStateListener));
    }

    public void setLanguage(Locale locale) {
        forAllAttributes(attribute -> attribute.setLanguage(locale));
    }



    public void rebase() {
        forAllAttributes(Attribute::rebase);
    }

    /**
     * setzt alle Attribute auf den persistierten Wert zurueck
     */
    public void revert() {
        forAllAttributes(Attribute::revert);
    }

    //todo: Um weitere nuetzliche/notwendige Methoden ergaenzen.
    // Was fehlt als Grundfunktionalitaet fuer eine typische Applikation mit einem Formular?



    /**
     * Methode zur Implementierung der logischen Abhaengigkeiten zwischen den Attributen
     */
    protected void setupAttributeChangedListeners() {
    }

    /**
     * Uebertraegt die Werte des dtos auf das PresentationModel
     *
     * @param dto die Daten, die typischerweise von einem Service geliefert wurden.
     *
     * Todo: Wie kann das DTO-Konzept verallgemeinert werden, so dass die apply-Methode generisch implementiert werden kann?
     *            Ist es wirklich notwendig unterschiedliche DTO-Klassen zu verwenden?
     */
    public abstract void apply(DTO dto);

    public abstract DTO toDTO();


    //************************************************************************************************
    // Methoden zur "Konfiguration" des PresentationModels
    // Attribute sollten ausschliesslich auf diesem Weg zu einem PM hinzugefuegt werden

    // todo: Im Moment werden nur Double, Long und String unterstuetzt. Ergaenzen Sie das bei Bedarf, z.B. durch Integer

    protected DoubleAttribute createDoubleAttribute(){
        return createDoubleAttribute(0.0);
    }

    protected DoubleAttribute createDoubleAttribute(double value) {
        DoubleAttribute attribute = new DoubleAttribute(value);
        allAttributes.add(attribute);

        return attribute;
    }

    protected IntegerAttribute createIntegerAttribute(){
        return createIntegerAttribute(0);
    }

    protected IntegerAttribute createIntegerAttribute(int value) {
        IntegerAttribute attribute = new IntegerAttribute(value);
        allAttributes.add(attribute);

        return attribute;
    }

    protected LongAttribute createLongAttribute() {
        return createLongAttribute(0L);
    }

    protected LongAttribute createLongAttribute(long value) {
        LongAttribute attribute = new LongAttribute(value);
        allAttributes.add(attribute);

        return attribute;
    }

    protected StringAttribute createStringAttribute(){
        return createStringAttribute(ELLIPSIS);
    }

    protected StringAttribute createStringAttribute(String value) {
        StringAttribute attribute = new StringAttribute(value);
        allAttributes.add(attribute);

        return attribute;
    }

    //************************************************************************************************

    /**
     * Wendet den Consumer auf alle Attribute an.
     *
     *
     * @param consumer gibt an was mit jedem Attribut gemacht werden soll
     */
    protected void forAllAttributes(Consumer<Attribute<?, ?, ?>> consumer) {
            allAttributes.forEach(consumer);
        }


    //************************************************************************************************
    //einige private Hilfs-Methoden

    private void checkDirtyState(){
        changed.set(someAttributeChanged());
    }

    private void checkValidState(){
        validProperty().set(allAttributesValid());
    }

    private boolean someAttributeChanged(){
        return allAttributes.stream().anyMatch(Attribute::isChanged);
    }

    private boolean allAttributesValid() {
        return allAttributes.stream().allMatch(Attribute::isValid);
    }


    //************************************************************************************************
    // alle getter und setter

    public boolean isChanged() {
        return changed.get();
    }

    public BooleanProperty changedProperty() {
        return changed;
    }

    public boolean isValid() {
        return valid.get();
    }

    public BooleanProperty validProperty() {
        return valid;
    }


}
