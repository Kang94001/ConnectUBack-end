package com.connectu.connectuapi.dao.impl;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("dyThread")
public class DyThread {
    private Integer threadId;
    private Integer userId ;
    private String title;
    private String content;
    private String createdAt;
    private Object picture;

}
