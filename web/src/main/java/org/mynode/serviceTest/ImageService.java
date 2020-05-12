package org.mynode.serviceTest;


import org.mynode.model.Image;
import org.mynode.repository.ImageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

  @Autowired private ImageDao imageDao;
  private Logger logger = LoggerFactory.getLogger(getClass());

  public Image save(Image image) {
    return imageDao.save(image);
  }

  public Image getImagesById(long id) {
    return imageDao.getImageById(id);
  }

  public List<Image> getImagesByUserId(long id) {
    return imageDao.getImagesByUserId(id);
  }

  public boolean deleteById(long id) {
    return imageDao.deleteById(id);
  }

  public List<Image> getAllImages() {
    return imageDao.getAllImages();
  }
}
