package com.example.w2.dto;

import lombok.*;

import java.time.LocalDate;

// 계층간 자료 전달을 위한 객체임
// VO는 DB에서 받아온 자료를 Entity 별로 들고오는 것이고
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
