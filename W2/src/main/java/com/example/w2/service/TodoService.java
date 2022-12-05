package com.example.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import com.example.w2.dao.TodoDAO;
import com.example.w2.domain.TodoVO;
import com.example.w2.dto.TodoDTO;
import com.example.w2.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    TodoService() {
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDto) throws Exception {
        // todoDto를 변환하여 todoVO로 변경
        TodoVO todoVo = modelMapper.map(todoDto, TodoVO.class);

//        System.out.println("todoVo : " + todoVo );
        log.info(todoVo);

        todoDAO.insert(todoVo); // INT를 반환하므로 이를 이용해서 예외처리도 가능함.
    }

    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = todoDAO.selectAll();

        log.info("voList*******************************");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo-> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;

    }

    public TodoDTO selectOne(Long tno) throws Exception{
        log.info("TNO :: "+ tno);
        TodoVO todoVO = todoDAO.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;

    }

    public void remove(Long tno) throws Exception{
        log.info("tno ::: "+ tno);
        todoDAO.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception{
        log.info("todoDTO ::: " + todoDTO);

        TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);

        todoDAO.updateOne(todoVO);
    }

}
