package com.ecw.backendapi.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FileUploadHelper {
	@Autowired
	ServletContext context;
	String Upload_Dir = "";
	String realPath = "";

	public byte[] doFileUpload(MultipartFile file, HttpServletRequest request) {
		String staticPath = "upload_product_file";
		boolean issuccess = false;
		if (file.isEmpty()) {
			return null;
		}
		try {
			String currentPath = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + currentPath);
			String currentDir = System.getProperty("user.dir");
			System.out.println("Current dir using System:" + currentDir);
			realPath = context.getRealPath(File.separator + "resources" + File.separator + "static");
			byte[] bytes = file.getBytes();
			Path path = Paths
					.get(currentDir + File.separator + "uploadfiles" + File.separator + file.getOriginalFilename());
			Files.write(path, bytes);

			issuccess = true;
			File img = new File(path.toString());
			ResponseEntity<byte[]> body = ResponseEntity.ok()
					.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
					.body(Files.readAllBytes(img.toPath()));
			byte[] allBytes = Files.readAllBytes(img.toPath());

			return allBytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// delete file from folder
	public boolean deleteImageFromFolder(String filename) {
		try {

			// realPath = context.getRealPath(File.separator + "resources" + File.separator
			// + "upload_file");
			// System.out.println("absolute path" + realPath);
			Path path = Paths.get(realPath + File.separator + filename);
			System.out.println("absolute path" + path);
			File file = new File(path.toString());
			System.out.println("absolute file" + file);
			System.out.println("File is Exists aor not " + file.getAbsolutePath());
			return (file.exists()) ? (file.delete()) ? true : false : false;
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

//	to load image 

	// To view an image
	public byte[] getImage(String imageDirectory, String imageName) throws IOException {
		Path imagePath = Path.of(imageDirectory, imageName);

		if (Files.exists(imagePath)) {
			byte[] imageBytes = Files.readAllBytes(imagePath);
			return imageBytes;
		} else {
			return null; // Handle missing images
		}

	}
	
	
	
	  // Delete an image
    public String deleteImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed"; // Handle missing images
        }
    }

}
