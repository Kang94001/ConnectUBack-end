package com.connectu.connectuapi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("friendship")
public class Friendship {
    @TableId
    private Integer friendshipId;
    private Integer followerId;
    private Integer followingId;
    @TableField(exist = false)
    private List a;

}

