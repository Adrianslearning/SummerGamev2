package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class nXnMAtrix implements Cloneable {
    int n;
    double[][] matEntries;
    protected static final Logger logger = LogManager.getLogger();

    /**
     * produces 0 matrix
     */
    public nXnMAtrix(int n) {
        this.n = n;
        matEntries = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matEntries[i][j] = 0.0;
            }
        }
    }

    /**
     * produces a matrix with specified entries
     */
    public nXnMAtrix(double[][] matEntries) {
        this.n = matEntries.length;
        this.matEntries = matEntries;
    }

    public static nXnMAtrix In(int n) {
        nXnMAtrix In = new nXnMAtrix(n);
        int i = 0;
        while (i < n) {
            In.matEntries[i][i] = 1;
            i++;
        }
        return In;
    }

    public void add(nXnMAtrix other) {
        if (other.n != this.n) {
            logger.error("Error: nXnMAtrix dimension does not match other in add");
        } else {
            for (int i = 0; i < other.n; i++) {
                for (int j = 0; j < other.n; j++) {
                    this.matEntries[i][j] += other.matEntries[i][j];
                }
            }
        }
    }

    public void times(nXnMAtrix other) {
        if (other.n != this.n) {
            logger.error("Error: nXnMAtrix dimension does not match other in times");
        } else {
            for (int i = 0; i < other.n; i++) {
                for (int j = 0; j < other.n; j++) {
                    for (int k = 0; k < this.n; k++) {
                        this.matEntries[i][j] += this.matEntries[i][k] * other.matEntries[k][j];
                    }
                }
            }
        }
    }

    public Vector vectorTimesMatrix(Vector v) {
        Vector result = new Vector(v.size);
        if (v.size == this.n) {
            if (v.isColomnVector) {
                for (int i = 0; i < this.n; i++) {
                    for (int j = 0; j < this.n; j++) {
                        result.entries[i] += v.entries[i] * this.matEntries[j][i];
                    }
                }
            } else {
                for (int i = 0; i < this.n; i++) {
                    for (int j = 0; j < this.n; j++) {
                        result.entries[i] += v.entries[j] * this.matEntries[i][j];
                    }
                }

            }
        } else {
           logger.error("Error: nXnMAtrix dimension does not match other vectortimes");
        }
        return result;
    }


    @Override
    public nXnMAtrix clone() {
        double[][] newMatEntries = Arrays.copyOf(matEntries, matEntries.length);
        return new nXnMAtrix(newMatEntries);
    }
}
