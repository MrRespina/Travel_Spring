package com.travel.main.board;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public String list( Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "kw", defaultValue = "") String kw,
			@RequestParam(value = "index", defaultValue = "subject") String index, 
			Principal principal) {
		// page ~ 10개 board 결과x 리스트
		int pageSize = 10;
		int boardAllCnt = this.boardService.getCountBoard(index, kw);
		int totalPages = (boardAllCnt / pageSize);
		
		List<Board> boardList = this.boardService.search(page, pageSize, index, kw);
		System.out.println(kw);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("boardAllCnt", boardAllCnt);
		model.addAttribute("boardList", boardList);
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		model.addAttribute("noNavbar",true);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "board_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,
		@RequestParam(name="alert",defaultValue="") String alert,
		Principal principal) {
		
		Board board = this.boardService.getBoard(id);
		board.setVote(this.boardService.cntVote(id));
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		model.addAttribute("board", board);
		model.addAttribute("noNavbar",true);
		model.addAttribute("alert",alert);
		
		return "board_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String boardCreate(BoardForm boardForm,Principal principal,Model model) {
		model.addAttribute("noNavbar",true);
		if (principal != null) {
			model.addAttribute("r", principal.getName());
		}
		return "board_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult
			, Principal principal,Model model) {
		model.addAttribute("noNavbar",true);
		if (bindingResult.hasErrors()) {
			// 오류가 있다면 제목과 내용 작성 화면으로 이동
			return "board_form";
		}
		// 오류가 없다면 질문 등록 
		this.boardService.create(boardForm.getSubject(), boardForm.getContent(), principal);	
		return "redirect:/board/list?page=1";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String boardModify (BoardForm boardForm, @PathVariable("id") Integer id, 
			Principal principal,Model model) {
		Board board = this.boardService.getBoard(id);
		if(!board.getAuthor_id().equals(principal.getName())
				&& !principal.getName().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		boardForm.setSubject(board.getSubject());
		boardForm.setContent(board.getContent());
		model.addAttribute("noNavbar",true);
		return "board_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String boardModify (@Valid BoardForm boardForm, BindingResult bindingResult 
			,@PathVariable("id") Integer id, Principal principal,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("noNavbar",true);
			return "board_from";
		}
		
		Board board = this.boardService.getBoard(id);
		if(!board.getAuthor_id().equals(principal.getName())
				&& !principal.getName().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		this.boardService.modify(board, boardForm.getSubject(), boardForm.getContent());
		model.addAttribute("noNavbar",true);
		return String.format("redirect:/board/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String boardDelete(Principal principal, @PathVariable("id") Integer id,Model model) {
		Board board = this.boardService.getBoard(id);
		if(!board.getAuthor_id().equals(principal.getName())
				&& !principal.getName().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		this.boardService.delete(board);
		model.addAttribute("noNavbar",true);
		return "redirect:/board/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String boardVote(@PathVariable("id") Integer id,
			Principal principal,Model model) {
		model.addAttribute("noNavbar",true);
		if (this.boardService.getBoardVoteById(id, principal) == null) {
			this.boardService.vote(id, principal);
			this.boardService.setCntVote(id);
			return String.format("redirect:/board/detail/%s?alert=%s", id, URLEncoder.encode("추천 완료!", StandardCharsets.UTF_8)) ;
		}
		return String.format("redirect:/board/detail/%s?alert=%s", id, URLEncoder.encode("이미 추천한 대상입니다!", StandardCharsets.UTF_8));
		
	}
	
}
