package br.ifpr.jogo.principal;


import javax.swing.JFrame;

import br.ifpr.jogo.modelo.FaseUm;

public class principal extends JFrame {
    public static final int LARGURA_JANELA = 1600;
    public static final int ALTURA_JANELA = 900;

    public principal(){
        FaseUm fase  = new FaseUm();
        super.add(fase);

        setVisible(true);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        this.setTitle("Meu Jogo");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);;


    }
    public static void main(String[] args) {
        principal principal = new principal();
        
    }
    
}
