package com.fs.humanResources.image.controller;

import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.List;

public class ImageControllerTest extends BaseUnitTest {

    @InjectMocks
    ImageController imageController;

    @Test
    public void getNewsImages_returnsExpectedImageNames() {
        List<String> imageNames = imageController.getNewsImages();

        Assert.assertEquals(5, imageNames.size());
        Assert.assertEquals("office-1.jpg", imageNames.get(0));
        Assert.assertEquals("office-2.jpg", imageNames.get(1));
        Assert.assertEquals("office-3.jpg", imageNames.get(2));
        Assert.assertEquals("office-4.jpg", imageNames.get(3));
        Assert.assertEquals("office-5.jpg", imageNames.get(4));
    }


    @Test
    public void getAdinImages_returnsExpectedImageNames() {
        List<String> imageNames = imageController.getAdminImages();

        Assert.assertEquals(3, imageNames.size());
        Assert.assertEquals("business-1.jpg", imageNames.get(0));
        Assert.assertEquals("business-8.jpg", imageNames.get(1));
        Assert.assertEquals("business-9.jpg", imageNames.get(2));
    }

    @Test
    public void getNewStartImages_returnsExpectedImageNames() {
        List<String> imageNames = imageController.getNewStartImages();

        Assert.assertEquals(3, imageNames.size());
        Assert.assertEquals("business-2.jpg", imageNames.get(0));
        Assert.assertEquals("business-3.jpg", imageNames.get(1));
        Assert.assertEquals("business-6.jpg", imageNames.get(2));
    }
}
