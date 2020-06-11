package com.sybinal.shop.controller.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class ImageCheckerTest {
    private File path;
    @Before
    public void init(){
        path = new File("D:\\images");
    }

    public boolean check4(File file){
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }

    @Test
    public void testImageType() {
        File[] files = path.listFiles();
        for (File file : files){
            System.out.println("Check file:     " + file.getName() +" : " + check4(file));
        }

    }
}