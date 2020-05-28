//package org.mynode.controller;
//
//
//import com.fasterxml.jackson.annotation.JsonView;
//import org.mynode.model.Customer;
//import org.mynode.model.Image;
//import org.mynode.model.view.JsView;
//import org.mynode.service.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping(value = {"/files"})
//public class FileController {
//    @Autowired
//    private FileService fileService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private ImageService imageService;
//    @Autowired
//    MessageService messageService;
//    @Value("${aws.sqs.name}")
//    private String queueName;
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @RequestMapping(value="", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
//        logger.info("test file name: " + file.getOriginalFilename());
//        try{
//            String s3key = fileService.uploadFile(file);
//            HttpSession session = request.getSession();
//            Long id = (Long)session.getAttribute("curUser");
//            Customer customer = customerService.getCustomerById(id);
////            String bear = request.getHeader("authorization");
////            char[] arr = bear.toCharArray();
////            char[] tokenTemp = new char[arr.length - 8];
////            for (int i = 0; i < tokenTemp.length; i++) {
////                tokenTemp[i] = arr[i + 8];
////            }
////            String token = String.copyValueOf(tokenTemp);
////            Claims claims = JWTService.decodeJwtToken(token);
////            Customer customer = customerService.getCustomerByName(claims.getSubject());
//            Image image = new Image(file.getOriginalFilename(), s3key, LocalDateTime.now(), customer);
//            imageService.save(image);
//            String url = fileService.getUrl(s3key);
//            messageService.sendMessage(url, 1);
//            return url;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @RequestMapping(value="/{id}", method = RequestMethod.GET)
//    @JsonView({JsView.User.class})
//    public List<Image> getImages(@RequestParam("id") long id){
//        try{
//            List<Image> images = imageService.getImagesByUserId(id);
//            return images;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
