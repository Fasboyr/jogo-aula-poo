package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements KeyListener, ActionListener {
    private Image  fundo;
    private Personagem personagem;
    private Timer timer;
    private ArrayList<Inimigo> inimigos;

    private static final int LARGURA_DA_JANELA = 1600;
    private static final int DELAY = 5;
    private static final int VELOCIDADE_DE_DESLOCAMENTO = 7;
    private static final int QTDE_DE_INIMIGOS = 40;

    
    

    public Fase(){
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        ImageIcon carregando = new ImageIcon("recursos\\background.jpg");
        this.fundo = carregando.getImage();
        
        this.personagem = new Personagem(VELOCIDADE_DE_DESLOCAMENTO);
        this.personagem.carregar();

        this.inicializaInimigos();

        this.addKeyListener(this);
        this.timer = new Timer(DELAY, this);
        this.timer.start();

    }

     public void inicializaInimigos(){
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

     public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();

        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Tiro tiro : tiros) {
            // Carregando imagem do objeto tiro pelo método carregar.
            tiro.carregar();
            // Desenhar o tiro na nossa tela.
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }

        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Inimigo inimigo : inimigos) {
            // Carregando imagem do objeto inimigo pelo método carregar.
            inimigo.carregar();
            // Desenhar o inimigo na nossa tela.
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
        }


        g.dispose();
    }


    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
        personagem.atirar();
    else
        personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.personagem.parar(e);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < tiros.size(); i++) {
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (tiros.get(i).getPosicaoEmX() > LARGURA_DA_JANELA)
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_TELA)
                tiros.remove(i);
            else
                // Atualizar a posição do tiro.
                tiros.get(i).atualizar();
        }

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < inimigos.size(); i++) {
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (inimigos.get(i).getPosicaoEmX() < 0)
                // Remover da lista se estiver fora do campo de visão (0)
                inimigos.remove(i);
            else
                // Atualizar a posição do inimigo.
                inimigos.get(i).atualizar();
        }

        repaint();
    }
}
