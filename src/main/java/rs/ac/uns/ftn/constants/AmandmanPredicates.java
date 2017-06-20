package rs.ac.uns.ftn.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Arsa on 6/19/2017.
 */
public class AmandmanPredicates {

  private AmandmanPredicates() {
    throw new UnsupportedOperationException("Constructor should not be initialized");
  }

  public static final String DATUM_AZURIRANJA = "datumAzuriranja";
  public static final String DATUM_KREIRANJA = "datumKreiranja";
  public static final String NAPRAVIO = "napravio";
  public static final String IME_DOKUMENTA = "imeDokumenta";
  public static final String STANJE = "stanje";
  public static final String VERZIJA = "verzija";

  public static final List<String> ALL_AMANDMAN_PREDICATES = Arrays.asList(DATUM_AZURIRANJA, DATUM_KREIRANJA,
    NAPRAVIO, IME_DOKUMENTA, STANJE, VERZIJA);

}
