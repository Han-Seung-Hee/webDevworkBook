package org.hsh.sb01.search;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.hsh.sb01.domain.Board;
import org.hsh.sb01.dto.BoardDTO;
import org.hsh.sb01.dto.PageRequestDTO;
import org.hsh.sb01.dto.PageResponseDTO;
import org.hsh.sb01.repository.BoardRepository;
import org.hsh.sb01.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardSearchImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    void search1() {
        //2-page order by bno desc
        Pageable pageable = PageRequest.of(2,10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t","w","c"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> rs = boardRepository.searchAll(types,keyword,pageable);

        log.info(rs.getTotalPages());

        log.info(rs.getSize());
        log.info(rs.getNumber());

        log.info(rs.hasPrevious());
        log.info(rs.hasNext());

        rs.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}