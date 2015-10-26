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

    /*public void mover(int dx, int dy) {

        sprite.setPosition( sprite.getX()+dx, sprite.getY()+dy );
    }*/

    public void mover(float targetX, float targetY)
    {
        float xPos = sprite.getX();
        float yPos = sprite.getY();
        if (xPos > targetX)
        {
            xPos--;
        }
        else if (xPos < targetX)
        {
            xPos++;
        }
        if (yPos > targetY)
        {
            yPos--;
        }
        else if (yPos < targetY)
        {
            yPos++;
        }
        sprite.setPosition( xPos, yPos );
    }



}


