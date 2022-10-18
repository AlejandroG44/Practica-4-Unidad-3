
package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ed.ito.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;



public class MyApp extends JFrame {

    private JPanel panelPrincipal;
    private JButton [] numeros;
    private JButton arriba,abajo,izquierda,derecha,atras,adelante;
    private Puzzle8 puzzle8;
    private PilaEstatica<Integer> pila;
    private PilaEstatica<Integer> pila2;
    
    public MyApp(){
        super("Puzzle-8");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponentes();
        puzzle8= new Puzzle8();
        pila=new PilaEstatica<Integer>(100);
        pila2=new PilaEstatica<Integer>(100);
        llenaTablero();
        this.pack();
    }
    // Inicializa tablero visible en pantalla con el contenido del arreglo bidimencional de la instancia de Puzzle8
    private void llenaTablero(){
        int [][] pz= puzzle8.getPuzzle();
        for(int i=0;i<numeros.length;i++)
            numeros[i].setText(""+pz[i/3][i%3]);      
    }

    // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void arribaValida(ActionEvent e){
        if(puzzle8.arriba()){
            llenaTablero();
            if(puzzle8.haTerminado()){
                JOptionPane.showMessageDialog(null, "Se logró!!!");
                System.exit(0);
            }
            try{
                pila.push(2);
                if(!pila2.isEmpty())
                    pila2=new PilaEstatica<Integer>(100);
            }catch(ExcepcionDeDesbordamientoDePila e2)
            {
                JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
            }
        }
        
    }
    // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void abajoValida(ActionEvent e){
        if(puzzle8.abajo()){
            llenaTablero();
            try{
                pila.push(1);
                if(!pila2.isEmpty())
                    pila2=new PilaEstatica<Integer>(100);
            }catch(ExcepcionDeDesbordamientoDePila e2)
            {
                JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
            }
        }
        
    }
    // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void izquierdaValida(ActionEvent e){
        if(puzzle8.izquierda()){
            llenaTablero();
            try{
                pila.push(4);
                if(!pila2.isEmpty())
                    pila2=new PilaEstatica<Integer>(100);
            }catch(ExcepcionDeDesbordamientoDePila e2)
            {
                JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
            }
        }
    }
    // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void derechaValida(ActionEvent e){
       if(puzzle8.derecha()){
            llenaTablero();
            try{
                pila.push(3);
                if(!pila2.isEmpty())
                    pila2=new PilaEstatica<Integer>(100);
            }catch(ExcepcionDeDesbordamientoDePila e2)
            {
                JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
            }
        }
    }
     // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void retrocede(ActionEvent e) {
        try{
            if(!pila.isEmpty()){
                int n=pila.pop();
                switch(n){
                    case 1: puzzle8.arriba(); 
                    break;
                    case 2: puzzle8.abajo();
                    break;
                    case 3: puzzle8.izquierda();
                    break;
                    case 4: puzzle8.derecha();
                }
                llenaTablero();
                pila2.push(n);
            }
        }catch (ExcepcionDeDesbordamientoDePila e2){
            JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
        } catch (ExcepcionDePilaVacia ex) {
            JOptionPane.showMessageDialog(null, "");
                System.exit(0);
        }
    }
     // Este mÃ©todo deberÃ¡ contener el cÃ³digo correspondiente
    private void avanza(ActionEvent e){
       try{
            if(!pila2.isEmpty()){
                int n=pila2.pop();
                switch(n){
                    case 2: puzzle8.arriba(); 
                    break;
                    case 1: puzzle8.abajo();
                    break;
                    case 4: puzzle8.izquierda();
                    break;
                    case 3: puzzle8.derecha();
                }
                llenaTablero();
                pila.push(n);
            }
        }catch (ExcepcionDeDesbordamientoDePila e2){
            JOptionPane.showMessageDialog(null, "Sin oportunidades");
                System.exit(0);
        } catch (ExcepcionDePilaVacia ex) {
            Logger.getLogger(MyApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponentes(){
        this.getContentPane().setLayout(new GridLayout(4,3));
        
        this.getContentPane().add(new JLabel(""));
        arriba= new JButton("Arriba");
        arriba.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                      arribaValida(e);
                }
        });
        this.getContentPane().add(arriba);
        this.getContentPane().add(new JLabel(""));
        izquierda= new JButton("Izquierda");
        izquierda.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                     izquierdaValida(e);
            }
        });
        this.getContentPane().add(izquierda);
        panelPrincipal= new JPanel(new GridLayout(3,3));
        numeros= new JButton[9];
        for(int i=0;i<numeros.length;i++){
            numeros[i]= new JButton(""+i);
            numeros[i].setEnabled(false);
            numeros[i].setBackground(new Color(5,122,123));
            numeros[i].setFont(new Font("Arial Bold",Font.BOLD,28));
            panelPrincipal.add(numeros[i]);
        }
        this.getContentPane().add(panelPrincipal);
        derecha= new JButton("Derecha");
        derecha.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                derechaValida(e);
            }
        });
        this.getContentPane().add(derecha);
        this.getContentPane().add(new JLabel());
        abajo= new JButton("Abajo");
        abajo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 abajoValida(e);
            }
        });
        this.getContentPane().add(abajo);
        this.getContentPane().add(new JLabel());
        atras= new JButton("Atras");
        atras.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    retrocede(e);
            }
        });
        this.getContentPane().add(atras);
        this.getContentPane().add(new JLabel());
        adelante= new JButton("Adelante");
        adelante.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                     avanza(e);
            }
        });
        this.getContentPane().add(adelante);

    }
    public static void main(String [] args){
          new MyApp().setVisible(true);;
    }
}
