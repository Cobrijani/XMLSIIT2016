package rs.ac.uns.ftn.dto.sednica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by Micko on 04-Mar-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SednicaDTO {

  private String id;

  private String naziv;

  private XMLGregorianCalendar datum;

  private String mesto;

}
