package com.myclass.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.CourseDto;
import com.myclass.dto.LoginDto;
import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.entity.Video;
import com.myclass.service.AuthenticationService;
import com.myclass.service.CategoryService;
import com.myclass.service.CloudinaryService;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;
import com.myclass.service.VideoService;

@Controller
@RequestMapping("lecturer")
public class LecturerController {

	public int idOfCurCourse;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private UserCourseService userCourseService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String index(ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("courses", courseService.findByLecturerId(currentAccount.getId()));
		return "client/lecturer/course";
	}

	@GetMapping("upload")
	public String add(ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("course", new CourseDto());
		model.addAttribute("categories", categoryService.findAll());
		return "client/lecturer/upload";
	}

	@PostMapping("upload")
	public String add(ModelMap model, @Valid @ModelAttribute("course") CourseDto course, BindingResult errors) {
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "course/add";
		}
		String cloudinaryImgURL = cloudinaryService.uploadFile(course.getFileDatas(), "dinw4xpxn", "965178722757942",
				"yzhCmpizKaFKpluXd6Om2bDTv_I");
		course.setImageUrl(cloudinaryImgURL);
		course.setLecturerId(currentAccount.getId());
		Course tmpCourse = new Course(course.getId(), course.getTitle(), course.getImageUrl(), course.getLeturesCount(),
				course.getAuthor(), course.getHourCount(), course.getPrice(), course.getDiscount(),
				course.getDescription(), course.getContent(), course.getCategoryId());
		tmpCourse.setLecturerId(currentAccount.getId());
		courseService.add(tmpCourse);

		return "redirect:/lecturer";
	}

	@GetMapping("addVideo/{id}")
	public String addVideo(ModelMap model, @PathVariable("id") int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("video", new Video());
		idOfCurCourse = id;
		Course courses = courseService.findById(id).get();
		model.addAttribute("courses", courses);
		return "client/lecturer/addToCourse";
	}

	@PostMapping("addVideo")
	public String addVideo(ModelMap model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		// Nếu có lỗi
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		String url = "client/lecturer/addVideo/".concat(Integer.toString(idOfCurCourse));
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return url;
		}
		String tmpUrl = "redirect:/lecturer/edit/".concat(Integer.toString(idOfCurCourse));
		videoService.update(video);
		return tmpUrl;
	}

	@GetMapping("editVideo/{id}")
	public String editVideo(@PathVariable("id") int id, ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		Optional<Video> video = videoService.findById(id);
		Optional<Course> courses = courseService.findById(idOfCurCourse);
		model.addAttribute("video", video.get());
		model.addAttribute("courses", courses.get());
		return "client/lecturer/editVideo";
	}

	@PostMapping("editVideo")
	public String editVideo(ModelMap model, @Valid @ModelAttribute("video") Video video, BindingResult errors) {
		// Nếu có lỗi
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		if (errors.hasErrors()) {
			Optional<Course> courses = courseService.findById(idOfCurCourse);
			model.addAttribute("courses", courses.get());
			return "client/lecturer/editVideo";
		}
		String tmpUrl = "redirect:/lecturer/edit/".concat(Integer.toString(idOfCurCourse));
		videoService.update(video);
		return tmpUrl;
	}

	// Lấy thông tin videos theo id
	@GetMapping("deleteVideo/{id}")
	public String deleteVideo(@PathVariable("id") int id) {

		videoService.removeById(id);
		String url = "redirect:/lecturer/edit/".concat(Integer.toString(idOfCurCourse));
		return url;
	}

	@GetMapping("deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") int id) {

		courseService.removeById(id);

		return "redirect:/lecturer";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}

		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		idOfCurCourse = id;
		Course course = courseService.findById(id).get();
		course.setLecturerId(currentAccount.getId());
		CourseDto tmp = new CourseDto(course.getId(), course.getTitle(), course.getLeturesCount(), course.getImage(),
				course.getAuthor(), course.getHourCount(), course.getViewCount(), course.getPrice(),
				course.getDiscount(), course.getPromotionPrice(), course.getDescription(), course.getContent(),
				course.getLecturerId());
		model.addAttribute("videos", videoService.urlId(id));
		model.addAttribute("defaultCategory", course.getCategoryId());
		model.addAttribute("course", tmp);
		return "client/lecturer/edit";
	}

	@PostMapping("edit")
	public String edit(ModelMap model, @Valid @ModelAttribute("course") CourseDto course, BindingResult errors) {
		User user = null;
		String oldImage = course.getImageUrl();
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "client/lecturer/edit";
		}
		String cloudinaryImgURL = "";
		if (!courseService.checkFile(course.getFileDatas())) {
			cloudinaryImgURL = cloudinaryService.uploadFile(course.getFileDatas(), "dinw4xpxn", "965178722757942",
					"yzhCmpizKaFKpluXd6Om2bDTv_I");
		} else {
			cloudinaryImgURL = oldImage;
		}

		course.setLecturerId(currentAccount.getId());
		Course tmpCourse = new Course(course.getId(), course.getTitle(), cloudinaryImgURL, course.getLeturesCount(),
				course.getAuthor(), course.getHourCount(), course.getPrice(), course.getDiscount(),
				course.getDescription(), course.getContent(), course.getCategoryId());
		tmpCourse.setLecturerId(currentAccount.getId());
		courseService.update(tmpCourse);
		return "redirect:/lecturer";
	}

	@GetMapping("course/{id}")
	public String detail(ModelMap model, @PathVariable("id") int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("userCourse", new UserCourse());
		model.addAttribute("videos", videoService.urlId(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("course", courseService.findById(id).get());
		model.addAttribute("courses", courseService.findByCategoryId(id));
		return "client/lecturer/detail";
	}

	@GetMapping("member/{id}")
	public String member(ModelMap model, @PathVariable("id") int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		model.addAttribute("categories", categoryService.findAll());
		List<User> members = userService.listMember(id);
		idOfCurCourse = id;
		model.addAttribute("members", members);
		model.addAttribute("size", members.size());
		return "client/lecturer/member";
	}

	@GetMapping("member/kick/{id}")
	public String kick(ModelMap model, @PathVariable("id") String id) {
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {

			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("categories", categoryService.findAll());
		userCourseService.deleteByUserIdAndCourseId(id, idOfCurCourse);
		String tmpUrl = "redirect:/lecturer/member/".concat(Integer.toString(idOfCurCourse));
		return tmpUrl;
	}

}
