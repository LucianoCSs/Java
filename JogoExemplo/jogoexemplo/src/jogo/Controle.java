package jogo;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {

    public boolean colisao(GameObject obj, TileInfo tile){
        if ((tile.id >= 4) && obj.collided(tile)) {
            return true;
        }
        return false;
    }
}