package com.example.prj3.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Board {
    private Integer id;
    private String title;
    private String body;
    private LocalDateTime inserted;
    private String writer;
    private List<String> fileName;
    private Integer fileCount;
}
