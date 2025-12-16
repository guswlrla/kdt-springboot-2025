package com.rubypaper.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rubypaper.board.domain.Board;
import com.rubypaper.board.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public String getBoardList(Model model, Board board) {
		Page<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList", boardList);
		return "boardList";
	}
}
