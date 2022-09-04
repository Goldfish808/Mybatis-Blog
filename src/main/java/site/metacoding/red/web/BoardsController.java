package site.metacoding.red.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.mapper.BoardsOrm;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;

@RequiredArgsConstructor	
@Controller
public class BoardsController {
	
	private final BoardsDao boardsDao;
	private final HttpSession session;
	
	//@PostMapping("/boards/{id}/delete")
	//@PostMapping("/boards/{id}/update")

	@GetMapping({"/","/boards"})
	public String getBoardList(Model model) {
		model.addAttribute("boardsList", boardsDao.findAll());
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id) {
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users userPS= (Users) session.getAttribute("principal");
		//주소로 강제로 접근하는 것을 막기 위함
		if(userPS != null) {
			return "boards/writeForm";	
		}else { //userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
			return "redirect:/";
		}
	}
	
	@PostMapping("/boards/insert")
	public String writeForm(WriteDto writeDto) {
		Users userPS= (Users) session.getAttribute("principal");
		writeDto.setId(userPS.getId());
		boardsDao.insert(writeDto);
		return "redirect:/";
	}
	
	
}
