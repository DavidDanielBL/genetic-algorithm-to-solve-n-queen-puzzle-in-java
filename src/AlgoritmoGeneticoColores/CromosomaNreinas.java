/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AlgoritmoGeneticoColores;


import java.util.Vector;

/**
 *
 * @author USUARIO
 */
public class CromosomaNreinas {

    private Vector<Alelo> v;
    private int costofitness;
    private String estado="Sobreviviente";

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public CromosomaNreinas(Vector<Alelo> vector) {
      //     vector = new Vector();
           this.v=vector;

    }

    public CromosomaNreinas() {
    }


    public int getCostofitness() {

      costofitness=costodecromosoma(v);
        return costofitness;
    }

    public void setCostofitness(int costofitness) {
        this.costofitness = costofitness;
    }

    public Vector<Alelo> getV() {
        return v;
    }

    public void setV(Vector<Alelo> v) {
        this.v = v;
    }

   public int costodecromosoma(Vector<Alelo> v) {
        int posiciondereyna = 0;
        int cantataques = 0;
        int numero = 0;
        int colactual = v.size();
        for (int coleva = 0; coleva < v.size(); coleva++) {
            if (coleva != v.size()) {
                posiciondereyna = v.elementAt(coleva).getDato();
                cantataques = cantataques + buscar(coleva, posiciondereyna, "derecha", "arriba", colactual, v)
                        + buscar(coleva, posiciondereyna, "derecha", "", colactual, v)
                        + buscar(coleva, posiciondereyna, "derecha", "abajo", colactual, v);
            }
            numero = cantataques;
        }
        cantataques = 0;
        return numero;


    }
        public int buscar(int columna, int fila, String movimientox, String movimientoy, int a, Vector<Alelo> v) {
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
            if (v.elementAt(columna).getDato()==fila && columna != v.size()) {
                x = 1;
            }
        }
        return x + buscar(columna, fila, movimientox, movimientoy, a, v);
    }


}
