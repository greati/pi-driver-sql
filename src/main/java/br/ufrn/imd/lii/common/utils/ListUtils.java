package br.ufrn.imd.lii.common.utils;

import org.apache.commons.lang3.tuple.Triple;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ListUtils {

    /**
     * Collect items until a predicate returns false.
     * Takes a start index and the predicate as parameters.
     *
     * @param items
     * @param fromIndex
     * @param predicate
     * @return
     */
    public static <T> Triplet<Optional<Integer>, Integer, List<T>> collectUntil(List<T> items,
                                                                                Integer fromIndex,
                                                                                Function<T, Boolean> predicate) {
        Integer currentIndex = fromIndex + 1;
        List<T> inBetweenItems = new ArrayList<>();
        while (currentIndex < items.size() && predicate.apply(items.get(currentIndex))) {
            inBetweenItems.add(items.get(currentIndex));
            ++currentIndex;
        }

        if (currentIndex >= items.size())
            return Triplet.with(Optional.empty(), currentIndex - fromIndex, inBetweenItems);

        return Triplet.with(Optional.of(currentIndex), currentIndex - fromIndex, inBetweenItems);
    }

    public static <T> List<Pair<Integer,T>> findMaxItems(List<T> items, BiFunction<T, T, Boolean> comparator) {
        if (items.isEmpty())
            return Collections.emptyList();
        T maxItem = items.get(0);
        for (T i : items) {
            if (comparator.apply(maxItem, i))
                maxItem = i;
        }
        List<Pair<Integer,T>> maxItems = new ArrayList<>();
        for (int i = 0; i < items.size(); ++i) {
            T item = items.get(i);
            if (item.equals(maxItem))
                maxItems.add(Pair.with(i, item));
        }
        return maxItems;
    }

}
