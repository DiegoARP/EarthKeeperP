package mx.itesm.earthkeeper;


import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;


/**
 * Created by cooldarp on 9/16/2015.
 */
public class EscenaNueva extends EscenaBase{

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
