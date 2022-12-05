package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.w2.dao.TodoDAO;
import com.example.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTest {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception{
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void insertTest() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .title("INSERTION TEST")
                .dueDate(LocalDate.of(2022,11,01))
                .build();

        todoDAO.insert(todoVO);

    }

    @Test
    public void selectAllTest() throws Exception{
        List<TodoVO> ls = todoDAO.selectAll();

        ls.forEach(System.out::println);
    }

    @Test
    public void selectOneTest() throws Exception{
        Long tno = 1L;
        TodoVO vo = todoDAO.selectOne(tno);
        System.out.println(vo);
    }

    @Test
    public void updateTest() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("updated test")
                .finished(true)
                .dueDate(LocalDate.now())
                .build();

        todoDAO.updateOne(todoVO);
        System.out.println(todoDAO.selectOne(1L));
    }
}
