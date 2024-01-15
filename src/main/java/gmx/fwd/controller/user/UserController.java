package gmx.fwd.controller.user;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gmx.fwd.service.user.UserService;
import gmx.fwd.vo.uservo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("org.springframework.security.authentication.ProviderManager#0")
	private AuthenticationManager authenticationManager;
	
	// 시작화면 (로그인 화면)
	@GetMapping("/welcomePage.do")
	public String loginPage() {
		return "login/login";
	}

	// 회원가입 페이지로 넘겨주는 컨트롤러
	@GetMapping("/goRegisterPage.do")
	public String register() {
		return "login/register";
	}
	
	@GetMapping("/errorPage.do")
	public String errorPage(Model model) {
		model.addAttribute("msg","Access Denied");
		return "login/errorPage";
	}

	// @PostMapping("/register.do")
	@ResponseBody
	@RequestMapping(value = "/register.do", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	public HashMap<String, String> register(@Valid @ModelAttribute UserVo user, BindingResult bindingResult) {

		HashMap<String, String> response = new HashMap<>();
		
		if (bindingResult.hasErrors()) {
			response.put("registerFlag", "fail");
			return response;
		}

		if (userService.register(user)) {
			response.put("registerFlag", "success");
		} else {
			response.put("registerFlag", "fail");
		}

		return response;
	}

	@GetMapping("/logout.do")
	public String logout() {
		return "login/login";
	}

	// 비빌번호 변경 페이지로 이동시켜주는 컨트롤러
	@GetMapping("/showChangePassword.do")
	public String showChangePasswordPage() {
		return "login/changePassword";
	}

	@PostMapping("/changePasswordAction.do")
	public String changePasswordAction(@RequestParam(name = "currentPassword") String currentPassword,
			@RequestParam(name = "newPassword") String newPassword, @RequestParam(name = "confirmNewPassword") String confirmNewPassword) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();

		if (currentUsername == null) {
			return "errorPage";
		}

		if (userService.changePassword(currentUsername, currentPassword, newPassword, confirmNewPassword)) {
			return "post/showAllPosts";
		} else {
			return "login/changePassword";
		}
	}

	@GetMapping("/unregister.do")
	public String unregister() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();

		if (currentUsername != null) {

			if (userService.unregisterUser(currentUsername)) {
				return "login/login";
			}
		}
		return "post/showAllposts";

	}

}
