//*

package algoritmogenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class AlgoritmogeneticoNreinas {

    public String[][] M;
    public List<CromosomaNreinas> poblacionordenada;
    public Vector ArrayVectores[];
    public int mejor = 10000;
    public int lineadecruce;
    public List<CromosomaNreinas> Hijos;
    public List<CromosomaNreinas> SelecciRuleta;
    public int combinatoriadehijos;
    public int cantidadhijoscruzados;
    public int cantidadhijosmutados;
    public int Padres;
    public double tasadecruce;
    public double tasademutacion;
    public CromosomaNreinas mejorfitness;
    public int totalutilidad;
    public double totalfitness;
    Comparator comparacion;
    Vector temp;
    List<Vector> GenesA;
    List<Vector> GenesB;
    Vector GenA;
    Vector GenB;
    Vector temporal;
    Vector temporal3;
    Vector temporal2;

    public int getTotalutilidad() {
        return totalutilidad;
    }

    public void setTotalutilidad(int totalutilidad) {
        this.totalutilidad = totalutilidad;
    }

    public double getTotalfitness() {
        return totalfitness;
    }

    public void setTotalfitness(int totalfitness) {
        this.totalfitness = totalfitness;
    }

    public CromosomaNreinas getMejorfitness() {
        return mejorfitness;
    }

    public void setMejorfitness(CromosomaNreinas mejorfitness) {
        this.mejorfitness = mejorfitness;
    }

    public int getCantidadhijosmutados() {
        return cantidadhijosmutados;
    }

    public void setCantidadhijosmutados(int cantidadhijosmutados) {
        this.cantidadhijosmutados = cantidadhijosmutados;
    }

    public double getTasademutacion() {
        return tasademutacion;
    }

    public void setTasademutacion(double tasademutacion) {
        this.tasademutacion = tasademutacion;
    }

    public double getTasadecruce() {
        return tasadecruce;
    }

    public void setTasadecruce(double tasadecruce) {
        this.tasadecruce = tasadecruce;
    }

    public int getCantidadhijoscruzados() {
        return cantidadhijoscruzados;
    }

    public void setCantidadhijoscruzados(int cantidadhijoscruzados) {
        this.cantidadhijoscruzados = cantidadhijoscruzados;
    }

    public int getPadres() {
        return Padres;
    }

    public void setPadres(int Padres) {
        this.Padres = Padres;
    }

    public int getCombinatoriadehijos() {
        return combinatoriadehijos;
    }

    public void setCombinatoriadehijos(int combinatoriadehijos) {
        this.combinatoriadehijos = combinatoriadehijos;
    }

    public List<CromosomaNreinas> getHijos() {
        return Hijos;
    }

    public void setHijos(List<CromosomaNreinas> Hijos) {
        this.Hijos = Hijos;
    }

    public List<CromosomaNreinas> getPoblacionordenada() {
        return poblacionordenada;
    }

    public void setPoblacionordenada(List<CromosomaNreinas> poblacionordenada) {
        this.poblacionordenada = poblacionordenada;
    }

    public int getLineadecruce() {
        return lineadecruce;
    }

    public void setLineadecruce(int lineadecruce) {
        this.lineadecruce = lineadecruce;
    }

    public AlgoritmogeneticoNreinas() {
        M = new String[1000][1000];
        ArrayVectores = new Vector[1000];
        GenesA = new Vector();
        GenesB = new Vector();
        temporal = new Vector();
        temporal2 = new Vector();
        temporal3 = new Vector();
        GenA = new Vector();
        GenB = new Vector();
        //  Hijos = new Vector();
        Hijos = new ArrayList<CromosomaNreinas>();
    }
    //Poblacion inicial

    public void poblacioninicial(int n, Vector v) {
        v.clear();
        for (int i = 0; i < n; i++) {
            v.insertElementAt(0, i);
        }
        System.out.println(v);

        if (n <= 100) {
            imprimirreinas(v);
        }
    }

    public CromosomaNreinas generarpoblacioninicial(int n, Vector v) {
        //Ingresamos el valor de la dimension de reinas
        int valor;
        CromosomaNreinas cromosoma = new CromosomaNreinas(v);
        v = new Vector();
        double m = n * (1.0);

        for (int i = 0; i < n; i++) {
            valor = (int) (Math.random() * m);
            v.insertElementAt(valor, i);
        }
        cromosoma.setV(v);
        System.out.println("Fitness" + cromosoma.getUtilidad());
        System.out.println("dsaFitness" + cromosoma.getV());
        if (n <= 100) {
            imprimirreinas(v);
        }
        return cromosoma;
    }

    public void imprimirreinas(Vector v) {
        String texto = "";
        String matrizA = "\n";
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < v.size(); j++) {
                if (v.elementAt(j).equals(i)) {
                    M[i][j] = "XX";
                    matrizA += "   " + M[i][j];

                } else {
                    M[i][j] = "00";
                    matrizA += "   " + M[i][j];
                }

            }
            matrizA += "\n";
        }

        texto = "[Matriz de cromosomas]" + matrizA + " " + texto + " ";
        System.out.println(texto);


    }
