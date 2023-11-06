package br.ifpr.jogo.teste.servico;


import br.ifpr.jogo.modelo.Jogador;
import br.ifpr.jogo.servico.JogadorServico;


public class ServicoTeste {
    public static void main(String[] args) {
        Jogador local = new Jogador("Joãozinho 2");
        JogadorServico.inserir(local);
    }
}
