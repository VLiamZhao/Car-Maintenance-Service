package org.mynode.repository;

import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.model.Image;

import java.util.List;

public interface ImageDao {
    Image save(Image image);
    Image getImageById(long id);
    boolean deleteById(long id);
    List<Image> getAllImages();
    List<Image> getImagesByUserId(long id);
}

