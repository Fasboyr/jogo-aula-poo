package br.ifpr.jogo.modelo;

import java.awt.Color;
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
    public static final int DELAY = 9;
    public static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    public static final int QTDE_DE_INIMIGOS = 50;
    public static final int QTDE_DE_NUVENS = 30;

    protected Image fundo;

    protected Timer timer;
    protected boolean timerAtivo;
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

    public void desenhaMenuInicial(Graphics2D graficos){
        int altura_instrucao = 200;
        int tamanho = 30;
        String textoInstrucao = "------Comandos------" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoInstrucao, 620, altura_instrucao);

        String textoInstru1 = "Use WASD ou as Setas para controlar o personagem" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoInstru1, 450, altura_instrucao + 30);

        String textoInstru2 = "Pressione Espaço para atirar" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoInstru2, 600, altura_instrucao + 60);

        String textoInstru3 = "Pressione Q para usar o SuperTiro" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoInstru3, 600, altura_instrucao + 90);

        String textoInstru4 = "Pressione ESC para pausar o jogo" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoInstru4, 600, altura_instrucao + 120);

        int altura_init = 400;
        String textoTitulo = "----Escolha uma Opção----" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoTitulo, 620, altura_init);

        String textoOp1 = "1- Iniciar Novo Jogo" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoOp1, 650, altura_init + 30);

        String textoOp2 = "2 - Carregar Último Ponto Salvo " ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, tamanho));
        graficos.setColor(Color.yellow);
        graficos.drawString(textoOp2, 600, altura_init + 60);
    }

    public void desenhaMenuPausa(Graphics2D graficos){
       String textoOp1 = "Pressione P para Salvar o Jogo" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(Color.GREEN);
        graficos.drawString(textoOp1, 650, 750);

        String textoOp2 = "Presionar L para Carregar do Ultimo Ponto Salvo" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(Color.GREEN);
        graficos.drawString(textoOp2, 600, 780);
    }

    public void desenhaMenuGameOver(Graphics2D graficos){
       String textoOp1 = "1- Iniciar Novo Jogo" ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(Color.GREEN);
        graficos.drawString(textoOp1, 650, 750);

        String textoOp2 = "2 - Carregar Último Ponto Salvo " ;
        graficos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
        graficos.setColor(Color.GREEN);
        graficos.drawString(textoOp2, 600, 780);
    }

    public abstract void inicializaInimigos();

    public abstract void verificarColisoes();

    public abstract void inicializaElementosGraficosAdicionais();

    public abstract void inicializarJogo();

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


    public boolean isTimerAtivo() {
        return this.timerAtivo;
    }

    public boolean getTimerAtivo() {
        return this.timerAtivo;
    }

    public void setTimerAtivo(boolean timerAtivo) {
        this.timerAtivo = timerAtivo;
    }

    public FaseEntidade getFaseEntidade() {
        return this.faseEntidade;
    }

    public void setFaseEntidade(FaseEntidade faseEntidade) {
        this.faseEntidade = faseEntidade;
    }

}
