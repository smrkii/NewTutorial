package renderEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TerrainTextureLocation {

    public int x=0;
    public  int y=0;

    ArrayList<TerrainTextureLocation> KvadratiCoords = new ArrayList<TerrainTextureLocation>();
    ArrayList<TerrainTextureLocation> KrogiCoords = new ArrayList<TerrainTextureLocation>();
    ArrayList<TerrainTextureLocation> ValjCoords = new ArrayList<TerrainTextureLocation>();
    ArrayList<TerrainTextureLocation> PiramidaCoords = new ArrayList<TerrainTextureLocation>();

    public ArrayList<TerrainTextureLocation> getKvadratiCoords() {
        return KvadratiCoords;
    }

    public ArrayList<TerrainTextureLocation> getKrogiCoords() {
        return KrogiCoords;
    }

    public ArrayList<TerrainTextureLocation> getValjCoords() {
        return ValjCoords;
    }

    public ArrayList<TerrainTextureLocation> getPiramidaCoords() {
        return PiramidaCoords;
    }

    public TerrainTextureLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TerrainTextureLocation(String image) {
        getCoords(image);
    }



    public void getCoords(String imagePath){
        int counter=0;

        File file= new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i =1; i <image.getWidth();i++){
            for (int j =1; j <image.getHeight();j++){
                int clr=  image.getRGB(i,j);
                int  red   = (clr & 0x00ff0000) >> 16;
                int  green = (clr & 0x0000ff00) >> 8;
                int  blue  =  clr & 0x000000ff;

                if(red==255 && green == 0 && blue==0){
                    PiramidaCoords.add(new TerrainTextureLocation(i-100,j-60));
                }
                else if(red==0 && green == 255 && blue==0){
                    ValjCoords.add(new TerrainTextureLocation(i-100,j-60));
                }
                else if(red==0 && green == 0 && blue==255){
                    KvadratiCoords.add(new TerrainTextureLocation(i-100,j-60));
                }
                else if(red==100 && green == 100 && blue==100){
                    KrogiCoords.add(new TerrainTextureLocation(i-100,j-60));
                }

            }
        }
    }


}

