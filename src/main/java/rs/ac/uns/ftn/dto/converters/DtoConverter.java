package rs.ac.uns.ftn.dto.converters;

/**
 * Interface that defines set of methods responsible from converting from dto to model and reverse
 *
 * @param <T1>
 * @param <D1> Created by Arsa on 15-Feb-17.
 */
@FunctionalInterface
public interface DtoConverter<T1, D1> {

  /**
   * Update given entity with passed dto object
   *
   * @param dto    dto object that holds updated data
   * @param entity model to be updated
   * @param <T2>   type of class that is being updated
   * @param <D2>   type of dto class that holds data
   * @return updated model
   */
  <T2 extends T1, D2 extends D1> T2
  convert(D2 dto, T2 entity);
}
