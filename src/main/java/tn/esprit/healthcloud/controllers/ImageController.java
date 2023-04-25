package tn.esprit.healthcloud.controllers;

import java.io.ByteArrayOutputStream; 
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.healthcloud.entities.Image;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.ImageRepository;
import tn.esprit.healthcloud.repositories.UserRepository;
import tn.esprit.healthcloud.services.FirebaseImageService;
import tn.esprit.healthcloud.services.IImageService;
import tn.esprit.healthcloud.services.PatientService;

@RestController
public class ImageController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private IImageService imageService;
    
    @Autowired
    private ImageRepository imageRepo;

//    @Autowired
//    private ImageRepository imageRepository;
//
//    @PostMapping("/upload")
//	public ResponseEntity<Long> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//		System.out.println("Original Image Byte Size - " + file.getBytes().length);
//		Image img = new Image();
//		img.setName(file.getOriginalFilename());
//		img.setType(file.getContentType());
//		img.setImage(compressBytes(file.getBytes()));
//		imageRepository.save(img);
//		return new ResponseEntity<>(img.getId(), HttpStatus.OK);
//	}
//	
//	@GetMapping(path = { "/getImage/{id}" })
//	public Image getImage(@PathVariable("id") Long id) throws IOException {
//		final Optional<Image> retrievedImage = imageRepository.findById(id);
//		Image img = new Image();
//		img.setName(retrievedImage.get().getName());
//		img.setType(retrievedImage.get().getType());
//		img.setImage(decompressBytes(retrievedImage.get().getImage()));
//		return img;
//	}
//    
//    
//    
    
 // compress the image bytes before storing it in the database
 		public static byte[] compressBytes(byte[] data) {
 			Deflater deflater = new Deflater();
 			deflater.setInput(data);
 			deflater.finish();
 			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
 			byte[] buffer = new byte[1024];
 			while (!deflater.finished()) {
 				int count = deflater.deflate(buffer);
 				outputStream.write(buffer, 0, count);
 			}
 			try {
 				outputStream.close();
 			} catch (IOException e) {
 			}
 			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
 			return outputStream.toByteArray();
 		}
 	
 		// uncompress the image bytes before returning it to the angular application
 		public static byte[] decompressBytes(byte[] data) {
 			Inflater inflater = new Inflater();
 			inflater.setInput(data);
 			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
 			byte[] buffer = new byte[1024];
 			try {
 				while (!inflater.finished()) {
 					int count = inflater.inflate(buffer);
 					outputStream.write(buffer, 0, count);
 				}
 				outputStream.close();
 			} catch (IOException ioe) {
 			} catch (DataFormatException e) {
 			}
 			return outputStream.toByteArray();
 		}
 		
 		
 		
 		
 		
 		
 	

 	    @PostMapping("/uploaddd")
 	    public ResponseEntity create(@RequestParam(name = "file") MultipartFile[] files) {

 	            for (MultipartFile file : files) {

 	                try {
 	                    String fileName = imageService.save(file);

 	                    String imageUrl = imageService.getImageUrl(fileName);

 	                    Image image = new Image();
 	                    image.setPath(imageUrl);
 	                    imageRepo.save(image);

 	                } catch (Exception e) {
 	                //  throw internal error;
 	                }
 	            }

 	        return ResponseEntity.ok().build();
 	    }

 		
 		
 		
 	    private static final Logger logger = Logger.getLogger(ImageController.class.getName());

 	   
 		  @PostMapping("/profile/pic/{fileName}")
 	    public Object download(@PathVariable String fileName) throws IOException {
 	        logger.info("HIT -/download | File Name : {}"+ fileName);
 	        return imageService.download(fileName);
 	    }
 		
 		
 		
 		
 	 
 	
    
    
    
}
