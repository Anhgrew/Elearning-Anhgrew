package com.myclass.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;

public class CloudinaryConfig {
	private Cloudinary cloudinary;

	public CloudinaryConfig(@Value("${cloudinary.apikey}") String key, @Value("${cloudinary.apisecret}") String secret,
			@Value("${cloudinary.cloudname}") String cloud) {
		cloudinary = Singleton.getCloudinary();
		cloudinary.config.cloudName = cloud;
		cloudinary.config.apiSecret = secret;
		cloudinary.config.apiKey = key;
	}

	@SuppressWarnings("rawtypes")
	public Map upload(Object file, Map options) {
		try {
			return cloudinary.uploader().upload(file, options);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String createUrl(String name, int width, int height, String action) {
		return cloudinary.url()
				.transformation(new Transformation().width(width).height(height).border("2px_solid_black").crop(action))
				.imageTag(name);
	}

}