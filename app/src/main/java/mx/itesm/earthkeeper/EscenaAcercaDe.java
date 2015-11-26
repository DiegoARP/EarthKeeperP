package mx.itesm.earthkeeper;

import android.util.Log;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaAcercaDe extends EscenaBase
{
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    private Sprite spriteFondo;

    private ITextureRegion regionBtnB;
    private ITextureRegion regionBtnK;
    private ITextureRegion regionBtnL;
    private ITextureRegion regionBtnT;

    //Animados
    private AnimatedSprite spriteB;
    private TiledTextureRegion regionAnimadoB;
    private AnimatedSprite spriteL;
    private TiledTextureRegion regionAnimadoL;
    //Sprites no animados
    private Sprite spriteBnnet;
    private ITextureRegion Bnnet;
    private Sprite spriteLumaka;
    private ITextureRegion Lumaka;
    private Sprite spriteKlaam;
    private ITextureRegion Klaam;
    private Sprite spriteThee;
    private ITextureRegion Thee;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/Pantalla_ConsolaVACIA.jpg");

//       "EARTHKEEPER-MASTER/Pantalla Galeria/botones/b-neetNom.png"
        regionBtnB = cargarImagen( "EARTHKEEPER-MASTER/Pantalla Galeria/botones/b-neetNom.png");
        regionBtnK = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/botones/KlaamNom.png");
        regionBtnL = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/botones/lumakaNom.png");
        regionBtnT = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/botones/theeNom.png");
        //Mosaicos
        regionAnimadoB = cargarImagenMosaico("EARTHKEEPER-MASTER/Pantalla Galeria/b neet sprite (2).png", 2313, 435, 1, 3);
        regionAnimadoL = cargarImagenMosaico("EARTHKEEPER-MASTER/Pantalla Galeria/lumakaSprite.png", 3840, 800, 1, 3);
        //
        Bnnet= cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/b-neet1.png");
        Lumaka = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/lumaka1.png");
        Klaam = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/klaam1.png");
        Thee = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/thee1.png");

    }




    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        //Animados
        spriteB	=	new	AnimatedSprite((ControlJuego.ANCHO_CAMARA/2)-500,
                (ControlJuego.ALTO_CAMARA/2),regionAnimadoB,
                actividadJuego.getVertexBufferObjectManager());
        spriteB.animate(200);

        //Imagenes centrales
        spriteBnnet = cargarSprite((ControlJuego.ANCHO_CAMARA/2)-500,( ControlJuego.ALTO_CAMARA/2)-10, Bnnet);
        spriteLumaka = cargarSprite(125, -260,Lumaka);
        spriteKlaam = cargarSprite(500,200,Klaam);
        spriteThee = cargarSprite(-550,150,Thee);


        //Botones
        Sprite spriteBA = new Sprite(ControlJuego.ANCHO_CAMARA/2,
                (ControlJuego.ALTO_CAMARA/2)-320, regionBtnB, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown() && !spriteBnnet.hasParent()) {


                    //detachChild(spriteL);

                    attachChild(spriteBnnet);
                   // return true;
                    //attachChild(spriteB);
                    //Log.i("xx", "1_attach spriteBnnet");
                } else if (pSceneTouchEvent.isActionUp() && spriteBnnet.hasParent() ){
                    detachChild(spriteBnnet);
                }


              return true; //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteFondo);
        attachChild(spriteBA);
        setTouchAreaBindingOnActionDownEnabled(true);
        registerTouchArea(spriteBA);



        spriteL	=	new	AnimatedSprite(ControlJuego.ANCHO_CAMARA/2,
                (ControlJuego.ALTO_CAMARA/2),	regionAnimadoL,
                actividadJuego.getVertexBufferObjectManager());
        spriteL.animate(200);

        Sprite spriteLA = new Sprite((ControlJuego.ANCHO_CAMARA/2)+45,
                (ControlJuego.ALTO_CAMARA/2)+320, regionBtnL, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown() && !spriteLumaka.hasParent() ) {
                    attachChild(spriteLumaka);
                } else if (pSceneTouchEvent.isActionUp() && spriteLumaka.hasParent() ){
                    detachChild(spriteLumaka);
                }


                return true; //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteLA);
        registerTouchArea(spriteLA);
        //-525,-30
        Sprite spriteKA = new Sprite((ControlJuego.ANCHO_CAMARA/2)-525,
                (ControlJuego.ALTO_CAMARA/2)-10, regionBtnK, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown() && !spriteKlaam.hasParent()) {


                    //detachChild(spriteL);

                    attachChild(spriteKlaam);
                    // return true;
                    //attachChild(spriteB);
                    //Log.i("xx", "1_attach spriteBnnet");
                } else if (pSceneTouchEvent.isActionUp() && spriteKlaam.hasParent()){
                    detachChild(spriteKlaam);
                }


                return true; //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteKA);
        registerTouchArea(spriteKA);
        Sprite spriteTA = new Sprite((ControlJuego.ANCHO_CAMARA/2)+520,
                (ControlJuego.ALTO_CAMARA/2)-30, regionBtnT, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown() && !spriteThee.hasParent()) {


                    //detachChild(spriteL);

                    attachChild(spriteThee);
                    // return true;
                    //attachChild(spriteB);
                    //Log.i("xx", "1_attach spriteBnnet");
                } else if (pSceneTouchEvent.isActionUp() && spriteThee.hasParent()){
                    detachChild(spriteThee);
                }


                return true; //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteTA);
        registerTouchArea(spriteTA);


    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaAcercaDe();
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_ACERCA_DE;
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
