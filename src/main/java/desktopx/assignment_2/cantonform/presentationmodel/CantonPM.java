package desktopx.assignment_2.cantonform.presentationmodel;

import desktopx.assignment_2.cantonform.presentationmodel.util.PMBase;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.DoubleAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.IntegerAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.LongAttribute;
import desktopx.assignment_2.cantonform.presentationmodel.util.attribute.StringAttribute;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import desktopx.assignment_2.cantonform.service.CantonDTO;

import java.util.Locale;

public class CantonPM extends PMBase<CantonDTO> {
    private final StringAttribute kanton          = createStringAttribute()
                                                    .caption(Locale.GERMANY, "Kanton")
                                                    .caption(Locale.UK, "Cantons");
    private final StringAttribute  kuerzel         = createStringAttribute()
                                                  .caption(Locale.GERMANY, "Kuerzel")
                                                 .caption(Locale.UK, "Abbr.");
    private final LongAttribute kantonsnummer   =  createLongAttribute()
                                                   .caption(Locale.GERMANY, "Kantonsnummer")
                                                   .caption(Locale.UK,      "Cantonsnumber");
    private final DoubleAttribute standesstimme   = createDoubleAttribute()
                                                 .caption(Locale.GERMANY, "Standesstimme")
                                                 .caption(Locale.UK,      "Class vote");;
    private final IntegerAttribute beitritt        = createIntegerAttribute()
                                                    .caption(Locale.GERMANY, "Beitritt")
                                                    .caption(Locale.UK, "Accession");
    private final StringAttribute  hauptort        = createStringAttribute()
                                                    .caption(Locale.GERMANY, "Hauptort")
                                                    .caption(Locale.UK, "Hauptort");
    private final IntegerAttribute einwohner       = createIntegerAttribute()
                                                    .caption(Locale.GERMANY, "Einwohner")
                                                    .caption(Locale.UK, "Resident");
    private final DoubleAttribute  auslaender      = createDoubleAttribute()
                                                     .caption(Locale.GERMANY, "Ausländer")
                                                     .caption(Locale.UK,      "Foreigner");
    private final DoubleAttribute  flaeche         = createDoubleAttribute()
                                                    .caption(Locale.GERMANY, "Fläche")
                                                     .caption(Locale.UK,      "Area");
    private final DoubleAttribute  einwohnerdichte = createDoubleAttribute()
                                                    .caption(Locale.GERMANY, "Einwohnerdichte")
                                                    .caption(Locale.UK,      "Population density");;
    private final IntegerAttribute gemeinden       = createIntegerAttribute()
                                                    .caption(Locale.GERMANY, "Gemeinden")
                                                     .caption(Locale.UK, "Communities");
    private final StringAttribute  amtssprache     = createStringAttribute()
                                                     .caption(Locale.GERMANY, "Amtsprache")
                                                     .caption(Locale.UK, "Amtsprache");

    public static CantonPM of(CantonDTO dto) {
        CantonPM pm = new CantonPM();
        pm.apply(dto);
        pm.rebase();

        return pm;
    }

    // eine Instanz von CantonPM kriegt man nur ueber die 'of'-Methode
    private CantonPM() {init();}

    @Override
    public void apply(CantonDTO cantonDTO) {
        getKanton().setValue(cantonDTO.getKanton());
        getKuerzel().setValue(cantonDTO.getKuerzel());
        getKantonsnummer().setValue(cantonDTO.getKantonsnummer());
        getStandesstimme().setValue(cantonDTO.getStandesstimme());
        getBeitritt().setValue(cantonDTO.getBeitritt());
        getHauptort().setValue(cantonDTO.getHauptort());
        getEinwohner().setValue(cantonDTO.getEinwohner());
        getAuslaender().setValue(cantonDTO.getAuslaender());
        getFlaeche().setValue(cantonDTO.getFlaeche());
        getEinwohnerdichte().setValue(cantonDTO.getEinwohnerdichte());
        getGemeinden().setValue(cantonDTO.getGemeinden());
        getAmtssprache().setValue(cantonDTO.getAmtssprache());
    }

    @Override
    public CantonDTO toDTO() {
        return new CantonDTO(getKanton().asString(),
            getKuerzel().asString(),
            getKantonsnummer().asString(),
            getStandesstimme().asString(),
            getBeitritt().asString(),
            getHauptort().asString(),
            getEinwohner().asString(),
            getAuslaender().asString(),
            getFlaeche().asString(),
            getEinwohnerdichte().asString(),
            getGemeinden().asString(),
            getAmtssprache().asString());
    }




    public StringAttribute getKanton() {
        return kanton;
    }

    public StringAttribute getKuerzel() {
        return kuerzel;
    }

    public LongAttribute getKantonsnummer() {
        return kantonsnummer;
    }

    public DoubleAttribute getStandesstimme() {
        return standesstimme;
    }

    public IntegerAttribute getBeitritt() {
        return beitritt;
    }

    public StringAttribute getHauptort() {
        return hauptort;
    }

    public IntegerAttribute getEinwohner() {
        return einwohner;
    }

    public DoubleAttribute getAuslaender() {
        return auslaender;
    }

    public DoubleAttribute getFlaeche() {
        return flaeche;
    }

    public DoubleAttribute getEinwohnerdichte() {
        return einwohnerdichte;
    }

    public IntegerAttribute getGemeinden() {
        return gemeinden;
    }

    public StringAttribute getAmtssprache() {
        return amtssprache;
    }
}
