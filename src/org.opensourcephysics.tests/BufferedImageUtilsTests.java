package org.opensourcephysics.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.opensourcephysics.media.core.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.geom.*;
import java.awt.Rectangle;
import java.util.Arrays;

class BufferedImageUtilsTests {

    @Test
    @DisplayName("AverageColor")
    void AverageColor() {

        BufferedImage bufImg = null;
        try {
            bufImg = ImageIO.read(new File("osp-tests/media/average_colors1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        double[] avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(0, 0, 12, 12));
        assertArrayEquals(avg, new double[]{196.0, 188.8125, 191.25});
        //System.out.println(Arrays.toString(avg));

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(0, 0, 3, 3));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 0.0, 0.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(0, 0, 2, 2));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 0.0, 0.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(0, 0, 3, 6));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 108.0, 0.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(6, 6, 3, 3));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 255.0, 255.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(3, 3, 6, 6));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{127.5, 127.5, 255.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(6, 6, 3, 6));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 255.0, 255.0});

        avg = BufferedImageUtils.getAverageColorInRect(bufImg, new Rectangle(6, 6, 6, 6));
        //System.out.println(Arrays.toString(avg));
        assertArrayEquals(avg, new double[]{255.0, 255.0, 255.0});


    }

    @Test
    @DisplayName("ShiftColor")
    void ShiftColor() {

        BufferedImage bufImg = null;
        try {
            bufImg = ImageIO.read(new File("osp-tests/media/average_colors1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: de-facto a duplicate of one of test files
        int[] initialData = {-65536, -65536, -65536, -11731200, -11731200, -11731200, -16777216, -16777216, -16777216, -1, -1, -1, -65536, -65536, -65536, -11731200, -11731200, -11731200, -16777216, -16777216, -16777216, -1, -1, -1, -65536, -65536, -65536, -11731200, -11731200, -11731200, -16777216, -16777216, -16777216, -1, -1, -1, -10240, -10240, -10240, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -10240, -10240, -10240, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -10240, -10240, -10240, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        int[] rgbData = bufImg.getRGB(
                0, 0,
                bufImg.getWidth(), bufImg.getHeight(),
                null,
                0,
                bufImg.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(initialData, rgbData);


        BufferedImage bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{0,0,0});

        rgbData = bufImg.getRGB(
                0, 0,
                bufImg.getWidth(), bufImg.getHeight(),
                null,
                0,
                bufImg.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(initialData, rgbData);

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(initialData, rgbData);



        bufImg = BufferedImageUtils.convertIfNeeded(bufImg, BufferedImage.TYPE_INT_RGB);
        bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{0,0,0});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(initialData, rgbData);

        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{255,0,0});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(rgbData,new int[]{-65536, -65536, -65536, -256, -256, -256, -65536, -65536, -65536, -1, -1, -1, -65536, -65536, -65536, -256, -256, -256, -65536, -65536, -65536, -1, -1, -1, -65536, -65536, -65536, -256, -256, -256, -65536, -65536, -65536, -1, -1, -1, -10240, -10240, -10240, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -10240, -10240, -10240, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -10240, -10240, -10240, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -1, -1, -1, -65281, -65281, -65281, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});

        bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{0,0,128});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        // System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(rgbData,new int[]{-65408, -65408, -65408, -11731072, -11731072, -11731072, -16777088, -16777088, -16777088, -1, -1, -1, -65408, -65408, -65408, -11731072, -11731072, -11731072, -16777088, -16777088, -16777088, -1, -1, -1, -65408, -65408, -65408, -11731072, -11731072, -11731072, -16777088, -16777088, -16777088, -1, -1, -1, -10112, -10112, -10112, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -10112, -10112, -10112, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -10112, -10112, -10112, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});
        /*
        try {
            ImageIO.write(bufImgCopy,"PNG",new File("osp-tests/media/average_colors_result2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{0,0,255});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(rgbData,new int[]{-65281, -65281, -65281, -11730945, -11730945, -11730945, -16776961, -16776961, -16776961, -1, -1, -1, -65281, -65281, -65281, -11730945, -11730945, -11730945, -16776961, -16776961, -16776961, -1, -1, -1, -65281, -65281, -65281, -11730945, -11730945, -11730945, -16776961, -16776961, -16776961, -1, -1, -1, -9985, -9985, -9985, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -9985, -9985, -9985, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -9985, -9985, -9985, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16776961, -16776961, -16776961, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});

        /*
        try {
            ImageIO.write(bufImgCopy,"PNG",new File("osp-tests/media/average_colors_result_0_0_255.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{0,255,0});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(rgbData,new int[]{-256, -256, -256, -11731200, -11731200, -11731200, -16711936, -16711936, -16711936, -1, -1, -1, -256, -256, -256, -11731200, -11731200, -11731200, -16711936, -16711936, -16711936, -1, -1, -1, -256, -256, -256, -11731200, -11731200, -11731200, -16711936, -16711936, -16711936, -1, -1, -1, -256, -256, -256, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -256, -256, -256, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -256, -256, -256, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16711681, -16711681, -16711681, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});

        /*
        try {
            ImageIO.write(bufImgCopy,"PNG",new File("osp-tests/media/average_colors_result_0_255_0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        bufImgCopy = BufferedImageUtils.copyImage(bufImg);
        BufferedImageUtils.shiftColors(bufImgCopy, new int[]{-255,0,-128});

        rgbData = bufImgCopy.getRGB(
                0, 0,
                bufImgCopy.getWidth(), bufImgCopy.getHeight(),
                null,
                0,
                bufImgCopy.getWidth()
        );
        //System.out.println(Arrays.toString(rgbData));
        assertArrayEquals(rgbData,new int[]{-16777216, -16777216, -16777216, -16711936, -16711936, -16711936, -16777216, -16777216, -16777216, -16711809, -16711809, -16711809, -16777216, -16777216, -16777216, -16711936, -16711936, -16711936, -16777216, -16777216, -16777216, -16711809, -16711809, -16711809, -16777216, -16777216, -16777216, -16711936, -16711936, -16711936, -16777216, -16777216, -16777216, -16711809, -16711809, -16711809, -16721920, -16721920, -16721920, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16721920, -16721920, -16721920, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16721920, -16721920, -16721920, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16777089, -16777089, -16777089, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809, -16711809});

        /*
        try {
            ImageIO.write(bufImgCopy,"PNG",new File("osp-tests/media/average_colors_result_-255_0_-128.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
