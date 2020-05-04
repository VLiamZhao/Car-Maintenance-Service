package org.mynode.controller;


import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.mynode.model.Customer;
import org.mynode.model.Image;
import org.mynode.service.CustomerService;
import org.mynode.service.FileService;
import org.mynode.service.ImageService;
import org.mynode.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ImageService imageService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value="", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        logger.info("test file name: " + file.getOriginalFilename());
        try{
            String s3key = fileService.uploadFile("canzhao", file);
            String bear = request.getHeader("authorization");
            char[] arr = bear.toCharArray();
            char[] tokenTemp = new char[arr.length - 8];
            for (int i = 0; i < tokenTemp.length; i++) {
                tokenTemp[i] = arr[i + 8];
            }
            String token = String.copyValueOf(tokenTemp);
            Claims claims = JWTService.decodeJwtToken(token);
            Customer customer = customerService.getCustomerByName(claims.getSubject());
            Image image = new Image(file.getOriginalFilename(), s3key, LocalDateTime.now(), customer);
            imageService.save(image);
            return fileService.getUrl(s3key);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
