package com.myclass.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.myclass.dto.ChangePassWordDto;
import com.myclass.dto.LoginDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.service.AuthenticationService;
import com.myclass.service.CategoryService;
import com.myclass.service.CloudinaryService;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;
import com.myclass.service.VideoService;
import com.myclass.validation.EmailExistsException;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private CloudinaryService cloudinaryService;

//	@Autowired
//	private RoleService roleService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserCourseService userCourseService;

	@GetMapping("profile")
	public String index(ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		ChangePassWordDto changePassWord = new ChangePassWordDto();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
			changePassWord.setEmail(currentAccount.getEmail());
			changePassWord.setPassword(currentAccount.getPassword());
			changePassWord.setId(currentAccount.getId());
		}

		model.addAttribute("changePassWord", changePassWord);
		model.addAttribute("user", new UserDto(user));
		model.addAttribute("categories", categoryService.findAll());
		return "client/user/profile";
	}

//	@PostMapping("search")
//	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
//
//		model.addAttribute("users", userService.findAllByKeyWord(keyword));
//		return "user/index";
//	}

	@PostMapping("enroll")
	public String enrol(ModelMap model, @Valid @ModelAttribute("usercourse") UserCourse userCourse) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", user);
		userCourseService.add(userCourse);
		return "redirect:/user/mycourse";
	}

	@GetMapping("mycourse")
	public String add(ModelMap model) {
		model.addAttribute("action", "/user/mycourse/search");
		model.addAttribute("categories", categoryService.findAll());
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", new UserDto(user));
		model.addAttribute("courses", courseService.findCourseByUserId(currentAccount.getId()));
		return "client/user/mycourse";
	}

	@PostMapping("mycourse/search")
	public String search(@RequestParam("keyword") String keyword, ModelMap model) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", new UserDto(user));
		//
		if (userService.findById(currentAccount.getId()) != null) {
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		List<Course> myCourses = courseService.findCourseByUserIdAndKeyWord(currentAccount.getId(), keyword);
		model.addAttribute("courses", myCourses);
		return "client/user/mycourse";
	}

	@GetMapping("mycourse/detail/{id}")
	public String detail(ModelMap model, @PathVariable("id") int id) {
		User user = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		if (currentAccount != null) {
			user = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		model.addAttribute("user", new UserDto(user));
		model.addAttribute("userCourse", new UserCourse());
		model.addAttribute("videos", videoService.urlId(id));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("course", courseService.findById(id).get());
		model.addAttribute("courses", courseService.findByCategoryId(id));
		return "client/user/mycoursedetail";
	}

	@PostMapping("profile/detail")
	public String detail(ModelMap model, @Valid @ModelAttribute("user") UserDto user, BindingResult errors) {
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		User tmp = null;
		if (currentAccount != null) {
			tmp = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "client/user/profile";
		}
		userService.updateDetail(user);
		if (tmp != null && tmp.getRole().getName().contains("ROLE_TEACHER")) {
			return "redirect:/user/profile";
		} else {
			return "redirect:/user/profile";
		}

	}

	@PostMapping("profile/picture")
	public String picture(ModelMap model, @Valid @ModelAttribute("user") UserDto user, BindingResult errors) {
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		User tmp = null;
		if (currentAccount != null) {
			tmp = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
		}
		if (errors.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "client/user/profile";
		}

		String oldImage = user.getAvatar();
		String cloudinaryImgURL = "";
		if (!courseService.checkFile(user.getFileDatas())) {
			cloudinaryImgURL = cloudinaryService.uploadFile(user.getFileDatas(), "dinw4xpxn", "965178722757942",
					"yzhCmpizKaFKpluXd6Om2bDTv_I");
		} else {
			cloudinaryImgURL = oldImage;
		}

		user.setAvatar(cloudinaryImgURL);
		userService.updatePicture(user);

		if (tmp != null) {
			return "redirect:/user/profile";
		} else {
			return "redirect:/user";
		}

	}

	@PostMapping("profile/security")
	public String securities(@Valid @ModelAttribute("changePassWord") ChangePassWordDto user, BindingResult result,
			WebRequest request, Errors errors, ModelMap model) throws EmailExistsException {
		User userReturn = null;
		LoginDto currentAccount = authenticationService.getCurrentLoginAccount();
		ChangePassWordDto changePassWord = new ChangePassWordDto();
		if (currentAccount != null) {
			userReturn = userService.findById(currentAccount.getId()).get();
			model.addAttribute("enrolls", userCourseService.countByUserId(currentAccount.getId()));
			changePassWord.setEmail(currentAccount.getEmail());
			changePassWord.setPassword(currentAccount.getPassword());
			changePassWord.setId(currentAccount.getId());
		}

		User dataBaseUser = userService.findByEmailAndDelete(user.getEmail());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean comparePassWord = false;
		if (dataBaseUser != null) {

			if (passwordEncoder.matches(user.getPassword(), dataBaseUser.getPassword())) {
				comparePassWord = true;
			} else {
				comparePassWord = false;
			}

		}

		if (comparePassWord) {
			boolean confirmMatching = user.getConfirm().equals(user.getNewPassWord());
			if (!result.hasErrors() && confirmMatching) {
				userService.updateSecurity(user);
				return "redirect:/user/profile";
			} else {

				model.addAttribute("changePassWord", changePassWord);
				model.addAttribute("user", new UserDto(userReturn));
				model.addAttribute("mess", "Update failed or your data is invalid!");
				if (!confirmMatching) {
					model.addAttribute("confirmMessage", "Password confirm and password don't match or empty!");
				}
				return "client/user/profile";
			}
		} else {
			model.addAttribute("changePassWord", changePassWord);
			model.addAttribute("user", new UserDto(userReturn));
			model.addAttribute("comparePassWord", "Password is invalid!");
			return "client/user/profile";
		}

	}

}