//    public void seleccionruleta(List<CromosomaNreinas> poblacion) //Ineficiente para poblacion grande
//    {
//        int sumautilidades = 0;
//        int sumadefitness = 0;
//        int sumatoria=0;
//        Vector seleccion = new Vector();
//        for (CromosomaNreinas cro : poblacion) {
//            sumautilidades += cro.getUtilidad();
//        }
//        setTotalutilidad(sumautilidades);
//        for (CromosomaNreinas cro : poblacion) {
//            cro.setFitness(getTotalutilidad() / cro.getUtilidad());
//        }
//        for (CromosomaNreinas cro : poblacion) {
//            sumadefitness += cro.getFitness();
//        }
//        setTotalfitness(sumadefitness);
//        double valoraleatorio = Math.random() * sumadefitness;
//        System.out.println(valoraleatorio);
//        while(sumatoria <valoraleatorio)
//        {
//         for (CromosomaNreinas cro : poblacion) {
//         sumatoria+=cro.getFitness();
//        }
//        }
//
//    }
    public List<CromosomaNreinas> GenerarDescendencia(double tasadecruce, double tasdemutacion, List<CromosomaNreinas> poblacion, int padres) {
        Padres = getPadres();//La cantidad de Padres viene de la seleccion
        setTasadecruce(tasadecruce);
        double tasacruces = (tasadecruce * Padres);
        double tasacrucess = Math.rint(tasacruces);
        int hijoscruz = (int) tasacrucess;
        setCantidadhijoscruzados(hijoscruz);


        System.out.println("Cantidad de Padres" + Padres);
        ordena(poblacion, new Comparator() {

            public int compare(Object cromosoma1, Object cromosoma2) {
                CromosomaNreinas cromo1 = (CromosomaNreinas) cromosoma1;
                CromosomaNreinas cromo2 = (CromosomaNreinas) cromosoma2;
                if (cromo1.getUtilidad() > cromo2.getUtilidad()) {
                    return -1;
                } else if (cromo1.getUtilidad() < cromo2.getUtilidad()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
     
        poblacionordenada = poblacion;
        for (CromosomaNreinas cro : poblacion) {
            System.out.println(cro.getUtilidad());
            System.out.println(cro.getV());
        }
        setMejorfitness(poblacion.get(Padres));
        crossover(poblacion, getPadres());
        getHijos().add(getMejorfitness());
        mutacion(getHijos(), tasdemutacion);
        System.out.println("HIJOS MUTADOS Y CRUZADOS");
        System.out.println("cantidad de hijos mutados" + getCantidadhijosmutados());
        for (CromosomaNreinas hijos : Hijos) {
            System.out.println(hijos.getV());
        }
        return Hijos;

    }

    private static void ordena(List<CromosomaNreinas> lista, Comparator comparator) {
        Collections.sort(lista, new Comparator() {

            public int compare(Object cromosomaA, Object cromosomaB) {
                CromosomaNreinas cromo1 = (CromosomaNreinas) cromosomaA;
                CromosomaNreinas cromo2 = (CromosomaNreinas) cromosomaB;
                if (cromo1.getUtilidad() > cromo2.getUtilidad()) {
                    return -1;
                } else if (cromo1.getUtilidad() < cromo2.getUtilidad()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public Vector GenesA(CromosomaNreinas cromosoma, int lineadecruce) {
        temp = new Vector();
        int limite = lineadecruce;
        for (int i = limite; i < cromosoma.getV().size(); i++) {
            temp.add(cromosoma.getV().elementAt(i));
        }
        return temp;
    }

    public Vector GenesB(CromosomaNreinas cromosoma, int lineadecruce) {
        temp = new Vector();
        int limite = lineadecruce;
        for (int i = 0; i < limite; i++) {
            temp.add(cromosoma.getV().elementAt(i));
        }
        return temp;
    }

    public void crossover(List<CromosomaNreinas> poblacion, int Padres) {
        int contadorhijoscruzad = 0;
        lineadecruce = (int) (Math.random() * (poblacion.get(0).getV().size() - 1) + 1);

        System.out.println("LINEA DE CRUCE " + lineadecruce);
        for (int j = 0; j < poblacion.size(); j++) {
            GenA = GenesA(poblacion.get(j), lineadecruce);
            GenesA.add(GenA);
        }
        for (int j = 0; j < poblacion.size(); j++) {
            GenB = GenesB(poblacion.get(j), lineadecruce);
            GenesB.add(GenB);
        }
        int i = 0;
        while (contadorhijoscruzad < Padres) {
            if (contadorhijoscruzad < getCantidadhijoscruzados()) {
                temporal.addAll(GenesB.get(i));
                temporal.addAll(GenesA.get(i + 1));
                CromosomaNreinas hijo = new CromosomaNreinas(temporal);
                hijo.setEstado("Hijo Cruzado");
                Hijos.add(hijo);
                contadorhijoscruzad++;
            }
            if (contadorhijoscruzad < getCantidadhijoscruzados()) {
                temporal2.addAll(GenesB.get(i + 1));
                temporal2.addAll(GenesA.get(i));
                CromosomaNreinas hijo = new CromosomaNreinas(temporal2);
                hijo.setEstado("Hijo Cruzado");
                contadorhijoscruzad++;
                Hijos.add(hijo);
            }
            if (contadorhijoscruzad >= getCantidadhijoscruzados() && getTasadecruce() != 1) {
                temporal3.addAll(poblacion.get(contadorhijoscruzad).getV());
                CromosomaNreinas hijo = new CromosomaNreinas(temporal3);
                hijo.setEstado("Hijo Copia de Padre");
                Hijos.add(hijo);
                contadorhijoscruzad++;
            }
            temporal = new Vector();
            temporal2 = new Vector();
            temporal3 = new Vector();
            i += 2;


        }

    }

    public void mutacion(List<CromosomaNreinas> hijoscruzados, double tasademutacion) {
        setTasademutacion(tasademutacion);
        double tasamutacion = tasademutacion * getCantidadhijoscruzados();
        double tasamut = Math.rint(tasamutacion);
        int hijosmutados = (int) tasamut;
        setCantidadhijosmutados(hijosmutados);
        for (int i = 0; i < hijosmutados; i++) {
            permutacion(hijoscruzados.get(i).getV());
            hijoscruzados.get(i).setEstado("Cruzado y Mutado");
        }
    }

    public void permutacion(Vector v) {
        int aleloA;
        int aleloB;

        int limite = v.size();
        do {
            aleloA = (int) ((Math.random() * limite - 1) + 1);
            aleloB = (int) ((Math.random() * limite - 1) + 1);
        } while (aleloA == aleloB);
        Object alelotemporalA = v.elementAt(aleloA);
        Object alelotemporalB = v.elementAt(aleloB);
        v.setElementAt(alelotemporalA, aleloB);
        v.setElementAt(alelotemporalB, aleloA);
    }


}
