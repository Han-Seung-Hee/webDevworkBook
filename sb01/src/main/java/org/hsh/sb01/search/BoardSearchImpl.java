package org.hsh.sb01.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.hsh.sb01.domain.Board;
import org.hsh.sb01.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board); // select ... from board

        BooleanBuilder booleanBuilder = new BooleanBuilder();

/*
        booleanBuilder.or(board.title.contains("11"));

        booleanBuilder.or(board.content.contains("11"));

        query.where(booleanBuilder);

        query.where(board.bno.gt(0L));
*/

        query.where(board.title.contains("1")); // where title like

        //Paging START
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> ls = query.fetch();

        long count = query.fetchCount();

        return null;

    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length >0 ) && keyword != null ){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){
                switch (type) {
                    case "t" -> booleanBuilder.or(board.title.contains(keyword));
                    case "c" -> booleanBuilder.or(board.content.contains(keyword));
                    case "w" -> booleanBuilder.or(board.writer.contains(keyword));
                }
            }

            query.where(booleanBuilder);
        }

        query.where(board.bno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> ls = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(ls,pageable,count);

    }


}
