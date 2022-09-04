package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	private final UsersDao usersDao;
	private final HttpSession session; //스프링에서 서버시작하자마자 IoC 컨테이너에 보관함
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate(); //세션에 있는걸 날려버리는거
		return "redirect:/"; //로그아웃하면 메인페이지로
	}
	
	@PostMapping("/login")
	public String login(LoginDto loginDto){
		Users usersPS = usersDao.login(loginDto);
		
		if(usersPS != null) { //로그인이 인증 됐음 ( 쿼리 내용이 그럼 )
			session.setAttribute("principal", usersPS); //principal : 인증됐다는 용어
			return "redirect:/"; // "/" 는 boards/main.jsp
		}else {
			return "redirect:/loginForm"; //로그인 잘 안되서 다시 로그인하라고
		}
			
		
	}
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm"; //회원가입이 완료되면 이동할 페이지
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
}
