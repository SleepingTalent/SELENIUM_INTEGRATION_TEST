package com.fs.humanResources.image.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ImageController implements Serializable {

    public List<String> getNewsImages() {

        List<String> images = new ArrayList<String>();
        images.add("office-1.jpg");
        images.add("office-2.jpg");
        images.add("office-3.jpg");
        images.add("office-4.jpg");
        images.add("office-5.jpg");

        return images;
    }

    public List<String> getNewStartImages() {

        List<String> images = new ArrayList<String>();
        images.add("business-2.jpg");
        images.add("business-3.jpg");
        images.add("business-6.jpg");

        return images;
    }
}
