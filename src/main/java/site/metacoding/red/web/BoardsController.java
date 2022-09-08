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
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsDao boardsDao;
	private final HttpSession session;
	
	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsDao.findById(id); //영속화
		Users principal = (Users) session.getAttribute("principal");

		if (boardsPS == null) { //있는글인지
			return "errors/badpage";
		}
		if (principal == null) { //로그인 되어있는지
			return "redirect:/loginForm";
		}
		if (boardsPS.getUsersId() != principal.getId()) { //본인의 글이 맞는지
			return "errors/badpage";
		}
		model.addAttribute("boards", boardsPS);
		return "boards/updateForm";
	}

	@PostMapping("/boards/{id}/update")
	public String updateBoards(@PathVariable Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id); //영속화
		Users principal = (Users) session.getAttribute("principal");

		if (boardsPS == null) { //있는글인지
			return "errors/badpage";
		}
		if (principal == null) { //로그인 되어있는지
			return "redirect:/loginForm";
		}
		if (boardsPS.getUsersId() != principal.getId()) { //본인의 글이 맞는지
			return "errors/badpage";
		}
		
		boardsPS.글수정(updateDto);
		
		boardsDao.update(boardsPS);
		return "redirect:/boards/"+id;
	}

	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");

		if (boardsPS == null) { //있는글인지
			return "errors/badpage";
		}
		if (principal == null) { //로그인 되어있는지
			return "redirect:/loginForm";
		}
		if (boardsPS.getUsersId() != principal.getId()) { //본인의 글이 맞는지
			return "errors/badpage";
		}
		boardsDao.delete(id); //핵심 로직
		return "redirect:/";
	}

	// ﻿http://localhost:8000/?page=0&keyword=스프링  으로 검색할 때
	// http://localhost:8000/?page=1
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page, String keyword) {// 사용자
		if (page == null)
			page = 0;
		int startNum = page * 3;
		

		if(keyword == null || keyword.isEmpty()) {
			List<MainDto> boardsList = boardsDao.findAll(startNum);
			PagingDto paging = boardsDao.paging(page, null);

			paging.blockPoint(page);
			
			System.out.println(paging.getTotalPage());
			model.addAttribute("boardsList", boardsList);
			model.addAttribute("paging", paging);
			
		}else {
			List<MainDto> boardsList = boardsDao.findSearch(startNum, keyword);
			
			PagingDto paging = boardsDao.paging(page, keyword);
			
//			System.out.println("==============================");
//			paging.setKeyword(keyword);
//			System.out.println("메서드 없느 키워드"+keyword);
//			System.out.println("키워드 "+paging.getKeyword());
//			System.out.println("==============================");
			paging.blockPoint(page);
			
			paging.setKeyword(keyword);
			System.out.println(paging.getTotalPage());
			model.addAttribute("boardsList", boardsList);
			model.addAttribute("paging", paging);
		}
		// model.addAttribute("boardsList", boardsDao.findAll());
		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		model.addAttribute("boardsContent", boardsDao.findById(id));
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
		if (principal == null) { // userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
			return "redirect:/";
		}

		return "boards/writeForm";
	}

	@PostMapping("/boards/write")
	public String writeBoards(WriteDto writeDto) {
		// Users userPS= (Users) session.getAttribute("principal");
		// writeDto.setId(userPS.getId());

		// 1번 세션에 접근해서 세션값을 확인한다. 그 때 Users로 다운캐스팅하고 키값은 principal로 한다

		// 2번 principal null 인지 확인하고, null이면 loginForm 리다이렉션 해준다.

		// 3번 BoardsDao 접근해서 insert 메서드를 호출한다.
		// 조건 : dto 를 entity로 변환해서 인수로 담아준다.
		// 조건 : entitiy에는 세션의 principal에 getId가 필요하다.

		Users principal = (Users) session.getAttribute("principal");

		if (principal == null) {
			return "redirect:/";
		}

		boardsDao.insert(writeDto.toEntity(principal.getId()));

		return "redirect:/";
	}

}
