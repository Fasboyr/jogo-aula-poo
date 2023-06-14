package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements KeyListener, ActionListener {
    private Image  fundo;
    private Personagem personagem;
    private Timer timer;

    private static final int DELAY = 5;
    private static final int VELOCIDADE_DE_DESLOCAMENTO = 3;
    

    public Fase(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("recursos\\espaço.jpg");
        this.fundo = carregando.getImage();
        
        this.personagem = new Personagem(VELOCIDADE_DE_DESLOCAMENTO);
        this.personagem.carregar();
        this.addKeyListener(this);

        this.timer = new Timer(DELAY, this);
        this.timer.start();



    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.personagem.atualizar();
        repaint();
    }
}
