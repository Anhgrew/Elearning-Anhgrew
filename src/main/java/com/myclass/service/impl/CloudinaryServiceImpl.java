package com.myclass.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.myclass.config.CloudinaryConfig;
import com.myclass.dto.CourseDto;
import com.myclass.dto.UserDto;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String uploadFile(MultipartFile[] fileDatas, String name, String key, String secret) {
		for (MultipartFile fileData : fileDatas) {

			String fileNameWithExt = fileData.getOriginalFilename();

			String cloudinaryImgURL = null;
			if (fileNameWithExt != null && fileNameWithExt.length() > 0) {
				try {

					File fileDir = new File("rowFiles");
					if (!fileDir.exists()) {
						fileDir.mkdir();
					}

					String fileName = FilenameUtils.removeExtension(fileNameWithExt);

					CourseDto productDto = courseRepository.findFileByName(fileName);
					if (productDto != null) {
						String random = String.valueOf((System.currentTimeMillis() / 1000l));
						fileName += "_" + random;
					}

					File physicalFile = new File(fileData.getOriginalFilename());
					FileOutputStream fout = new FileOutputStream(fileDir.getName() + "/" + physicalFile);
					fout.write(fileData.getBytes());
					fout.close();
					File toUpload = new File("rowFiles/" + fileNameWithExt);
					System.out.println("to Upload: " + toUpload.toString());
					CloudinaryConfig cloudinary = new CloudinaryConfig(key, secret, name);
					@SuppressWarnings("rawtypes")
					Map params = ObjectUtils.asMap("public_id", "Elearning/" + fileName);
//				        Map uploadResult = cloudinary.uploader().upload(toUpload, params);
					@SuppressWarnings("rawtypes")
					Map uploadResult = cloudinary.upload(toUpload, params);
					toUpload.delete();
					System.out.println("==============>>" + uploadResult.get("url"));
					cloudinaryImgURL = uploadResult.get("url").toString();
					return cloudinaryImgURL;
				} catch (Exception e) {
					System.out.println("upload:" + e.getMessage());
				}
			}
		}
		return null;

	}

	@Override
	public String uploadFileUser(MultipartFile[] fileDatas, String name, String key, String secret) {
		for (MultipartFile fileData : fileDatas) {

			String fileNameWithExt = fileData.getOriginalFilename();

			String cloudinaryImgURL = null;
			if (fileNameWithExt != null && fileNameWithExt.length() > 0) {
				try {

					File fileDir = new File("rowFiles");
					if (!fileDir.exists()) {
						fileDir.mkdir();
					}

					String fileName = FilenameUtils.removeExtension(fileNameWithExt);

					UserDto productDto = userRepository.findFileByAvatarName(fileName);
					if (productDto != null) {
						String random = String.valueOf((System.currentTimeMillis() / 1000l));
						fileName += "_" + random;
					}

					File physicalFile = new File(fileData.getOriginalFilename());
					FileOutputStream fout = new FileOutputStream(fileDir.getName() + "/" + physicalFile);
					fout.write(fileData.getBytes());
					fout.close();
					File toUpload = new File("rowFiles/" + fileNameWithExt);
					System.out.println("to Upload: " + toUpload.toString());
					CloudinaryConfig cloudinary = new CloudinaryConfig(key, secret, name);
					@SuppressWarnings("rawtypes")
					Map params = ObjectUtils.asMap("public_id", "Elearning/userAvatar/" + fileName);
//				        Map uploadResult = cloudinary.uploader().upload(toUpload, params);
					@SuppressWarnings("rawtypes")
					Map uploadResult = cloudinary.upload(toUpload, params);
					toUpload.delete();
					System.out.println("==============>>" + uploadResult.get("url"));
					cloudinaryImgURL = uploadResult.get("url").toString();
					return cloudinaryImgURL;
				} catch (Exception e) {
					System.out.println("upload:" + e.getMessage());
				}
			}
		}
		return null;

	}

}
