package rs.ac.uns.ftn.dto.sednica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Micko on 15-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SednicaPostDTO{
  private String id;

  private String naziv;

  private XMLGregorianCalendar datum;

  private String mesto;

  private String[] akti;

  private String[][] amandmani;

  @Override
  public String toString() {
    return "SednicaPostDTO{" +
      "id='" + id + '\'' +
      ", naziv='" + naziv + '\'' +
      ", datum='" + datum + '\'' +
      ", mesto='" + mesto + '\'' +
      ", amandmani=" + Arrays.toString(amandmani) +
      ", aktovi=" + Arrays.toString(akti) +
      '}';
  }
}
