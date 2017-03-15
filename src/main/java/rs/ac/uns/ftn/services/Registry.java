package rs.ac.uns.ftn.services;

/**
 * Created by SBratic on 3/15/2017.
 */
public interface Registry<K, V> {

  V getItemFromRegistry(K key);
}
