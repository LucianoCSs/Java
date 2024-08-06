import jplay.GameImage;
import jplay.Keyboard;
import jogo.Cenario1;
import jplay.Window;

public class App {
    public static void main(String[] args) throws Exception {
        Window janela = new Window(800, 600);
        GameImage plano = new GameImage("/home/luciano/Documentos/Java/JogoExemplo/jogoexemplo/src/recursos/sprites/tela_titulo.png");
        Keyboard teclado = janela.getKeyboard();

        while (true) {
            plano.draw();
            janela.update();

            if (teclado.keyDown(Keyboard.ENTER_KEY)) { // se a tecla ENTER for pressionada aparece a msg
                new Cenario1(janela);
            }
        }
    }
}
