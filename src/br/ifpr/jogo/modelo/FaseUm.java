package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.security.Principal;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class FaseUm extends Fase {
    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int VIDA_POR_COLISAO = 1;

    public FaseUm() { 
        super(); 
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon("recursos\\background.jpg");
        fundo = carregando.getImage();
        personagem = new Personagem(VELOCIDADE_DE_DESLOCAMENTO);
        personagem.carregar(); 

        this.inicializaElementosGraficosAdicionais();

        this.inicializaInimigos();
        timer = new Timer(DELAY, this); 
        timer.start(); 
    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        super.estrelas = new ArrayList<Estrela>();
        for (int i = 0; i < QTDE_DE_ESTRELAS; i++) {
            int x = (int) (Math.random() * 1600);
            int y = (int) (Math.random() * 900);
            Estrela estrela = new Estrela(x, y);
            super.estrelas.add(estrela);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            for (Estrela estrela : estrelas) {
                graficos.drawImage(estrela.getImagem(), estrela.getPosicaoEmX(),estrela.getPosicaoEmY(), this);
            }
        
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
            ArrayList<Tiro> tiros = personagem.getTiros();
            for (Tiro tiro : tiros) { 
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
            for (SuperTiro superTiro : superTiros) {
                superTiro.carregar();
                graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), this);
            }


            
            for (Inimigo inimigo : inimigos) {
                inimigo.carregar();
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            super.desenhaPontuacao(graficos);
            super.desenhaVida(graficos);
        }   else {
            ImageIcon fimDeJogo = new ImageIcon("recursos\\gameover.png");
            graficos.drawImage(fimDeJogo.getImage(), 350, 100, null);
        }
        g.dispose();
        
    }

    @Override
    public void verficarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();
        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                int vidaAtual = this.personagem.getVida();
                if (vidaAtual == 0) {
                    this.personagem.setEhVisivel(false);
                    inimigo.setEhVisivel(false);
                    emJogo = false;
                }
                this.personagem.setVida(vidaAtual - VIDA_POR_COLISAO);
                inimigo.setEhVisivel(false);

            }
            
            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
            ArrayList<SuperTiro> superTiros = this.personagem.getSuperTiros();
            for (int s = 0; s < superTiros.size(); s++) {
                SuperTiro superTiro = superTiros.get(s);
                Rectangle formaSuper =superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuper)) {
                    inimigo.setEhVisivel(false);
                }
                if (formaInimigo.intersects(formaSuper)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                }
            }
            
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        if(e.getKeyCode() == KeyEvent.VK_Q)
                personagem.superAtirar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        for (Estrela estrela : this.estrelas) {
            estrela.atualizar();
        }
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (int i = 0; i < tiros.size(); i++) {

            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA || !tiro.getEhVisivel())
                tiros.remove(tiro);
            else
                tiro.atualizar();
        }

        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < superTiros.size(); i++) {
            // Obter o objeto tiro da posicao i do ArrayList
            SuperTiro superTiro = superTiros.get(i);
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (superTiro.getPosicaoEmX() > LARGURA_DA_JANELA || !superTiro.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_JANELA)
                superTiros.remove(superTiro);
            else
        
                superTiro.atualizar();
        }



        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = this.inimigos.get(i);
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                inimigos.remove(inimigo);
            else
                inimigo.atualizar();
        }
        this.verficarColisoes();
        repaint();
    }

   
}
