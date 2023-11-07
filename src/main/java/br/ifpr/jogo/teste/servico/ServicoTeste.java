package br.ifpr.jogo.teste.servico;


import br.ifpr.jogo.modelo.Inimigo;
import br.ifpr.jogo.modelo.Jogador;
import br.ifpr.jogo.servico.InimigoServico;
import br.ifpr.jogo.servico.JogadorServico;


public class ServicoTeste {
    public static void main(String[] args) {
        Jogador local = new Jogador("Joãozinho 2");
        JogadorServico.inserir(local);

        Inimigo inimigo = new Inimigo(100, 200);

        InimigoServico.inserir(inimigo);
        
    }
}
