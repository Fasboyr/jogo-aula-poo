package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import br.ifpr.jogo.dao.impl.FaseEntidadeDaoImpl;
import br.ifpr.jogo.entidade.FaseEntidade;
import br.ifpr.jogo.principal.principal;

@Entity
public class FaseUm extends Fase {
    private static final int PONTOS_POR_INIMIGO = 10;
    private static final int VIDA_POR_COLISAO = 1;

    public FaseUm() {
        super();
        this.faseEntidade = new FaseEntidade();
        this.faseEntidade.setEmJogo(false);
        ImageIcon carregando = new ImageIcon(getClass().getResource("/background.jpg"));

        fundo = carregando.getImage();
        this.faseEntidade.setPersonagem(new Personagem(VELOCIDADE_DE_DESLOCAMENTO));
        
        timer = new Timer(DELAY, this);
        timerAtivo = false;
    }




    @Override
    public void inicializaInimigos() {
        ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) (Math.random() * 8000 + principal.LARGURA_JANELA);
            int y = (int) (Math.random() * 650 + 30);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
        this.faseEntidade.setInimigos(inimigos);
    }

    @Override
    public void inicializaElementosGraficosAdicionais() {
        ArrayList<Nuvem> nuvens = new ArrayList<Nuvem>();
        for (int i = 0; i < QTDE_DE_NUVENS; i++) {
            int x = (int) (Math.random() * principal.LARGURA_JANELA);
            int y = (int) (Math.random() * principal.ALTURA_JANELA);
            Nuvem nuvem = new Nuvem(x, y);
            nuvens.add(nuvem);
        }
        this.faseEntidade.setNuvens(nuvens);
    }

    @Override
    public void inicializarJogo(){
        this.faseEntidade.setEmJogo(true);
        this.faseEntidade.setPersonagem(new Personagem(VELOCIDADE_DE_DESLOCAMENTO));
        this.faseEntidade.getPersonagem().carregar();
        this.inicializaElementosGraficosAdicionais();
        this.inicializaInimigos();
        timer.start();
        timerAtivo = true;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        if (this.faseEntidade.isEmJogo()) {
            for (Nuvem nuvem : this.faseEntidade.getNuvens()) {
                graficos.drawImage(nuvem.getImagem(), nuvem.getPosicaoEmX(), nuvem.getPosicaoEmY(), this);
            }

            graficos.drawImage(this.faseEntidade.getPersonagem().getImagem(), this.faseEntidade.getPersonagem().getPosicaoEmX(), this.faseEntidade.getPersonagem().getPosicaoEmY(), this);
            List<Tiro> tiros = this.faseEntidade.getPersonagem().getTiros();
            for (Tiro tiro : tiros) {
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            List<SuperTiro> superTiros = this.faseEntidade.getPersonagem().getSuperTiros();
            for (SuperTiro superTiro : superTiros) {
                superTiro.carregar();
                graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), this);
            }

            for (Inimigo inimigo : this.faseEntidade.getInimigos()) {
                inimigo.carregar();
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
            super.desenhaPontuacao(graficos);
            super.desenhaVida(graficos);

            if(!this.isTimerAtivo()){
                super.desenhaMenuPausa(graficos);
                timer.stop();
            }

        } else if(this.faseEntidade.getPersonagem().getVida() <= 0 && !this.faseEntidade.isEmJogo()){
            ImageIcon fimDeJogo = new ImageIcon(getClass().getResource("/gameover.png"));
            graficos.drawImage(fimDeJogo.getImage(), 350, 100, null);
            super.desenhaMenuGameOver(graficos);
        }else if(!this.faseEntidade.isEmJogo()){
            super.desenhaMenuInicial(graficos);
            timer.stop();
        }else if(!this.isTimerAtivo()){
            super.desenhaMenuPausa(graficos);
            timer.stop();
        }

        g.dispose();

    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = this.faseEntidade.getPersonagem().getRectangle();
        for (int i = 0; i < this.faseEntidade.getInimigos().size(); i++) {
            Inimigo inimigo = this.faseEntidade.getInimigos().get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            int vidaAtual = this.faseEntidade.getPersonagem().getVida();

            if (formaInimigo.intersects(formaPersonagem)) {
                this.faseEntidade.getPersonagem().setVida(vidaAtual - VIDA_POR_COLISAO);
                inimigo.setEhVisivel(false);
                if (vidaAtual == 0) {
                    this.faseEntidade.getPersonagem().setEhVisivel(false);
                    inimigo.setEhVisivel(false);
                    this.faseEntidade.setEmJogo(false);
                }

            }

            List<Tiro> tiros = this.faseEntidade.getPersonagem().getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
                if (formaInimigo.intersects(formaTiro)) {
                    int pontuacaoAtual = this.faseEntidade.getPersonagem().getPontuacao();
                    this.faseEntidade.getPersonagem().setPontuacao(pontuacaoAtual + PONTOS_POR_INIMIGO);
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
            List<SuperTiro> superTiros = this.faseEntidade.getPersonagem().getSuperTiros();
            for (int s = 0; s < superTiros.size(); s++) {
                SuperTiro superTiro = superTiros.get(s);
                Rectangle formaSuper = superTiro.getRectangle();
                if (formaInimigo.intersects(formaSuper)) {
                    inimigo.setEhVisivel(false);
                }
                if (formaInimigo.intersects(formaSuper)) {
                    int pontuacaoAtual = this.faseEntidade.getPersonagem().getPontuacao();
                    this.faseEntidade.getPersonagem().setPontuacao(pontuacaoAtual + (PONTOS_POR_INIMIGO / 2));
                    inimigo.setEhVisivel(false);
                }
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.faseEntidade.getPersonagem().atirar();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            this.faseEntidade.getPersonagem().superAtirar();
        } else {
            this.faseEntidade.getPersonagem().mover(e);
        }


        if(e.getKeyCode() == KeyEvent.VK_P && !this.isTimerAtivo()){
            faseSalvar();
        }else if(e.getKeyCode() == KeyEvent.VK_L && !this.isTimerAtivo() ){
            faseCarregar();
        }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(!this.isTimerAtivo()){
                timer.start();
                this.timerAtivo = true;
            }else{
                this.timerAtivo = false;              
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_1 && !this.faseEntidade.isEmJogo()){
            this.inicializarJogo();
        }else if(e.getKeyCode() == KeyEvent.VK_2 && !this.faseEntidade.isEmJogo() ){
            faseCarregar();
        }
    }
  
    private void faseSalvar() {
        FaseEntidadeDaoImpl dao = new FaseEntidadeDaoImpl();
        boolean dadosExistem = dao.verificarDadosExistem();
        if(dadosExistem){
            dao.atualizar(faseEntidade);
        }
        else{
            dao.inserir(faseEntidade);
        }
        
    }
    private void faseCarregar() {
        FaseEntidadeDaoImpl dao = new FaseEntidadeDaoImpl();
        FaseEntidade instanciaCarregada = dao.buscarPorId(1);
        
        if (instanciaCarregada != null) {
            this.faseEntidade = new FaseEntidade();
            
            this.faseEntidade = instanciaCarregada;
            this.faseEntidade.getPersonagem().carregar();
            this.faseEntidade.getPersonagem().setDeslocamentoEmX(0);
            this.faseEntidade.getPersonagem().setDeslocamentoEmY(0);

            for (Inimigo inimigo : this.faseEntidade.getInimigos()) {
                inimigo.carregar();
            }
              for (Nuvem nuvem : this.faseEntidade.getNuvens()) {
                nuvem.carregar();
            }
            for (Tiro tiro : this.faseEntidade.getPersonagem().getTiros()) {
                tiro.carregar();
            }
            for (SuperTiro superTiro : this.faseEntidade.getPersonagem().getSuperTiros()) {
                superTiro.carregar();
            }
            timer.start();
            timerAtivo = true;

        }
    }

    

    @Override
    public void keyReleased(KeyEvent e) {
        this.faseEntidade.getPersonagem().parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.faseEntidade.getPersonagem().atualizar();
        for (Nuvem nuvem : this.faseEntidade.getNuvens()) {
            nuvem.atualizar();
        }
        List<Tiro> tiros = this.faseEntidade.getPersonagem().getTiros();
        for (int i = 0; i < tiros.size(); i++) {

            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > principal.LARGURA_JANELA || !tiro.getEhVisivel())
                tiros.remove(tiro);
            else
                tiro.atualizar();
        }

        List<SuperTiro> superTiros = this.faseEntidade.getPersonagem().getSuperTiros();
        for (int i = 0; i < superTiros.size(); i++) {
            SuperTiro superTiro = superTiros.get(i);
            if (superTiro.getPosicaoEmX() > principal.LARGURA_JANELA || !superTiro.getEhVisivel())
                superTiros.remove(superTiro);
            else
                superTiro.atualizar();
        }

        for (int i = 0; i < this.faseEntidade.getInimigos().size(); i++) {
            Inimigo inimigo = this.faseEntidade.getInimigos().get(i);
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                this.faseEntidade.getInimigos().remove(inimigo);
            else
                inimigo.atualizar();
        }
        this.verificarColisoes();
        this.faseEntidade.getPersonagem().verificarBorda();
        repaint();
    }

}
