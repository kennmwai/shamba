package com.kenm.spring.farmclientservice.dto;

import com.kenm.spring.farmclientservice.models.enums.EUserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    private Long id;
    private EUserRole name;

}
