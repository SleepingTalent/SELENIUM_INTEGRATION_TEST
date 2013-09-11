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
        images.add("desert-2.jpg");
        images.add("lighthouse-2.jpg");
        images.add("tulips-2.jpg");

        return images;
    }
}
