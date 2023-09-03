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
    protected ArrayList<Estrela> estrelas;

    public static final int LARGURA_DA_JANELA = 1600;
    public static final int DELAY = 5;
    public static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    public static final int QTDE_DE_INIMIGOS = 40;
    public static final int QTDE_DE_ESTRELAS = 10;


    public Fase() {
        setFocusable(true); // + define o foco inicial do jogo
        setDoubleBuffered(true); // + Otimização computacional
        addKeyListener(this); // + Definindo que a própria classe irá controlar os eventos do teclado
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

