package com.ccx.entity.biz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ccx.entity.base.Entity;
import com.ccx.entity.base.SuperEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_user")
@ToString
@EqualsAndHashCode(callSuper = true)
public class AppUserDO extends Entity {

    @NotBlank(message = "昵称必须填写",groups = SuperEntity.Save.class)
    private String nickName;

    private String headImage;

    @NotBlank(message = "手机号码必填",groups = SuperEntity.Save.class)
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "手机格式错误",groups = SuperEntity.Save.class)
    private String phone;

    private String email;

    private String openId;

    /**
     * 0 false
     * 1 true
     */
    private Short isVip;

    private Boolean enable;

    /**
     * 注册的时候 验证
     */
    @TableField(exist = false)
    @NotBlank(message = "请输入验证码",groups = SuperEntity.Save.class)
    private String code;


}
