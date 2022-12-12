package com.example.spdiexample.dto;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class todoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;

}
