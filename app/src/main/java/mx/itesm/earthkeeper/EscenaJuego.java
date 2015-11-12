package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/11/2015.
 */

import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Toast;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.input.touch.controller.MultiTouchController;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;
import java.util.Random;

//RECUPERACION
public class EscenaJuego extends EscenaBase {

    private boolean juegoCorriendo = true;
    private Camera mCamera;
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion Galaxias;
    private ITextureRegion Tierra;
    private ITextureRegion Marco;
    private ITextureRegion GalaxiaVerde;
    private ITextureRegion GalaxiaRojo;
    private ITextureRegion GalaxiaAmarillo;
    private ITextureRegion GalaxiaAzul;
    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteGalaxias;
    private Sprite spriteTierra;
    private Sprite spriteMarco;
    private Sprite spriteGalaxiaVerde;
    private Sprite spriteGalaxiaAzul;
    private Sprite spriteGalaxiaAmarillo;
    private Sprite spriteGalaxiaRojo;

    private int VIDA=3;



    //	Enemigos
    private ArrayList<Enemigos> listaEnemigos;
    private ArrayList<ITextureRegion> listaP;
    private ITextureRegion regionVerde;
    private ITextureRegion regionRojo;
    private ITextureRegion regionAmarillo;
    private ITextureRegion regionAzul;
    // Fin del juego
    private ITextureRegion regionFin;
    //	Tiempo	para	generar	enemigos
    private float tiempoEnemigos =	0;				//	Contador
    private float	LIMITE_TIEMPO	=	2.5f;		//	Cada	2.5s	se	crea






    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoC.jpg");
        Galaxias = cargarImagen("Galaxias_Juntas.png");
        Tierra = cargarImagen("Tierra_NUEVA.png");
        Marco = cargarImagen("PantallaMarcoFINAL.png");
        regionVerde = cargarImagen("Circular_Verde.png");
        regionAzul = cargarImagen("Circular_Azul.png");
        regionAmarillo = cargarImagen("Circular_Amarilo.png");
        regionRojo = cargarImagen("Circular_Rojo.png");
        regionFin = cargarImagen("Consola_GAMEOVER.png");
        GalaxiaVerde = cargarImagen("PruebaVerde.jpg");
        GalaxiaRojo = cargarImagen("PruebaRojo.jpg");
        GalaxiaAmarillo = cargarImagen("PruebaAmarillo.jpg");
        GalaxiaAzul = cargarImagen("PruebaAzul.jpg");

    }



   /* @Override

    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        listaP.add(regionAmarillo);
        listaP.add(regionVerde);
        listaP.add(regionAzul);
        listaP.add(regionRojo);
        for (int i  = 0; i<=3; i++){

            Sprite spriteEnemigo = cargarSprite(ControlJuego.ANCHO_CAMARA +  listaP.get(i).getWidth(),
                    (float) (Math.random() * ControlJuego.ALTO_CAMARA -  listaP.get(i).getHeight()) +
                            listaP.get(i).getHeight(),  listaP.get(i));
            Enemigos nuevoEnemigo = new Enemigos(spriteEnemigo);
            listaEnemigos.add(nuevoEnemigo);
           this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
            registerTouchArea(nuevoEnemigo.getSpriteEnemigo());
            setTouchAreaBindingOnActionDownEnabled(true);

            attachChild(nuevoEnemigo.getSpriteEnemigo());
        }


        return true;
    }/*

  /*  public boolean onTouchEvent(MotionEvent event) {
        int myEventAction = event.getAction();

        float X = event.getX();
        float Y = event.getY();

        switch (myEventAction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                spriteTierra.setPosition(X, Y);
                break;}
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }*/


