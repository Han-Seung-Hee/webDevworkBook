package org.hsh.sb01.search;

import org.hsh.sb01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Paging
public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
