package com.ccx.entity.base;

import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Entity extends SuperEntity {

    private Date updateDate;

    private String updateMan;
}
