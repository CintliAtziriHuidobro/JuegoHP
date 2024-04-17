
package todo;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Juego {
    
    JFrame ventana;
    JPanel panelPresentacion;
    JLabel fondoPresentacion;
    JLabel BotonJugar;
    JLabel Letras;
    
    JPanel panelJuego;
    JLabel fondoJuego;
    
    JLabel matriz[][];
    int mat [][];
    int matAux[][];
    Random aleatorio;
    
    String jugador;
    
    JLabel nombreJugador;
    Timer tiempo;
    JLabel cronometro;
    int min;
    int seg;
    
    int contador;
    Timer tiempocarta;
    int contadorcarta;
    Timer tiempocarta1;
    int ban;
    int ban1;
    
    int num;
    int antx;
    int anty;
    int actnum;
    int actx;
    int acty;
  
    
    
    
    
    
    //constructor
    
    public Juego(){
        
        //ventana
        ventana = new JFrame ("Adivina");
        ventana.setSize(1173,725);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        

        
        //JPanel presentacion
        panelPresentacion = new JPanel ();
        panelPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);
        
        
        //Fondo
        fondoPresentacion = new JLabel();
        fondoPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        fondoPresentacion.setLocation(0, 0);
        fondoPresentacion.setIcon(new ImageIcon("imagenes/cas.png"));
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion, 0);
        
      
        //botonjugar
        BotonJugar = new JLabel();
        BotonJugar.setSize(300, 300);
        BotonJugar.setLocation(800, 35);
        BotonJugar.setIcon(new ImageIcon("imagenes/jugar.png"));
        BotonJugar.setVisible(true);
        panelPresentacion.add(BotonJugar, 0);
        
        //letrasmemorama
        Letras = new JLabel();
        Letras.setSize(300, 300);
        Letras.setLocation(0, 0);
        Letras.setIcon(new ImageIcon("imagenes/memorama.png"));
        Letras.setVisible(true);
        panelPresentacion.add(Letras, 0);
        
        
        
        //panel juego
        panelJuego = new JPanel ();
        panelJuego.setSize(ventana.getWidth(), ventana.getHeight());
        panelJuego.setLocation(0, 0);
        panelJuego.setLayout(null);
        panelJuego.setVisible(true);
        
        
        //fondojuego
        fondoJuego = new JLabel();
        fondoJuego.setSize(ventana.getWidth(), ventana.getHeight());
        fondoJuego.setLocation(0, 0);
        fondoJuego.setIcon(new ImageIcon("imagenes/fondoeste.png"));
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego, 0);
        
   
        //nombre del jugador
        nombreJugador = new JLabel();
        nombreJugador.setSize(200,200);
        nombreJugador.setLocation(950, 80);
        nombreJugador.setForeground(Color.black);
        nombreJugador.setFont(new Font("Times New Roman",0,30));
        nombreJugador.setVisible(true);
        panelJuego.add(nombreJugador, 0);
        
        //cronometro
        cronometro = new JLabel();
        cronometro.setSize(150,30);
        cronometro.setLocation(0, 0);
        cronometro.setForeground(Color.black);
        cronometro.setVisible(true);
        panelJuego.add(cronometro, 0);
        
        //matriz logica
        mat = new int [6][7]; //asignar memoria
        matAux = new int [6][7];
        aleatorio = new Random();
        this.numerosAleatorios(); //llama 
        
        
        //matriz de imagenes
        matriz = new JLabel[6][7];
        for (int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(80+(j*123),8+(i*110),125,130);//tamaño
                matriz[i][j].setIcon(new ImageIcon("imagenes/personajes/"+matAux[i][j]+".png"));
                matriz[i][j].setVisible(true);
                panelJuego.add(matriz[i][j],0);
                
                
                
            }
        }
       // tiempo
      
       min = 0;
       seg = 0;
       tiempo = new Timer(1000, (ActionEvent e) -> {
           seg++;
           if(seg == 60){
               min++;
               seg = 0;
           }
           cronometro.setText("Tiempo:"+min+":"+seg);
        });
       
       //tiempo carta
       contadorcarta =0;
       tiempocarta = new Timer(1000, (ActionEvent e) -> {
           contadorcarta++;
        });
       
       tiempocarta.start();
       tiempocarta.stop();
       contadorcarta =0;
       ban = 0;
       ban1=0;
       
        //cartas(click)
        contador=0;
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter (){
                    @Override
                    public void mousePressed( MouseEvent e){
                        for (int k = 0; k < 6; k++) {
                            for (int l = 0; l < 7; l++) {
                                if(e.getSource()== matriz[k][l]){ //posision de la carta
                                    //System.out.println(k+" "+l);//posision
                                    if(matAux[k][l]==0 && contador!=2){ //==0 maxaux--numeros
                                        
                                        matAux[k][l] = mat [k][l]; //numero aleatorio = imagen
                                        matriz[k][l].setIcon(new ImageIcon("imagenes/personajes/"+matAux[k][l]+".png")); //voltear la carta
                                        contador++; 
                                        
                                        actnum=mat[k][l];
                                        actx=k;
                                        acty=l;
                                        
                                        if(contador==1){ 
                                            num = mat[k][l];
                                            antx= k;
                                            anty= l;
                                            
                                        }
                                        
                                       
                                        
                                        tiempocarta1 = new Timer (500, (ActionEvent e1) -> {
                                            if(contador==2 && ban1==0){
                                                tiempocarta.restart(); //empezar
                                                ban1=1;
                                            }
                                            
                                            
                                            if(contador ==2 && contadorcarta == 1 ){
                                                tiempocarta.stop(); //detiene tiempo
                                                contadorcarta=0;
                                                if(matAux[actx][acty] == matAux[antx][anty]){
                                                    
                                                    matAux[actx][acty]=-1;
                                                    matAux[antx][anty]=-1;
                                                    matriz[actx][acty].setIcon(new ImageIcon("imagenes/personajes/"+matAux[actx][acty]+".png"));
                                                    matriz[antx][anty].setIcon(new ImageIcon("imagenes/personajes/"+matAux[antx][anty]+".png"));
                                                    contador=0;
                                                    
                                                    //ganar
                                                    int acum=0;
                                                    for (int m = 0; m < 6; m++) {
                                                        for (int n = 0; n < 7; n++) {
                                                            if(matAux[m][n]==-1)
                                                                acum++;
                                                            
                                                        }
                                                    }
                                                    if(acum==42){
                                                        tiempo.stop();
                                                        JOptionPane.showMessageDialog(ventana,"GANASTE con un tiempo: " +min+":"+seg);
                                                        for (int m = 0; m < 6; m++) {
                                                            for (int n = 0; n < 7; n++) {
                                                                mat[m][n]=0;
                                                                matAux[m][n]=0;
                                                                matriz[m][n].setIcon(new ImageIcon("imagenes/personajes/"+matAux[m][n]+".png"));
                                                                
                                                            }
                                                            
                                                        }
                                                        
                                                        numerosAleatorios();
                                                        min=0;
                                                        seg=0;
                                                        tiempo.restart();
                                                        
                                                        JOptionPane.showMessageDialog(null, "Para salir cierre la ventana");
                                                        
                                                        int opcion= JOptionPane.showConfirmDialog(null, "¿Desea comenzar el juego con otro jugador?");
                                                        
                                                        if(opcion==JOptionPane.YES_OPTION){
                                                            
                                                            jugador = JOptionPane.showInputDialog(ventana, "Ingrese su nombre para poder jugar", "");
                                                            while(jugador == null || jugador.compareTo("Nombre")==0 || jugador.compareTo(" ")==0){
                                                                jugador = JOptionPane.showInputDialog(ventana,"Debes de ingresar tu nombre", "Escribe aqui");
                                                            }
                                                           
                                                            nombreJugador.setText(" "+jugador);
                                                            tiempo.start();
                                                            panelPresentacion.setVisible(false);
                                                            ventana.add(panelJuego);
                                                            panelJuego.setVisible(true);
                                                            
                                                            numerosAleatorios();
                                                            min=0;
                                                            seg=0;
                                                            tiempo.restart();
                                                        }
                                                        if(opcion==JOptionPane.NO_OPTION){
                                                            
                                                            numerosAleatorios();
                                                            min=0;
                                                            seg=0;
                                                            tiempo.restart();
                                                            
                                                            
                                                        }
                                                        if(opcion==JOptionPane.CANCEL_OPTION){
                                                            numerosAleatorios();
                                                            min=0;
                                                            seg=0;
                                                            tiempo.restart();
                                                            
                                                            
                                                        }
                                                        
                                                    }
                                                    
                                                    
                                                    
                                                }
                                                
                                                
                                                for (int m = 0; m < 6; m++) {
                                                    for (int n = 0; n < 7; n++) {
                                                        if(matAux[m][n]!=0 && matAux[m][n]!=-1){
                                                            
                                                            matAux[m][n] = 0;
                                                            matriz[m][n].setIcon(new ImageIcon("imagenes/personajes/"+matAux[m][n]+".png"));
                                                            System.out.println("HOLA");
                                                            contador=0;
                                                        }
                                                        
                                                    }
                                                    
                                                }
                                                tiempocarta1.stop();
                                                ban1=0;
                                            }
                                        });
                                        if(ban ==0){
                                            tiempocarta1.start();
                                            ban=0;
                                        }
                                        if(contador == 2){
                                            tiempocarta1.restart();
                                        }
                                    }
                                  
                                }
                                
                            }
                            
                        }
                    }
    
                });
                
            }
            
        }
       
       
       
       
       
       
       
       
       
       
       
        
        //boton jugar
        BotonJugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println("presione el boton");
                jugador = JOptionPane.showInputDialog(ventana, "Ingrese su nombre para poder jugar", "");
                while(jugador == null || jugador.compareTo("Nombre")==0 || jugador.compareTo(" ")==0){
                    
                    jugador = JOptionPane.showInputDialog(ventana,"Debes de ingresar tu nombre", "Escribe aqui");
                }
                nombreJugador.setText(" "+jugador);
                tiempo.start();
                panelPresentacion.setVisible(false);
                ventana.add(panelJuego);
                panelJuego.setVisible(true);
                
                
            }
           
        });
        
        
        
        
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
    }
    
    public void numerosAleatorios(){
        
        int acumulador=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mat[i][j]=0;//matriz sea igual a 0
                matAux[i][j]=0;
                
            }
            
        }
        
        
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mat[i][j]= aleatorio.nextInt(21)+1; //numeros aleatorios del 1 al 21
                do{
                   acumulador = 0;
                   for (int k = 0; k < 6; k++) { 
                       for (int l = 0; l < 7; l++) {
                            if(mat[i][j]== mat[k][l]){ //tiene numeros iguales 
                                acumulador +=1; //acumula el mismo numero (pares)
                            }
                        }
                    }
                    if(acumulador==3){ 
                        mat[i][j] = aleatorio.nextInt(21)+1; //vuelve a sacar un numero
                    }
                 } while (acumulador == 3); //si vuelve a sacar el numero haga esto
             }
        
         }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                
                System.out.println(mat[i][j]+"  "); //mostrar matriz
            }
            System.out.println(""); //manda
        }
   
     }
    

    
    
    
    
}
