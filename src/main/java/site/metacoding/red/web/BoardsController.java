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
import site.metacoding.red.domain.boards.mapper.MainView;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsDao boardsDao;
	private final HttpSession session;

	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")

	//http://localhost:8000/?page=1
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page) {//사용자
		//model.addAttribute("boardsList", boardsDao.findAll());
		if(page==null) page = 0;
		int startNum = page * 3;
		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto paging = boardsDao.paging(page);
		
		final int blockCount = 5;
		
		int currentBlock = page/blockCount;
		int startPageNum = 1 + blockCount *currentBlock;
		int lastPageNum = 5 + blockCount *currentBlock;
		paging.setLastPageNum(paging.getBlockCount());
		if(paging.getTotalPage() < lastPageNum) {
			lastPageNum = paging.getTotalPage();
		}
		paging.setBlockCount(blockCount);
		paging.setCurrentBlock(currentBlock);
		paging.setStartPageNum(startPageNum);
		paging.setLastPageNum(lastPageNum);
		
		model.addAttribute("boardsList",boardsList);
		model.addAttribute("paging",paging);
		return "boards/main";
	}


	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		
		model.addAttribute("boardsContent",boardsDao.findById(id));
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		// 주소로 강제로 접근하는 것을 막기 위함
//		if (principal != null) {
//			return "boards/writeForm";
//		} else { // userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
//			return "redirect:/";
//		}
		if (principal == null) {	// userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
			return "redirect:/";
		}

		return "boards/writeForm";
	}

	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		// Users userPS= (Users) session.getAttribute("principal");
		// writeDto.setId(userPS.getId());

		
		// 1번 세션에 접근해서 세션값을 확인한다. 그 때 Users로 다운캐스팅하고 키값은 principal로 한다
		
		// 2번 principal null 인지 확인하고, null이면 loginForm 리다이렉션 해준다.
		
		// 3번 BoardsDao 접근해서 insert 메서드를 호출한다.
		// 조건 : dto 를 entity로 변환해서 인수로 담아준다.
		// 조건 : entitiy에는 세션의 principal에 getId가 필요하다.
		
		Users principal = (Users) session.getAttribute("principal");
		
		if(principal == null ) {
			return "redirect:/";
		}
		
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	
		return "redirect:/";
	}

}
