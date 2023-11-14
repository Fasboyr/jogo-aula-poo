package br.ifpr.jogo.modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.entidade.FaseEntidade;

//import org.hibernate.annotations.CascadeType;

public abstract class Fase extends JPanel implements KeyListener, ActionListener {
    public static final int DELAY = 5;
    public static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    public static final int QTDE_DE_INIMIGOS = 50;
    public static final int QTDE_DE_NUVENS = 30;

    protected Image fundo;

    protected Timer timer;
    protected FaseEntidade faseEntidade;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(this);
    }

    public void desenhaPontuacao(Graphics2D graficos) {
        String textoPontuacao = "PONTOS: " + faseEntidade.getPersonagem().getPontuacao();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoPontuacao, 20, 25);
    }

    public void desenhaVida(Graphics2D graficos) {
        String textoVida = "VIDA: " + faseEntidade.getPersonagem().getVida();
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(new java.awt.Color(255, 255, 255));
        graficos.drawString(textoVida, 20, 50);
    }

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

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

    public Image getFundo() {
        return this.fundo;
    }

    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
