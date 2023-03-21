package com.example.demomonna;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author： Monna
 * @CreateTime:2023-03-20 11:31:35
 * @Descrption: 需修复后的数据名称
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixParam {
    private String into_id;
    private String realCode;
    private String realChannelName;

}
