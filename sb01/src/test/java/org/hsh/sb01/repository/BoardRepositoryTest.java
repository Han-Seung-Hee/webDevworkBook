package org.hsh.sb01.repository;

import lombok.extern.log4j.Log4j2;
import org.hsh.sb01.domain.Board;
import org.hsh.sb01.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInput(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title...." + i)
                    .content("content.... "+ i)
                    .writer("user0"+i)
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO : " + result.getBno());
        });


    }

    @Test
    public void testSelect(){
        Long bno = 100L;

        Optional<Board> rs = boardRepository.findById(bno);

        Board board = rs.orElseThrow();

        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        log.info("original title ::: " + board.getTitle());
        log.info("original content ::: " + board.getContent());

        board.change("update22...title 100", "update22...content 100");
        boardRepository.save(board);

        result = boardRepository.findById(bno);
        board = result.orElseThrow();
        log.info("updated title ::: " + board.getTitle());
        log.info("updated content ::: " + board.getContent());

    }

    @Test
    public void testDelete(){
        Board board = Board.builder()
                .title("title....101")
                .content("content....101 ")
                .writer("user0101")
                .build();

        Board result = boardRepository.save(board);

        Long bno = result.getBno();

        boardRepository.deleteById(bno);

    }

    @Test
    public void testPage(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total pages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size  : " + result.getSize());

        List<Board> brList = result.getContent();

        brList.forEach(board -> log.info(board));
    }
}