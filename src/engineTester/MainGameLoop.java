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

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        /*TERRRAIN TEXTURE*/
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\podlaga1.jpg"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\mud.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\grassFlowers.png"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\path.png"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\emptyBlendMap.png"));
        /*TERRRAIN TEXTURE*/


        ModelData data = OBJFileLoader.loadOBJ("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\piramida.obj");
        RawModel model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\piramida_textura.png")));
        ModelTexture texture1 = staticModel.getTexture();
        texture1.setShineDamper(10);
        texture1.setReflectivity(10);

        Terrain terrain = new Terrain(0,0,loader,texturePack,blendMap,"C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\heightmap.png");
        //Terrain terrain2 = new Terrain(-1,-1,loader,texturePack,blendMap,"C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\heightmap.png");

        List<Entity> entities = new ArrayList<Entity>();

        TerrainTextureLocation TexturesCoords = new TerrainTextureLocation();

        for(TerrainTextureLocation coord : TexturesCoords.getOgrajaCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(data.getVertices(),staticModel, new Vector3f(x,y,z),0,0,0,3));
        }

        data = OBJFileLoader.loadOBJ("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\kocka.obj");
        model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\kocka_textura.png")));


        for(TerrainTextureLocation coord : TexturesCoords.getKvadratiCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(data.getVertices(),staticModel, new Vector3f(x,y,z),0,0,0,3));
        }

        data = OBJFileLoader.loadOBJ("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\valj.obj");
        model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\valj_textura.png")));


        for(TerrainTextureLocation coord : TexturesCoords.getValjCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(data.getVertices(),staticModel, new Vector3f(x,y,z),0,0,0,3));
        }

        data = OBJFileLoader.loadOBJ("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\krog.obj");
        model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\krog_textura.png")));


        for(TerrainTextureLocation coord : TexturesCoords.getKrogiCoords()) {
            float x = coord.x;
            float z = coord.y;
            float y = terrain.getHeightOfTerrain(x,z);

            entities.add(new Entity(data.getVertices(),staticModel, new Vector3f(x,y,z),0,0,0,3));
        }


        /*
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\grassModel.obj",loader),
                new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\grassTexture.png")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);
        Entity grass1 = new Entity(grass, new Vector3f(20,0,-50),0,0,0,2);
        */

        Light light = new Light(new Vector3f(0,20000,0),new Vector3f(1,1,1));

        MasterRenderer renderer = new MasterRenderer();


        ModelData bunnyModelData = OBJFileLoader.loadOBJ("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\avto.obj");
        RawModel Bunnymodel = loader.loadToVAO(bunnyModelData.getVertices(),bunnyModelData.getTextureCoords(),bunnyModelData.getNormals(),bunnyModelData.getIndices());
        TexturedModel PlayerModel = new TexturedModel(Bunnymodel,new ModelTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\avto_textura.png")));

        Player player = new Player(entities,bunnyModelData.getVertices(),PlayerModel, new Vector3f(40,20,210), 0, 0, 0, 1);

        Camera camera = new Camera(player);

        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("C:\\Users\\smrki\\Documents\\rg\\NewTutorial\\res\\gui.png"), new Vector2f(0.2f,0.2f), new Vector2f(-0.7f,-0.6f));
        guis.add(gui);
        GuiRenderer guiRenderer = new GuiRenderer(loader);


        while(!Display.isCloseRequested()){
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