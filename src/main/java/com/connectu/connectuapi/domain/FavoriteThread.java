package com.connectu.connectuapi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("favoriteThread")

public class FavoriteThread {
    @TableId
    private Integer favoriteThreadId;
    private Integer userId;
    private Integer threadId;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private DyThread dyThread;
    @TableField(exist = false)
    private dyThreadHashtag dyThreadHashtag;
    @TableField(exist = false)
    private DyHashtag dyHashtag;

}
