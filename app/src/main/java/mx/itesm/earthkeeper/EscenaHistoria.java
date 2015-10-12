package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/4/2015.
 */

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

public class EscenaHistoria extends EscenaBase {


    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    private Sprite spriteFondo;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("creditosNuevo.jpg");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
    }



   //* public void animHistoria(){
       // Animation animashion = AnimationUtils.loadAnimation(null, R.anim.animationfile);
        //regionFondo.setText("Some text view.");
        //regionFondo.setAnimation(spriteFondo);
    //}


    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNueva();
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_NUEVA;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        regionFondo = null;
    }









}
