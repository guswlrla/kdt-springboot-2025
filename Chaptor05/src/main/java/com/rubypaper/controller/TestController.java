package com.rubypaper.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RestController
@RequestMapping("/api")
public class TestController {
	@Autowired
	private BoardRepository boardRepo;

	@GetMapping("/board")
	public List<Board> getBoards() {
		return boardRepo.findAll();
	}

	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		return boardRepo.findById(seq).get();
	}

	@PostMapping("/board")
	public Board postBoard(@RequestBody Board board) {
		board.setCnt(0L);
		board.setCreateDate(new Date());
		return boardRepo.save(board);
	}

	@PutMapping("/board/{seq}")
	public Board putBoard(@PathVariable Long seq, @RequestBody Board board) {
		Board b = boardRepo.findById(seq).get();
		board.setSeq(seq);
		board.setCnt(0L);
		board.setCreateDate(b.getCreateDate());
		return boardRepo.save(board);
	}

	@PatchMapping("/board/{seq}")
	public Board patchBoard(@PathVariable Long seq, @RequestBody Board board) {
		Board b = boardRepo.findById(seq).get();
		if(board.getTitle() != null)
			b.setTitle(board.getTitle());
		if(board.getContent() != null)
			b.setContent(board.getContent());
		if(board.getWriter() != null)
			b.setWriter(board.getWriter());
		return boardRepo.save(b);
	}

	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board b = boardRepo.findById(seq).get();
		boardRepo.delete(b);
		return b;
	}
}
