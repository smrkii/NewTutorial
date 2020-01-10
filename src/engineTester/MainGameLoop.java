package engineTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;

import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.*;
import terrains.Terrain;
import textures.*;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MainGameLoop {

    private final static String path="C:\\res\\";

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        List<Entity> entities = new ArrayList<Entity>();
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        List<GuiTexture> guis = new ArrayList<GuiTexture>();

        //Iz slike pridobi lokacije objektov v svetu.
        TerrainTextureLocation ObjetcsCoords = new TerrainTextureLocation(path+"proga1.png");

        //TERRAIN
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture(path+"podlaga1.jpg"));
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture(path+"emptyBlendMap.png"));
        Terrain terrain = new Terrain(0,0,loader,texturePack,blendMap,path+"heightmap.png");

        //AVTO
        ModelData AvtoObjekt = OBJFileLoader.loadOBJ(path+"avto.obj");
        RawModel AvtoModel = loader.loadToVAO(AvtoObjekt.getVertices(),AvtoObjekt.getTextureCoords(),AvtoObjekt.getNormals(),AvtoObjekt.getIndices());
        TexturedModel AvtoTextureModel = new TexturedModel(AvtoModel,new ModelTexture(loader.loadTexture(path+"avto_textura.png")));

        //PIRAMIDA
        ModelData piramidaObjekt = OBJFileLoader.loadOBJ(path+"piramida.obj");
        RawModel PiramidaModel = loader.loadToVAO(piramidaObjekt.getVertices(),piramidaObjekt.getTextureCoords(),piramidaObjekt.getNormals(),piramidaObjekt.getIndices());
        TexturedModel PiramidaTextureModel = new TexturedModel(PiramidaModel,new ModelTexture(loader.loadTexture(path+"piramida_textura.png")));
        ModelTexture texture1 = PiramidaTextureModel.getTexture();
        texture1.setShineDamper(10);
        texture1.setReflectivity(10);

        //KOCKA
        ModelData KockaObjekt = OBJFileLoader.loadOBJ(path+"kocka.obj");
        RawModel KockaModel = loader.loadToVAO(KockaObjekt.getVertices(),KockaObjekt.getTextureCoords(),KockaObjekt.getNormals(),KockaObjekt.getIndices());
        TexturedModel KockaTextureModel = new TexturedModel(KockaModel,new ModelTexture(loader.loadTexture(path+"kocka_textura.png")));

        //VALJ
        ModelData ValjObjekt = OBJFileLoader.loadOBJ(path+"valj.obj");
        RawModel ValjModel = loader.loadToVAO(ValjObjekt.getVertices(),ValjObjekt.getTextureCoords(),ValjObjekt.getNormals(),ValjObjekt.getIndices());
        TexturedModel ValjTextureModel = new TexturedModel(ValjModel,new ModelTexture(loader.loadTexture(path+"valj_textura.png")));

        //KROG
        ModelData KrogObjekt = OBJFileLoader.loadOBJ(path+"krog.obj");
        RawModel KrogModel = loader.loadToVAO(KrogObjekt.getVertices(),KrogObjekt.getTextureCoords(),KrogObjekt.getNormals(),KrogObjekt.getIndices());
        TexturedModel KrogTextureModel = new TexturedModel(KrogModel,new ModelTexture(loader.loadTexture(path+"krog_textura.png")));


        //Postavi piramide v svet
        for(TerrainTextureLocation coord : ObjetcsCoords.getPiramidaCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(piramidaObjekt.getVertices(),PiramidaTextureModel, new Vector3f(x,y,z),0,0,0,3));
        }

        //Postavi kocke v svet
        for(TerrainTextureLocation coord : ObjetcsCoords.getKvadratiCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(KockaObjekt.getVertices(),KockaTextureModel, new Vector3f(x,y,z),0,0,0,3));
        }

        //Postavi valje v svet
        for(TerrainTextureLocation coord : ObjetcsCoords.getValjCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(ValjObjekt.getVertices(),ValjTextureModel, new Vector3f(x,y,z),0,0,0,3));
        }

        //Postavi kroge v svet
        for(TerrainTextureLocation coord : ObjetcsCoords.getKrogiCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(KrogObjekt.getVertices(),KrogTextureModel, new Vector3f(x,y,z),0,0,0,3));
        }


        Light light = new Light(new Vector3f(0,20000,0),new Vector3f(1,1,1));

        Player player = new Player(entities,AvtoObjekt.getVertices(),AvtoTextureModel, new Vector3f(40,20,210), 0, 0, 0, 1);

        Camera camera = new Camera(player);


        GuiTexture gui = new GuiTexture(loader.loadTexture(path+"gui.png"), new Vector2f(0.2f,0.2f), new Vector2f(-0.7f,-0.6f));
        guis.add(gui);

        gui = new GuiTexture(loader.loadTexture(path+"move_mouse.png"), new Vector2f(0.2f,0.2f), new Vector2f(0.7f,-0.6f));
        guis.add(gui);

        gui = new GuiTexture(loader.loadTexture(path+"healthBar.png"), new Vector2f(0.74f,0.1f), new Vector2f(0.34f,0.8f));
        guis.add(gui);

        gui = new GuiTexture(loader.loadTexture(path+"healthBarFrame.png"), new Vector2f(0.7f,0.1f), new Vector2f(0.3f,0.8f));
        guis.add(gui);

        while(!Display.isCloseRequested()){
            if(player.isHealthCollision()){
                guis.get(2).inscreaseScaleX(-0.1f);
                guis.get(2).inscreasePositionX(-0.1f);

                if(player.isDead()) {
                    player = new Player(entities,AvtoObjekt.getVertices(),AvtoTextureModel, new Vector3f(40,20,210), 0, 0, 0, 1);
                    camera = new Camera(player);

                    guis.get(2).setScaleX(0.74f);
                    guis.get(2).setPositionX(0.34f);
                }
            }

            camera.move();
            player.move(terrain);
            renderer.processEntity(player);

            //renderer.processEntity(grass1);

            renderer.processTerrain(terrain);
            //renderer.processTerrain(terrain2);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);

            guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }

        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }

}