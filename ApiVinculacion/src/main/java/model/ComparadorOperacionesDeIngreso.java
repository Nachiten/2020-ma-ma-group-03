package model;

import java.util.Comparator;

public class ComparadorOperacionesDeIngreso implements Comparator<OperacionDeIngreso> {

    public int compare(OperacionDeIngreso a, OperacionDeIngreso b)
    {
        return Float.compare(a.getMontoTotal(), b.getMontoTotal());
    }
}
