/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package algoritmogenetico;

import java.util.Vector;

/**
 *
 * @author USUARIO
 */


public class CromosomaNreinas {

    private Vector v;
    private int utilidad;
    private String estado;
    private double fitness;

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CromosomaNreinas(Vector vector) {
        //     vector = new Vector();
        this.v = vector;

    }

    public CromosomaNreinas() {
    }

    public int getUtilidad() {

        utilidad = costodecromosoma(v);
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public Vector getV() {
        return v;
    }

    public void setV(Vector v) {
        this.v = v;
    }

    public int costodecromosoma(Vector v) {
        int posiciondereyna = 0;
        int cantataques = 0;
        int numero = 0;
        int colactual = v.size();
        for (int coleva = 0; coleva < v.size(); coleva++) {
            if (coleva != v.size()) {
                posiciondereyna = Integer.parseInt(v.elementAt(coleva).toString());
                cantataques = cantataques + buscar(coleva, posiciondereyna, "derecha", "arriba", colactual, v)
                        + buscar(coleva, posiciondereyna, "derecha", "", colactual, v)
                        + buscar(coleva, posiciondereyna, "derecha", "abajo", colactual, v);
            }
            numero = cantataques;
        }
        cantataques = 0;
        return numero;

    }

    public int buscar(int columna, int fila, String movimientox, String movimientoy, int a, Vector v) {
        int x = 0;
        if ((fila > v.size() - 1) || (fila < 0) || (columna > v.size() - 1) || (columna < 0)) {

            return 0; //fin de recursividad si sale de dimension
        } else {
            if (movimientox.compareTo("derecha") == 0) {
                columna++;
            } else if (movimientox.compareTo("izquierda") == 0) {
                columna--;
            }
            if (movimientoy.compareTo("arriba") == 0) {
                fila--;
            } else if (movimientoy.compareTo("abajo") == 0) {
                fila++;
            }
        }
        if (columna != v.size() && columna != a) {
            if (v.elementAt(columna).equals(fila) && columna != v.size()) {
                x = 1;
            }
        }
        return x + buscar(columna, fila, movimientox, movimientoy, a, v);
    }

}
