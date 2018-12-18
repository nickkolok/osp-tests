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
import java.util.Arrays;

class FindShiftedPatternTests {

    @Test
    @DisplayName("ShiftedPatternRecognition")
    void ShiftedPatternRecognition() {

        TemplateMatcher templateMatcher = null;

        BufferedImage wholeImg = null, templateImg1 = null, templateImg2 = null;



		try
		{
			wholeImg = ImageIO.read(new File("osp-tests/media/VID_20181210_013457_img_8.png"));
			wholeImg = BufferedImageUtils.convertIfNeeded(wholeImg, BufferedImage.TYPE_INT_RGB);

			templateImg1 = BufferedImageUtils.convertIfNeeded(
					ImageIO.read(new File("osp-tests/media/VID_20181210_013457_tmp1_8.png")),
					BufferedImage.TYPE_INT_ARGB);
			templateImg2 = BufferedImageUtils.convertIfNeeded(
					ImageIO.read(new File("osp-tests/media/VID_20181210_013457_tmp2_8.png")),
							BufferedImage.TYPE_INT_ARGB);
		} catch (IOException e){e.printStackTrace();}

        Rectangle mask = new Rectangle(-100,-100,200,200);
		templateMatcher = new TemplateMatcher(templateImg1, mask);

		TPoint p = templateMatcher.getMatchLocation(wholeImg,new Rectangle(528, 434, 81, 81));
		System.out.println(p);
		System.out.println(Arrays.toString(templateMatcher.getMatchWidthAndHeight()));

		templateImg2 = BufferedImageUtils.copyImage(templateImg1);

		BufferedImageUtils.shiftColors(templateImg2,new int[]{-7,-7,-7});

		templateMatcher.setTemplate(templateImg2);
		p = templateMatcher.getMatchLocation(wholeImg,new Rectangle(528, 434, 81, 81));
		System.out.println(p);
		System.out.println(Arrays.toString(templateMatcher.getMatchWidthAndHeight()));

		BufferedImageUtils.shiftColors(templateImg2,new int[]{-7,-7,-7});

		templateMatcher.setTemplate(templateImg2);
		p = templateMatcher.getMatchLocation(wholeImg,new Rectangle(528, 434, 81, 81));
		System.out.println(p);
		System.out.println(Arrays.toString(templateMatcher.getMatchWidthAndHeight()));
/*

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

        System.out.println(templateMatcher.getMatchWidthAndHeight()[0]);
        System.out.println(templateMatcher.getMatchWidthAndHeight()[1]);

        TemplateMatcherOld1 templateMatcherOld1 = new TemplateMatcherOld1(bufImg,mask);
        templateMatcherOld1.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth(), wholeImg2.getHeight()));

        System.out.println(templateMatcherOld1.getMatchWidthAndHeight()[0]);
        System.out.println(templateMatcherOld1.getMatchWidthAndHeight()[1]);


        matchPoint = templateMatcher.getMatchLocation(wholeImg2, new Rectangle(0, 0, wholeImg2.getWidth()-10, wholeImg2.getHeight()-10));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1216.842152749612) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 728.4886287544222) < 0.001, true);

        matchPoint = templateMatcher.getMatchLocation(wholeImg2, new Rectangle(1200, 700, 240, 240));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1216.842152749612) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 728.4886287544222) < 0.001, true);

        templateMatcherOld1.getMatchLocation(wholeImg2, new Rectangle(1200, 700, 240, 240));

        System.out.println(templateMatcher.getMatchWidthAndHeight()[0]);
        System.out.println(templateMatcher.getMatchWidthAndHeight()[1]);

        System.out.println(templateMatcherOld1.getMatchWidthAndHeight()[0]);
        System.out.println(templateMatcherOld1.getMatchWidthAndHeight()[1]);



        matchPoint = templateMatcher.getMatchLocation(wholeImg3, new Rectangle(0, 0, wholeImg3.getWidth(), wholeImg3.getHeight()));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1206.8495062326326) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 813.1373763187489) < 0.001, true);

        matchPoint = templateMatcher.getMatchLocation(wholeImg3dark, new Rectangle(1200, 700, 240, 240));
        System.out.println(matchPoint);
        assertEquals(Math.abs(matchPoint.x - 1206.809439349297) < 0.001, true);
        assertEquals(Math.abs(matchPoint.y - 813.1654226398783) < 0.001, true);
*/

    }

}

