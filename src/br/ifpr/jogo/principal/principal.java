package br.ifpr.jogo.principal;

import javax.swing.JFrame;

public class principal extends JFrame {
    public principal(){
        setVisible(true);
        setSize(500, 500);
        setTitle("Meu Jogo");
        setLocationRelativeTo(null);


    }
    public static void main(String[] args) {
        principal principal = new principal();
        
    }
    
}
