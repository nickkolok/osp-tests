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


class TemplateMatcherComparisonTests {

    //Uncomment to run
    @Test
    @DisplayName("Simple pattern recognition")
    void SimplePatternRecognition() {

/*
        BufferedImage
            bufImg = null,
            tmpImg = null,
            wholeImg1 = null,
            wholeImg2 = null,
            wholeImg3 = null;
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
            tmpImg = new BufferedImage(wholeImg3.getWidth(), wholeImg3.getHeight(), BufferedImage.TYPE_INT_RGB);
            tmpImg.getGraphics().drawImage(wholeImg3, 0, 0, null);
            wholeImg3 = tmpImg;
        } catch (IOException e){e.printStackTrace();}




        try
        {
            bufImg = ImageIO.read(new File("osp-tests/media/glass_pattern1.png"));
        } catch (IOException e){e.printStackTrace();}


        mask = new Ellipse2D.Double(0,0,bufImg.getWidth(), bufImg.getHeight());
*/
/*
        long totalTimeOld1=0, totalTimeOld2=0, totalTimeNew=0;
        long time1, time2, time3, time4;



        //BufferedImage input = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //input.createGraphics().drawImage(bufImg, 0, 0, null);

        //assertEquals(input.getType(),BufferedImage.TYPE_INT_ARGB,"image.getType()==BufferedImage.TYPE_INT_ARGB");

        TemplateMatcherOld1 templateMatcherOld1 = new TemplateMatcherOld1(bufImg, mask);
        TemplateMatcherOld2 templateMatcherOld2 = new TemplateMatcherOld2(bufImg, mask);
        TemplateMatcher templateMatcher = new TemplateMatcher(bufImg, mask);


        for(int i = 0; i < 500; i++){
            time1 = System.currentTimeMillis();
            for(int j = 0; j <500; j++) {
                templateMatcherOld1 = new TemplateMatcherOld1(bufImg, mask);
            }

            time2 = System.currentTimeMillis();
            for(int j = 0; j <500; j++) {
                templateMatcher = new TemplateMatcher(bufImg, mask);
            }
            time3 = System.currentTimeMillis();

            for(int j = 0; j <500; j++) {
                templateMatcherOld2 = new TemplateMatcherOld2(bufImg, mask);
            }
            time4 = System.currentTimeMillis();

            totalTimeOld1 += time2 - time1;
            totalTimeNew += time3 - time2;
            totalTimeOld2 += time4 - time3;

        }

        System.out.println("Time consumed by old matcher 1, ms:");
        System.out.println(totalTimeOld1);
        System.out.println("Time consumed by old matcher 2, ms:");
        System.out.println(totalTimeOld2);
        System.out.println("Time consumed by new matcher, ms:");
        System.out.println(totalTimeNew);
*/

/*
        long totalTimeOld1=0, totalTimeOld2=0, totalTimeOld3=0, totalTimeNew=0;
        long time1, time2, time3, time4;


        TemplateMatcherOld1 templateMatcherOld1 = new TemplateMatcherOld1(bufImg, mask);
        TemplateMatcherOld2 templateMatcherOld2 = new TemplateMatcherOld2(bufImg, mask);
        TemplateMatcherOld3 templateMatcherOld3 = new TemplateMatcherOld3(bufImg, mask);
        TemplateMatcher templateMatcherNew = new TemplateMatcher(bufImg, mask);

        TPoint matchPoint = null;

        for(int i = 0; i < 2; i++) {
            time1 = System.currentTimeMillis();
            for (int j = 0; j < 1; j++) {
                matchPoint = templateMatcherNew.getMatchLocation(wholeImg1, new Rectangle(0, 0, wholeImg1.getWidth(), wholeImg1.getHeight()));
                matchPoint = templateMatcherNew.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth(), wholeImg2.getHeight()));
                matchPoint = templateMatcherNew.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth()-10, wholeImg2.getHeight()-10));
                matchPoint = templateMatcherNew.getMatchLocation(wholeImg2, new Rectangle(1200, 700, 240, 240));
                matchPoint = templateMatcherNew.getMatchLocation(wholeImg3, new Rectangle(0, 0, wholeImg3.getWidth(), wholeImg3.getHeight()));
            }
            time2 = System.currentTimeMillis();
            for(int j = 0; j < 1; j++) {
                matchPoint = templateMatcherOld3.getMatchLocation(wholeImg1, new Rectangle(0, 0, wholeImg1.getWidth(), wholeImg1.getHeight()));
                matchPoint = templateMatcherOld3.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth(), wholeImg2.getHeight()));
                matchPoint = templateMatcherOld3.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth()-10, wholeImg2.getHeight()-10));
                matchPoint = templateMatcherOld3.getMatchLocation(wholeImg2, new Rectangle(1200, 700, 240, 240));
                matchPoint = templateMatcherOld3.getMatchLocation(wholeImg3, new Rectangle(0, 0, wholeImg3.getWidth(), wholeImg3.getHeight()));
            }
            time3 = System.currentTimeMillis();


            totalTimeOld3 += time3 - time2;
            totalTimeNew += time2 - time1;
            //totalTimeOld2 += time4 - time3;
        }




        System.out.println("Time consumed by old matcher 3, ms:");
        System.out.println(totalTimeOld3);
        //System.out.println("Time consumed by old matcher 2, ms:");
        //System.out.println(totalTimeOld2);
        System.out.println("Time consumed by new matcher, ms:");
        System.out.println(totalTimeNew);

*/

		assertEquals(2, 2, "Mock assert");





    }


}
