package model;

import java.util.Comparator;

class ComparadorOperacionesDeEgreso implements Comparator<OperacionDeEgreso> {

    public int compare(OperacionDeEgreso a, OperacionDeEgreso b)
    {
        return Float.compare(a.getMontoTotal(), b.getMontoTotal());
    }
}
