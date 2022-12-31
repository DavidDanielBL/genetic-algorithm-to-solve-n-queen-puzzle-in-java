/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AlgoritmoGeneticoColores;



import java.awt.Component;


import java.awt.GridLayout;

import javax.swing.JPanel;


public class Ventana extends JPanel {
   
public Ventana() 
    {
       PanelAlelos con = new PanelAlelos();
     
       this.setBounds(380,420,300, 300);
       this.setSize(300,300);
       this.setVisible(true);
             
        Component consola2 = con.consola("Consola"); 
        setLayout(new GridLayout(1, 1));
        add(consola2);

    
    }

  
}
