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
}
