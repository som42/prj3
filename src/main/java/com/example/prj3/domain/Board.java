package com.example.prj3.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Integer id;
    private String title;
    private String body;
    private LocalDateTime inserted;
    private String writer;
}
