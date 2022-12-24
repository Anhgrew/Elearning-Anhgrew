package com.myclass.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("upload")
public class UploadController {

	@GetMapping("")
	public String index() {
		return "upload-index";
	}

	@PostMapping("")
	public String upload(@RequestParam CommonsMultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {

		// Lấy đường dẫn thư mục gốc
		String rootFolder = request.getServletContext().getRealPath("");
		System.out.println(rootFolder);

		// Tạo đường dẫn thư mục chứa file upload
		File folderUpload = new File(rootFolder, "static/upload");
		System.out.println(folderUpload);

		// Kiểm tra nếu thư mục chưa tồn tại thì tạo mới
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}

		// Lấy tên của file upload
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);

		// Tạo đường dẫn tuyệt đối cho file vừa upload lên
		File pathFile = new File(folderUpload, fileName);
		System.out.println(pathFile);

		// Lưu file xuống thư mục upload
		file.transferTo(pathFile);

		request.setAttribute("path", "/upload/" + fileName);
		return "upload-index";
	}
}
