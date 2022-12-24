package com.myclass.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	String uploadFile(MultipartFile[] fileDatas, String name, String key, String secret);

	String uploadFileUser(MultipartFile[] fileDatas, String name, String key, String secret);
}