private void crearEnemigos() {
        listaP.add(regionAmarillo);
        listaP.add(regionVerde);
        listaP.add(regionAzul);
        listaP.add(regionRojo);
        //System.out.println(listaP.size());
        int z=0;
        //int random = (int )(Math.random() * 4 + 1);
        //for (int x = 700; x <= 1200; x += 100) {
            //int random = (int )(Math.random() * 4 + 1);
            Random ran = new Random();
             z = ran.nextInt(3 - 0 + 1) + 0;
            //for (int y = 100; y <= 700; y += 100) {
                Sprite nave = cargarSprite(-100, 100, listaP.get(z));
                attachChild(nave);
                Enemigos enemigo = new Enemigos(nave);
                listaEnemigos.add(enemigo);
          //  }
       // }
       /* for (int x = 100; x <= -100; x += -100) {
            for (int y = -100; y <= -100; y += -100) {
                Sprite nave = cargarSprite(x, y, regionEnemigo);
                attachChild(nave);
                Enemigos enemigo = new Enemigos(nave);
                listaEnemigos.add(enemigo);
            }
        }*/
    }



    @Override
    public void crearEscena() {
        listaEnemigos = new ArrayList<>();
        listaP = new ArrayList<>();


        spriteGalaxiaVerde = cargarSprite(0,0,GalaxiaVerde);
        spriteGalaxiaAzul = cargarSprite(ControlJuego.ANCHO_CAMARA,0,GalaxiaAzul);
        spriteGalaxiaRojo = cargarSprite(0,ControlJuego.ALTO_CAMARA,GalaxiaRojo);
        spriteGalaxiaAmarillo = cargarSprite(ControlJuego.ANCHO_CAMARA,ControlJuego.ALTO_CAMARA,GalaxiaAmarillo);



        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        spriteGalaxias = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, Galaxias);
        spriteTierra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Tierra);
        spriteMarco = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Marco);
        attachChild(spriteFondo);
        attachChild(spriteGalaxias);
        attachChild(spriteTierra);
        attachChild(spriteMarco);
        attachChild(spriteGalaxiaVerde);
        attachChild(spriteGalaxiaRojo);
        attachChild(spriteGalaxiaAmarillo);
        attachChild(spriteGalaxiaAzul);
        crearEnemigos();




        registerTouchArea(spriteTierra);
        setTouchAreaBindingOnActionDownEnabled(true);

    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaJuego();
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_JUEGO;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        regionFin.getTexture().unload();
        Galaxias.getTexture().unload();
        Tierra.getTexture().unload();
        Marco.getTexture().unload();
        regionVerde.getTexture().unload();
        regionRojo.getTexture().unload();
        regionAmarillo.getTexture().unload();
        regionAzul.getTexture().unload();
        regionFondo = null;
        Galaxias = null;
        Tierra = null;
        Marco = null;
        regionVerde = null;
        regionRojo = null;
        regionAmarillo = null;
        regionAzul = null;
        regionFin=null;
    }





    @Override
    protected void onManagedUpdate(float pSecondsElapsed)	{
        int vida=3;
        listaP.add(regionAmarillo);
        listaP.add(regionVerde);
        listaP.add(regionAzul);
        listaP.add(regionRojo);
        Random ran = new Random();






        //float posX = Tierra.getTextureX();
        //float posY = Tierra.getTextureY();
        super.onManagedUpdate(pSecondsElapsed);
        tiempoEnemigos	+=	pSecondsElapsed;		//	Acumular	tiempo
        if	(tiempoEnemigos>LIMITE_TIEMPO)	{	//	Se	cumplió	el	tiempo
            tiempoEnemigos	=	0;
            int z = ran.nextInt(3 - 0 + 1) + 0;
            int r1 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
            int r2 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
            int r3 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
            int r4 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
            int var = ran.nextInt(4 - 1 + 1) + 1;
            /*final Sprite spriteEnemigo	=	cargarSprite(ControlJuego.ANCHO_CAMARA+listaP.get(z).getWidth(),
                    (float)(Math.random()*ControlJuego.ALTO_CAMARA-listaP.get(z).getHeight())	+
                            listaP.get(z).getHeight(),listaP.get(z)) ;*/
            Sprite spriteEnemigo = new Sprite(ControlJuego.ANCHO_CAMARA+listaP.get(z).getWidth(),
                    (float)(Math.random()*ControlJuego.ALTO_CAMARA-listaP.get(z).getHeight())  +
                            listaP.get(z).getHeight(),listaP.get(z),actividadJuego.getVertexBufferObjectManager()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

                    return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                }
            };









            Enemigos nuevoEnemigo = new Enemigos(spriteEnemigo);

            registerTouchArea(nuevoEnemigo.getSpriteEnemigo());
            setTouchAreaBindingOnActionDownEnabled(true);
            attachChild(nuevoEnemigo.getSpriteEnemigo());
            //nuevoEnemigo.mover(0,10);
            listaEnemigos.add(nuevoEnemigo);	//	Lo	AGREGA	a	la	escena
            boolean mGrabbed = false;











       	//	Lo	AGREGA	a	la	lista











            // Agrega al enemigo a posiciones aleatorias generadas
            if(var==1) {
               // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                nuevoEnemigo.getSpriteEnemigo().setPosition(0,r1);
            } else if (var==2) {
                nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
            } else if (var==3) {
                nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
            } else if (var==4) {
                nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
            }
        }






        //	Actualizar	cada	uno	de	los	enemigos	y	ver	si	alguno	ya	salió	de	la	pantalla
        for	(int	i=listaEnemigos.size()-1;	i>=0;	i--) {
            Enemigos enemigo = listaEnemigos.get(i);
            //enemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA,100);
            // MoveModifier mod1=new MoveModifier(50,0,ControlJuego.ALTO_CAMARA/2,50,ControlJuego.ANCHO_CAMARA/2);
            //enemigo.getSpriteEnemigo().registerEntityModifier(mod1);
            enemigo.mover(spriteTierra.getX(), spriteTierra.getY());

            if (enemigo.getSpriteEnemigo().getX() < -enemigo.getSpriteEnemigo().getWidth()) {
                detachChild(enemigo.getSpriteEnemigo());        //	Lo	ELIMINA	de	la	escena
                listaEnemigos.remove(enemigo);                                                                    //	Lo	ELIMINA	de	la	lista
            }
            //	Revisa	el	choque	del	personaje	con	el	enemigo

            if (enemigo.getSpriteEnemigo().getTextureRegion()==regionVerde) {
                if (spriteGalaxiaVerde.collidesWith(enemigo.getSpriteEnemigo())){
                    detachChild(enemigo.getSpriteEnemigo());
                    listaEnemigos.remove(enemigo);
                }
            }

            if (enemigo.getSpriteEnemigo().getTextureRegion()==regionAzul) {
                if (spriteGalaxiaAzul.collidesWith(enemigo.getSpriteEnemigo())){
                    detachChild(enemigo.getSpriteEnemigo());
                    listaEnemigos.remove(enemigo);
                }
            }

            if (enemigo.getSpriteEnemigo().getTextureRegion()==regionAmarillo) {
                if (spriteGalaxiaAmarillo.collidesWith(enemigo.getSpriteEnemigo())){
                    detachChild(enemigo.getSpriteEnemigo());
                    listaEnemigos.remove(enemigo);
                }
            }

            if (enemigo.getSpriteEnemigo().getTextureRegion()==regionRojo) {
                if (spriteGalaxiaRojo.collidesWith(enemigo.getSpriteEnemigo())){
                    detachChild(enemigo.getSpriteEnemigo());
                    listaEnemigos.remove(enemigo);
                }
            }



            if (spriteTierra.collidesWith(enemigo.getSpriteEnemigo())) {
                detachChild(enemigo.getSpriteEnemigo());
                //listaEnemigos.remove(enemigo);
                vida--;
                if (vida == 0) {
                    juegoCorriendo = false;
                    Sprite spriteFin = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                            regionFin, actividadJuego.getVertexBufferObjectManager()) {
                        @Override
                        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                            if (pSceneTouchEvent.isActionUp()) {
                                onBackKeyPressed();
                            }
                            return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                        }
                    };
                    registerTouchArea(spriteFin);
                    attachChild(spriteFin);
                }
            }


        }
    }


   // @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0, 0, mCamera.getWidth(), mCamera.getHeight());

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(mCamera.getWidth(), mCamera.getHeight()), this.mCamera);
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);



        return engineOptions;
    }










}
