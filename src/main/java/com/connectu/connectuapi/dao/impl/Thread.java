package com.connectu.connectuapi.dao.impl;
import lombok.Data;
@Data
public class Thread  {
    private Integer threadId;
    private Integer categoryId;
    private Integer userId ;
    private String title;
    private String content;
    private String createdAt;
    private Object picture;

}
