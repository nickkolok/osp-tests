package org.opensourcephysics.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


class TemplateMatcherTests {

    @Test
    @DisplayName("Simple pattern recognition")
    void SimplePatternRecognition() {

        TemplateMatcher templateMatcher = null;
        BufferedImage
                bufImg = null,
                tmpImg = null,
                wholeImg1 = null,
                wholeImg2 = null,
                wholeImg3 = null,
                wholeImg3dark = null;
        Ellipse2D.Double mask = null;
        try
        {
            wholeImg1 = ImageIO.read(new File("osp-tests/media/glass_full1.png"));
            tmpImg = new BufferedImage(wholeImg1.getWidth(), wholeImg1.getHeight(), BufferedImage.TYPE_INT_RGB);
            tmpImg.getGraphics().drawImage(wholeImg1, 0, 0, null);
            wholeImg1 = tmpImg;

            wholeImg2 = ImageIO.read(new File("osp-tests/media/glass_full2.png"));
            tmpImg = new BufferedImage(wholeImg2.getWidth(), wholeImg2.getHeight(), BufferedImage.TYPE_INT_RGB);
            tmpImg.getGraphics().drawImage(wholeImg2, 0, 0, null);
            wholeImg2 = tmpImg;

            wholeImg3 = ImageIO.read(new File("osp-tests/media/glass_full3.png"));
            wholeImg3dark = ImageIO.read(new File("osp-tests/media/glass_full3_dark.png"));
        } catch (IOException e){e.printStackTrace();}


        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();


        try
        {
            bufImg = ImageIO.read(new File("osp-tests/media/glass_pattern1.png"));
        } catch (IOException e){e.printStackTrace();}

        mask = new Ellipse2D.Double(0,0,bufImg.getWidth(), bufImg.getHeight());
        templateMatcher = new TemplateMatcher(bufImg,mask);

        TPoint matchPoint = templateMatcher.getMatchLocation(wholeImg1, new Rectangle(0, 0, wholeImg1.getWidth(), wholeImg1.getHeight()));
        assertEquals(matchPoint, new TPoint(1173.0, 643.0), "The point should be exact!");

        matchPoint = templateMatcher.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth(), wholeImg2.getHeight()));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1216.842152749612) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 728.4886287544222) < 0.001, true);


        matchPoint = templateMatcher.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth()-10, wholeImg2.getHeight()-10));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1216.842152749612) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 728.4886287544222) < 0.001, true);

        matchPoint = templateMatcher.getMatchLocation(wholeImg2, new Rectangle(1200, 700, 240, 240));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1216.842152749612) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 728.4886287544222) < 0.001, true);


        matchPoint = templateMatcher.getMatchLocation(wholeImg3, new Rectangle(0, 0, wholeImg3.getWidth(), wholeImg3.getHeight()));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1206.8495062326326) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 813.1373763187489) < 0.001, true);

        matchPoint = templateMatcher.getMatchLocation(wholeImg3dark, new Rectangle(1200, 700, 240, 240));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1206.809439349297) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 813.1654226398783) < 0.001, true);


    }

}
