package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户增加一个学习资源
 * @author asus
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceSaveDto {
	
	private Integer resourceId;


}
