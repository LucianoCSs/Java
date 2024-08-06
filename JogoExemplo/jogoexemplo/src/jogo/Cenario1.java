package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public class Cenario1 {
   private Window janela;
   private Scene cena;
   private Jogador jogador;
   private Keyboard teclado;

public Cenario1(Window window) {
    this.janela = window;
    this.cena = new Scene();
    cena.loadFromFile("/home/luciano/Documentos/Java/JogoExemplo/jogoexemplo/src/recursos/scn/Cenario1.scn");
    this.jogador = new Jogador(540, 350);
    this.teclado = janela.getKeyboard();
    run();
}

private void run(){
    while (true) {
        // cena.draw();
        jogador.mover(janela, teclado); // controle
        jogador.caminho(cena);
        cena.moveScene(jogador); // segue aonde o personagem for no cenario
        jogador.x += cena.getXOffset(); // movimentação mais fluida
        jogador.y += cena.getYOffset();
        jogador.draw();
        janela.update();
        janela.delay(1);
    }
}
   
   
}
