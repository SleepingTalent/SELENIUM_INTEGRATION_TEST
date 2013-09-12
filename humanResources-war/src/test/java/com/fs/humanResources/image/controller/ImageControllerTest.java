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

        Assert.assertEquals(3, imageNames.size());
        Assert.assertEquals("desert-2.jpg", imageNames.get(0));
        Assert.assertEquals("lighthouse-2.jpg", imageNames.get(1));
        Assert.assertEquals("tulips-2.jpg", imageNames.get(2));
    }
}
