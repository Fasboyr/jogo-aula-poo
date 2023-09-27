package br.ifpr.jogo.modelo;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Fase extends JPanel implements KeyListener, ActionListener{
    protected Image  fundo;
    protected Personagem personagem;
    protected Timer timer;
    protected ArrayList<Inimigo> inimigos;
    protected boolean emJogo = true;
    protected ArrayList<Nuvem> nuvens;

    public static final int DELAY = 5;
    public static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    public static final int QTDE_DE_INIMIGOS = 50;
    public static final int QTDE_DE_NUVENS = 30;


    public Fase() {
        setFocusable(true); 
        setDoubleBuffered(true); 
        addKeyListener(this); 
    }

    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + personagem.getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

    public void desenhaVida(Graphics2D graficos) {
        String textoVida = "VIDA: " + personagem.getVida();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoVida, 20, 50);
    }

    public abstract void inicializaInimigos();

    public abstract void verficarColisoes();

    public abstract void inicializaElementosGraficosAdicionais();

   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
