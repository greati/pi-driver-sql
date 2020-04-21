package br.ufrn.imd.lii.pidriver.dao;

import br.ufrn.imd.lii.pidriver.exception.DataAccessException;
import br.ufrn.imd.lii.pidriver.model.PiItemValue;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Data access class for PI stored values.
 *
 * @author Vitor Greati
 */
public interface PiItemValueDAO extends AutoCloseable {

    /**
     * Seach for PI Item values.
     *
     * @param tag
     * @param initDate
     * @param finalDate
     * @param nPoints
     * @param minValue
     * @param maxValue
     * @return
     */
    List<PiItemValue> search(String tag,
                             Optional<Pair<Date, Boolean>> initDate,
                             Optional<Pair<Date, Boolean>> finalDate,
                             Optional<Integer> nPoints,
                             Optional<Pair<Float, Boolean>> minValue,
                             Optional<Pair<Float, Boolean>> maxValue)  throws DataAccessException;

}
