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
import org.andengine.engine.handler.IDrawHandler;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.input.touch.controller.MultiTouchController;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.IDisposable;

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
    private ITextureRegion Vida_uno;
    private ITextureRegion Vida_dos;
    private ITextureRegion Vida_tres;
    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteGalaxias;
    private Sprite spriteTierra;
    private Sprite spriteMarco;
    private Sprite spriteGalaxiaVerde;
    private Sprite spriteGalaxiaAzul;
    private Sprite spriteGalaxiaAmarillo;
    private Sprite spriteGalaxiaRojo;
    private Sprite spriteVida1;
    private Sprite spriteVida2;
    private Sprite spriteVida3;
    //VIDA
    private int ANCHO_VIDA;
    private int vida;
    private Rectangle rectVida;
    private Rectangle rectVidaActual;

    private int VIDA=3;
    //Puntos
    private Text txtPuntos;
    private int puntos;
    private IFont fontMonster;

    //	Enemigos
    private ArrayList<Enemigos> listaEnemigos;
    private ArrayList<ITextureRegion> listaP;
    private ITextureRegion regionVerde;
    private ITextureRegion regionRojo;
    private ITextureRegion regionAmarillo;
    private ITextureRegion regionAzul;

    //Naves Triangulares
    private ITextureRegion regionTVerde;
    private ITextureRegion regionTRojo;
    private ITextureRegion regionTAmarillo;
    private ITextureRegion regionTAzul;

    // Fin del juego
    private ITextureRegion regionFin;
    //	Tiempo	para	generar	enemigos
    private float tiempoEnemigos =	0;				//	Contador
    private float	LIMITE_TIEMPO	=	1.0f;		//	Cada	2.5s	se	crea
    //	Sprite	animado

   // private AnimatedSprite spriteFondo;		//	Sprite
    //private TiledTextureRegion regionFondo;		//	Región

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;
    private ITextureRegion regionBtnPlay;
    private ITextureRegion regionBtnMainMenu;
    //Escudo
    private ITextureRegion regionEscudo;
    private ITextureRegion regionEscudoActivado;
    private Sprite spriteEscudoActivado;
    private Sprite spriteEscudo;





    public interface IEntity extends IDrawHandler, IUpdateHandler, IDisposable {
        public boolean isIgnoreUpdate();
        public void setIgnoreUpdate(final boolean pIgnoreUpdate);
    }



    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Fondo_Consola.jpg");
        //regionFondo	=	cargarImagenMosaico("Fondo_Anim.png", 5306, 800,	1,	4);
        GalaxiaVerde = cargarImagen("Circulo_Amarillo.png");
        GalaxiaRojo = cargarImagen("Circulo_Amarillo.png");
        GalaxiaAmarillo = cargarImagen("Circulo_Amarillo.png");
        GalaxiaAzul = cargarImagen("Circulo_Amarillo.png");
        Galaxias = cargarImagen("Galaxias_Juntas.png");
        Tierra = cargarImagen("Tierra_NUEVA.png");
        Marco = cargarImagen("MARCO-FINAL-REDONDO.png");
        regionVerde = cargarImagen("Circular_Verde.png");
        regionAzul = cargarImagen("Circular_Azul.png");
        regionAmarillo = cargarImagen("Circular_Amarilo.png");
        regionRojo = cargarImagen("Circular_Rojo.png");
        regionFin = cargarImagen("EARTHKEEPER-MASTER/Pantalla Gameover/Pantalla_GAMEOVER.jpg");
        Vida_uno = cargarImagen("VIDA_1.png");
        Vida_dos = cargarImagen("VIDA_2.png");
        Vida_tres = cargarImagen("VIDA_3.png");
        regionTVerde = cargarImagen("Naves/Triangular_Verde.png");
        regionTAzul = cargarImagen("Naves/Triangular_Azul.png");
        regionTAmarillo = cargarImagen("Naves/Triangular_Amarillo.png");
        regionTRojo = cargarImagen("Naves/Triangular_Rojo.png");


        fontMonster = cargarFont("OCR.ttf", 32, 0xFFFFFFFF, "Puntos: 0123456789");
        // Pausa
        regionBtnPausa = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Boton-Pausa.png");
        regionPausa = cargarImagen("EARTHKEEPER-MASTER/Pantalla Pausa/Pantalla_Pausa.jpg");
        regionBtnPlay = cargarImagen("EARTHKEEPER-MASTER/Pantalla Principal/PLAY_Boton.png");
        regionBtnMainMenu = cargarImagen ("EARTHKEEPER-MASTER/Pantalla Pausa/Main-Menu_Boton.png");
        //Escudo
        regionEscudo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/barra escudo 0.png");
        regionEscudoActivado = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo solo.png");


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

    private void agregarVida() {
        // Vida
        ANCHO_VIDA = ControlJuego.ANCHO_CAMARA/2;
        //vida = 100; // %
        // Fondo
        rectVida = new Rectangle(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA-50,
                ANCHO_VIDA+10,ANCHO_VIDA/8,actividadJuego.getVertexBufferObjectManager());
        rectVida.setColor(0, 0, 0, 0.4f);
        attachChild(rectVida);
        // Nivel
        rectVidaActual = new Rectangle(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA-50,
                ANCHO_VIDA,ANCHO_VIDA/8,actividadJuego.getVertexBufferObjectManager());
        rectVidaActual.setColor(0, 1, 0);

        attachChild(rectVidaActual);
    }

    private void agregarTextoPuntos() {
        txtPuntos = new Text(ControlJuego.ANCHO_CAMARA-440,ControlJuego.ALTO_CAMARA-70,
                fontMonster," 0          ",actividadJuego.getVertexBufferObjectManager());
        attachChild(txtPuntos);
    }

    private void actualizarPuntos() {
        txtPuntos.setText(" " + puntos);
    }



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

        spriteGalaxias = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, Galaxias);
        spriteGalaxiaVerde = cargarSprite(0,0,GalaxiaVerde);
        spriteGalaxiaAzul = cargarSprite(ControlJuego.ANCHO_CAMARA,0,GalaxiaAzul);
        spriteGalaxiaRojo = cargarSprite(0,ControlJuego.ALTO_CAMARA, GalaxiaRojo);
        spriteGalaxiaAmarillo = cargarSprite(ControlJuego.ANCHO_CAMARA,ControlJuego.ALTO_CAMARA,GalaxiaAmarillo);

        /*
        spriteFondo	=	new	AnimatedSprite(ControlJuego.ANCHO_CAMARA/2,
                ControlJuego.ALTO_CAMARA/2,	regionFondo,
                actividadJuego.getVertexBufferObjectManager());
        spriteFondo.animate(200);
*/

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        //Fondo animado
        /*AutoParallaxBackground fondoAnimado	=	new	AutoParallaxBackground(1,1,1,5);
        fondoAnimado.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-3, spriteFondo));
        setBackground(fondoAnimado);*/
        //

        spriteTierra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Tierra);
        spriteMarco = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Marco);
        spriteVida1 = cargarSprite(ControlJuego.ANCHO_CAMARA-838,ControlJuego.ALTO_CAMARA-71,Vida_uno);
        spriteVida2 = cargarSprite(ControlJuego.ANCHO_CAMARA-793,ControlJuego.ALTO_CAMARA-75,Vida_dos);
        spriteVida3 = cargarSprite(ControlJuego.ANCHO_CAMARA-750,ControlJuego.ALTO_CAMARA-79,Vida_tres);
        //spriteEscudo =cargarSprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,regionEscudo);

        attachChild(spriteFondo);
        attachChild(spriteGalaxiaVerde);
        attachChild(spriteGalaxiaRojo);
        attachChild(spriteGalaxiaAmarillo);
        attachChild(spriteGalaxiaAzul);
        attachChild(spriteGalaxias);
        attachChild(spriteTierra);
        attachChild(spriteMarco);
        //attachChild(spriteEscudo);
        attachChild(spriteVida1);
        attachChild(spriteVida2);
        attachChild(spriteVida3);

        crearEnemigos();
        // Crear elementos de pausa
        agregarPausa();
        //Escudo
        agregarEscudo();
        //registerTouchArea(spriteTierra);
        setTouchAreaBindingOnActionDownEnabled(true);
        agregarTextoPuntos();

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

    private void agregarEscudo(){
        //spriteEscudo =cargarSprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,regionEscudo);
       // final Sprite Escudo = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
              //  regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()) {
                    Sprite Escudo = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                            regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
                    attachChild(Escudo);
                    Escudo.setAlpha(0.4f);
                    return true;
                }
                return true;  //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }

        };
        attachChild(btnEscudo);
        registerTouchArea(btnEscudo);
    }

    private void agregarPausa() {
        // Crea el botón de PAUSA y lo agrega a la escena
        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth()/2+800, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        //btnPausa.setAlpha(0.4f);
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        //ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                regionPausa);
        escenaPausa.attachChild(fondoPausa);

        // Crea el botón de PAUSA y lo agrega a la escena

        Sprite btnMain = new Sprite((ControlJuego.ANCHO_CAMARA /2)+45,(ControlJuego.ALTO_CAMARA/2)+320,
                regionBtnMainMenu, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                // setIgnoreUpdate(false);

                if (pSceneTouchEvent.isActionDown()) {
                    //juegoCorriendo=true;
                    //admEscenas.crearEscenaMenu();
                    onBackKeyPressed();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(btnMain);
        escenaPausa.registerTouchArea(btnMain);
        escenaPausa.setBackgroundEnabled(false);


        Sprite btnContinuar = new Sprite(ControlJuego.ANCHO_CAMARA /2,(ControlJuego.ALTO_CAMARA/2)-320,
                regionBtnPlay, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
               // setIgnoreUpdate(false);

                if (pSceneTouchEvent.isActionDown()) {
                    //juegoCorriendo=true;
                    pausarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        //btnContinuar.setAlpha(0.4f);
        escenaPausa.attachChild(btnContinuar);
        escenaPausa.registerTouchArea(btnContinuar);

        escenaPausa.setBackgroundEnabled(false);
    }


    private void pausarJuego() {
        if (juegoCorriendo) {
            setIgnoreUpdate(true);
            setChildScene(escenaPausa, false, true, true);
            juegoCorriendo = false;
            //getChildScene().setIgnoreUpdate(true);
        } else {
            clearChildScene();
            setIgnoreUpdate(false);
            juegoCorriendo = true;

        }
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
        Vida_uno.getTexture().unload();
        Vida_dos.getTexture().unload();
        Vida_tres.getTexture().unload();
        regionBtnPausa.getTexture().unload();
        regionBtnPausa = null;
        Vida_uno = null;
        Vida_dos = null;
        Vida_tres = null;
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

        Random ran = new Random();






        //float posX = Tierra.getTextureX();
        //float posY = Tierra.getTextureY();
        super.onManagedUpdate(pSecondsElapsed);
        actualizarPuntos();
        int cont=0;
        int z=0;
        if( puntos<100) {

            listaP.add(regionAmarillo);
            listaP.add(regionVerde);
            listaP.add(regionAzul);
            listaP.add(regionRojo);
        } else if (puntos>=100)
        {

            listaP.add(regionTAmarillo);
            listaP.add(regionTVerde);
            listaP.add(regionTAzul);
            listaP.add(regionTRojo);
        }

       // if (juegoCorriendo = true) {

            tiempoEnemigos += pSecondsElapsed;        //	Acumular	tiempo

            if (tiempoEnemigos > LIMITE_TIEMPO) {    //	Se	cumplió	el	tiempo
                if(pSecondsElapsed%5==0){
                    LIMITE_TIEMPO-=0.5f;
                }
                tiempoEnemigos = 0;

                    z = ran.nextInt(listaP.size() - 0 + 1) + 0;

              //  int r1 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
                float r1 = GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2) + ran.nextFloat()*(ControlJuego.ALTO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                float r2 = GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2)+ ran.nextFloat()*(ControlJuego.ALTO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                float r3 = GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2)+ ran.nextFloat()*(ControlJuego.ANCHO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth() + regionAzul.getWidth()/2);
                float r4 = GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2) + ran.nextFloat()*(ControlJuego.ANCHO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                //int r2 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
                //int r3 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
                //int r4 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
                int var = ran.nextInt(4 - 1 + 1) + 1;
            /*final Sprite spriteEnemigo	=	cargarSprite(ControlJuego.ANCHO_CAMARA+listaP.get(z).getWidth(),
                    (float)(Math.random()*ControlJuego.ALTO_CAMARA-listaP.get(z).getHeight())	+
                            listaP.get(z).getHeight(),listaP.get(z)) ;*/

                //  Sprite spriteEnemigo = new Sprite(ControlJuego.ANCHO_CAMARA + listaP.get(z).getWidth(),
                  //      (float) (Math.random() * ControlJuego.ALTO_CAMARA - listaP.get(z).getHeight()) +
                 //               listaP.get(z).getHeight(), listaP.get(z), actividadJuego.getVertexBufferObjectManager())



                Sprite spriteEnemigo = new Sprite(-100,
                       1000, listaP.get(z), actividadJuego.getVertexBufferObjectManager()) {

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
                listaEnemigos.add(nuevoEnemigo);    //	Lo	AGREGA	a	la	escena
                boolean mGrabbed = false;


                //	Lo	AGREGA	a	la	lista

                // Agrega al enemigo a posiciones aleatorias generadas

                if (puntos<100) {
                    if (var == 1) {
                        // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                    } else if (var == 2) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                    } else if (var == 3) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    } else if (var == 4) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                    }


                } else if (puntos >100) {
                    if (var == 1) {
                        // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                    } else if (var == 2) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                    } else if (var == 3) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                    } else if (var == 4) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    }





                }
            }

       // if ( juegoCorriendo==true) {
            for (int i = listaEnemigos.size() - 1; i >= 0; i--) {
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

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionVerde || enemigo.getSpriteEnemigo().getTextureRegion() == regionTVerde  ) {
                    if (spriteGalaxiaVerde.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    } else if (spriteGalaxiaAzul.collidesWith(enemigo.getSpriteEnemigo()) || spriteGalaxiaRojo.collidesWith(enemigo.getSpriteEnemigo())
                    ||spriteGalaxiaAmarillo.collidesWith(enemigo.getSpriteEnemigo())){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionAzul || enemigo.getSpriteEnemigo().getTextureRegion() == regionTAzul) {
                    if (spriteGalaxiaAzul.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaRojo)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAmarillo)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionAmarillo || enemigo.getSpriteEnemigo().getTextureRegion() == regionTAmarillo) {
                    if (spriteGalaxiaAmarillo.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAzul) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaRojo)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionRojo || enemigo.getSpriteEnemigo().getTextureRegion() == regionTRojo) {
                    if (spriteGalaxiaRojo.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAzul) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAmarillo)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    }
                }


                if (spriteTierra.collidesWith(enemigo.getSpriteEnemigo())) {
                    detachChild(enemigo.getSpriteEnemigo());
                    //listaEnemigos.remove(enemigo);
                    vida--;
                    if (vida == 2) {
                        detachChild(spriteVida3);
                    } else if (vida == 1) {
                        detachChild(spriteVida2);
                    } else if (vida == 0) {
                        detachChild(spriteVida1);
                        juegoCorriendo = false;
                        Sprite spriteFin = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                                regionFin, actividadJuego.getVertexBufferObjectManager()) {
                            @Override
                            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                                if (pSceneTouchEvent.isActionUp()) {
                                    onBackKeyPressed();
                                }
                                return true; // return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
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
