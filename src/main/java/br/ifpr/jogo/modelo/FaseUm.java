package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import br.ifpr.jogo.principal.principal;

@Entity
public class FaseUm extends Fase {
    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int VIDA_POR_COLISAO = 1;

    public FaseUm() {
        super();
        this.emJogo = true;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/background.jpg"));

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
            int x = (int) (Math.random() * 8000 + principal.LARGURA_JANELA);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        super.nuvens = new ArrayList<Nuvem>();
        for (int i = 0; i < QTDE_DE_NUVENS; i++) {
            int x = (int) (Math.random() * principal.LARGURA_JANELA);
            int y = (int) (Math.random() * principal.ALTURA_JANELA);
            Nuvem nuvem = new Nuvem(x, y);
            super.nuvens.add(nuvem);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            for (Nuvem nuvem : nuvens) {
                graficos.drawImage(nuvem.getImagem(), nuvem.getPosicaoEmX(), nuvem.getPosicaoEmY(), this);
            }

            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
            List<Tiro> tiros = personagem.getTiros();
            for (Tiro tiro : tiros) {
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            List<SuperTiro> superTiros = personagem.getSuperTiros();
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

        } else {
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/gameover.png"));
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
            int vidaAtual = this.personagem.getVida();

            if (formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setVida(vidaAtual - VIDA_POR_COLISAO);
                inimigo.setEhVisivel(false);
                if (vidaAtual == 0) {
                    this.personagem.setEhVisivel(false);
                    inimigo.setEhVisivel(false);
                    emJogo = false;
                }

            }

            List<Tiro> tiros = this.personagem.getTiros();
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
            List<SuperTiro> superTiros = this.personagem.getSuperTiros();
            for (int s = 0; s < superTiros.size(); s++) {
                SuperTiro superTiro = superTiros.get(s);
                Rectangle formaSuper = superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuper)) {
                    inimigo.setEhVisivel(false);
                }
                if (formaInimigo.intersects(formaSuper)) {
                    int pontuacaoAtual = this.personagem.getPontuacao();
                    this.personagem.setPontuacao(pontuacaoAtual + (PONTOS_POR_INIMIGO / 2));
                    inimigo.setEhVisivel(false);
                }
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            personagem.atirar();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            personagem.superAtirar();
        } else {
            personagem.mover(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        for (Nuvem nuvem : this.nuvens) {
            nuvem.atualizar();
        }
        List<Tiro> tiros = personagem.getTiros();
        for (int i = 0; i < tiros.size(); i++) {

            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > principal.LARGURA_JANELA || !tiro.getEhVisivel())
                tiros.remove(tiro);
            else
                tiro.atualizar();
        }

        List<SuperTiro> superTiros = personagem.getSuperTiros();
        for (int i = 0; i < superTiros.size(); i++) {
            SuperTiro superTiro = superTiros.get(i);
            if (superTiro.getPosicaoEmX() > principal.LARGURA_JANELA || !superTiro.getEhVisivel())
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
        personagem.verificarBorda();
        repaint();
    }

}
