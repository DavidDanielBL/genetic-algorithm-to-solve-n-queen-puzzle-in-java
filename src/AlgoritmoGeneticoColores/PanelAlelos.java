/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGeneticoColores;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextField;

/**
 *
 * @author USUARIO
 */
public class PanelAlelos extends JPanel {

    ColorPane pane;
    public JTextField titulo;
    public JLabel prompt;
    public ColorPane pantalla;
    public JPanel p1, p2, p3;
    public JButton btSalir;
    public Color B, F1, F2, F3; //colores

    public Component consola(String text) {
        pane = new ColorPane();

        B = Color.BLACK;
        F1 = Color.green;
        p1 = new JPanel(new BorderLayout()); // panel Principal
        p2 = new JPanel(new GridLayout(1, 1)); // panel para 3 botones en BorderLayout.SOUTH
        p3 = new JPanel(new GridLayout(1, 1));
        titulo = new JTextField(40);
        titulo.setToolTipText("Muestra por alelo");
        pantalla = new ColorPane();
        pantalla.setToolTipText("SE MUESTRA RESULTADO");
        btSalir = new JButton("Salida");
        btSalir.setToolTipText("Salir de programa");
        btSalir.addActionListener(new cerrarVentana());
        btSalir.setBackground(Color.black);
        btSalir.setForeground(Color.GREEN);
        p1.setBackground(Color.black);
        p2.add(btSalir);
        p3.setBackground(Color.ORANGE);
        titulo.setBackground(Color.BLACK);
        titulo.setForeground(Color.GREEN);

        titulo.setText("Cromosomas coloreados para 6 reinas");
        titulo.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
        titulo.setEditable(false);
        p3.add(titulo);

        pantalla.setEditable(true);
        pantalla.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
        pantalla.setBackground(Color.black);
        pantalla.setForeground(F1);

        Vector<Alelo> v = new Vector<Alelo>();
        List<CromosomaNreinas> poblacion;
        poblacion = new ArrayList<CromosomaNreinas>();
        AlgoritmogeneticoNreinas ab = new AlgoritmogeneticoNreinas();
        int nropobla = 6;
        for (int i = 0; i < nropobla; i++) {
            poblacion.add(i, ab.generarpoblacioninicial(5, v));
        }
        System.out.println("Poblacion INICIAL");
        pantalla.append(Color.white, "Poblacion Inicial" + "\n");
        System.out.print("\n");
        for (CromosomaNreinas cro : poblacion) {

            System.out.println(cro.getCostofitness());
            for (Alelo ale : cro.getV()) {
                System.out.print(ale.getDato());
                pantalla.append(new Color(ale.getColor().elementAt(0).hashCode(), ale.getColor().elementAt(1).hashCode(), ale.getColor().elementAt(2).hashCode()), String.valueOf(ale.getDato()));


            }
            pantalla.append(Color.WHITE, "   Utilidad : " + String.valueOf(cro.getCostofitness()));
            pantalla.append(Color.BLUE, "\n");
            System.out.println("\n");
        }

        int iteraciones = 0;

        int Padres = nropobla - 1;

        while (iteraciones < 10) {

            AlgoritmogeneticoNreinas a = new AlgoritmogeneticoNreinas();

            a.setPadres(Padres);
            a.setHijos(new ArrayList<CromosomaNreinas>());


            a.GenerarDescendencia(1, 0.6, poblacion, a.getPadres());
            pantalla.append(Color.white, "\nPoblacion Ordenada \n");
            for (CromosomaNreinas cro : a.getPoblacionordenada()) {

                System.out.println(cro.getCostofitness());
                for (Alelo ale : cro.getV()) {
                    System.out.print(ale.getDato());
                    pantalla.append(new Color(ale.getColor().elementAt(0).hashCode(), ale.getColor().elementAt(1).hashCode(), ale.getColor().elementAt(2).hashCode()), String.valueOf(ale.getDato()));
                }
                pantalla.append(Color.WHITE, "   Utilidad : " + String.valueOf(cro.getCostofitness()));
                pantalla.append(Color.white, "\n");
                System.out.println("\n");
            }

            pantalla.append(Color.white, "\n" + "Cantidad de Padres Seleccionados: " + a.getPadres());
            pantalla.append(Color.white, "\n" + "Tasa de cruzamiento: " + a.getTasadecruce());
            pantalla.append(Color.white, "\n" + "Cantidad de Hijos cruzados: (" + a.getTasadecruce() + "*" + a.getPadres() + ")≈ "
                    + a.getCantidadhijoscruzados());
            pantalla.append(Color.white, "\n" + "Copias de Padres(menor igual a): " + a.getPadres());
            pantalla.append(Color.white, "\n" + "Tasa de Mutacion: " + a.getTasademutacion());
            pantalla.append(Color.white, "\n" + "Cantidad de Hijos Mutados:(" + a.getTasademutacion() + "*" + a.getCantidadhijoscruzados() + ")≈ "
                    + a.getCantidadhijosmutados());
            pantalla.append(Color.white, "\n" + "Linea de Cruce : " + String.valueOf(a.getLineadecruce()));
            pantalla.append(Color.white, "\n" + "Mejor Cromosoma " + "\n");
            for (Alelo ale : a.getMejorfitness().getV()) {
                System.out.print(ale.getDato());
                pantalla.append(new Color(ale.getColor().elementAt(0).hashCode(), ale.getColor().elementAt(1).hashCode(), ale.getColor().elementAt(2).hashCode()), String.valueOf(ale.getDato()));
            }
            pantalla.append(Color.white, "\n" + "Mejor Utilidad " + String.valueOf(a.getMejorfitness().getCostofitness()) + "\n");
            pantalla.append(Color.white, "\n" + "HIJOS \n");
            for (CromosomaNreinas hijos : a.getHijos()) {

                for (Alelo ale : hijos.getV()) {
                    System.out.print(ale.getDato());
                    pantalla.append(new Color(ale.getColor().elementAt(0).hashCode(), ale.getColor().elementAt(1).hashCode(), ale.getColor().elementAt(2).hashCode()), String.valueOf(ale.getDato()));

                }
                pantalla.append(Color.WHITE, "   Utilidad : " + String.valueOf(hijos.getCostofitness()));
                pantalla.append(Color.WHITE, "   Estado : " + String.valueOf(hijos.getEstado()));
                pantalla.append(Color.white, "\n");
                System.out.print("\n");

            }
            a.mutacion(a.getHijos(), a.getTasademutacion());
            poblacion = a.getHijos();
            iteraciones++;

        }

        JScrollPane pScroll = new JScrollPane(pantalla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pScroll.setBackground(Color.BLACK);
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p1.add(p2, BorderLayout.SOUTH);
        p1.add(p3, BorderLayout.NORTH);
        p1.add(pScroll, BorderLayout.CENTER);




        p1.setFocusable(true);

        return p1;
    }

    public class cerrarVentana implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
          
         System.exit(0);



        }
    }

}
