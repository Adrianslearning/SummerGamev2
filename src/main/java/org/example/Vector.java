package org.example;

import java.util.Arrays;

public class Vector implements Cloneable {
    boolean isColomnVector = true;
    double[] entries;
    int size;

    /**
     * creates vector 0
     * <p>
     * for row vector set isColumn Vector to false
     */
    public Vector(int size) {
        entries = new double[size];
        Arrays.fill(entries, 0.0);
        this.size = size;
    }

    /**
     * creats the vector with sepcified entries
     * <p>
     * for row vector set isColumn Vector to false
     */
    public Vector(double[] entries) {
        this.entries = new double[entries.length];
        System.arraycopy(entries, 0, this.entries, 0, entries.length);
        this.size = entries.length;
    }

    public double skalarProduct(Vector v) {
        double product = 0.0;
        for (int i = 0; i < size; i++) {
            product += entries[i] * v.entries[i];
        }
        return product;
    }

    public double norm() {
        return Math.sqrt(this.skalarProduct(this));
    }

    public void add(Vector v) {
        for (int i = 0; i < size; i++) {
            entries[i] += v.entries[i];
        }
    }

    public double angle(Vector v) {
        return Math.acos(this.skalarProduct(v) / (this.norm() * v.norm()));
    }

    public void tranpose() {
        isColomnVector = !isColomnVector;
    }

    @Override
    public Vector clone() {
        double[] newEntries = Arrays.copyOf(entries, size);
        Vector v = new Vector(newEntries);
        v.isColomnVector = isColomnVector;
        return v;
    }
}
