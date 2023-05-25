package com.bkk.domain.entity;


import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (DictData)表实体类
 *
 * @author makejava
 * @since 2023-04-11 20:43:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_dict_data")
@ApiModel(value = "DictData对象", description = "字典数据表")
public class DictData implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "字典类型id")
    private Long dictId;

    @ApiModelProperty(value = "字典标签")
    private String label;

    @ApiModelProperty(value = "字典键值")
    private String value;

    @ApiModelProperty(value = "回显样式")
    private String style;

    @ApiModelProperty(value = "是否默认（1是 0否）")
    private String isDefault;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否发布(1:是，0:否)")
    private Integer isPublish;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;




}
