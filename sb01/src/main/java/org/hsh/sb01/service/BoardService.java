package org.hsh.sb01.service;

import org.hsh.sb01.dto.BoardDTO;
import org.hsh.sb01.dto.PageRequestDTO;
import org.hsh.sb01.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
