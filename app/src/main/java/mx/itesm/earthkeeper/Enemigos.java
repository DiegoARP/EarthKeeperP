package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/22/2015.
 */

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;


public class Enemigos {


    private Sprite sprite;

    public Enemigos(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSpriteEnemigo() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void mover(int dx, int dy) {
        sprite.setPosition( sprite.getX()+dx, sprite.getY()+dy );
    }



}


