package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by Piotr on 2017-03-11.
 */
class ArraysFunctions {

    static int indexOfMaxElement(double[] array) {
        int idxOfMax = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[idxOfMax]) {
                idxOfMax = i;
            }
        }
        return idxOfMax;
    }

    static int indexOfMinElement(double[] array) {
        int idxOfMin = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < array[idxOfMin]) {
                idxOfMin = i;
            }
        }
        return idxOfMin;
    }
}
