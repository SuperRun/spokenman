package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户学习资源
 * @author asus
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceSearchDto {
	
	@ApiModelProperty("资源id")
	private Integer resourceId;

	@ApiModelProperty("用户id")
	private Integer userId;

}
