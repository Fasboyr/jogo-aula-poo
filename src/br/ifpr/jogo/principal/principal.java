package br.ifpr.jogo.principal;

import javax.swing.JFrame;

import br.ifpr.jogo.modelo.Fase;

public class principal extends JFrame {
    public principal(){
        Fase fase  = new Fase();
        super.add(fase);

        setVisible(true);
        setSize(1600, 900);
        this.setTitle("Meu Jogo");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);;


    }
    public static void main(String[] args) {
        principal principal = new principal();
        
    }
    
}
