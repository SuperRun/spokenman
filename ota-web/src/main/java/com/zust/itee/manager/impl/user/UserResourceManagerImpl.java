package com.zust.itee.manager.impl.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.UserResourceDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.user.UserResourceSaveDto;
import com.zust.itee.dto.request.user.UserResourceSearchDto;
import com.zust.itee.dto.request.user.UserResourceUpdateDto;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.user.UserResource;
import com.zust.itee.enums.resource.LearningResourceType;
import com.zust.itee.enums.user.UserResourceStatus;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.user.UserResourceManager;
import com.zust.itee.service.resource.LearningResourceService;
import com.zust.itee.service.user.UserResourceService;
import com.zust.itee.service.user.UserService;
import com.zust.itee.util.FunctionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserResourceManagerImpl implements UserResourceManager {

    @Resource
    private UserResourceService userResourceService;

    @Resource
    private LearningResourceService learningResourceService;

    @Resource
    private UserService userService;

    @Override
    public PageBaseDto<UserResourceDto> listUserResources(Integer userId, PageBaseDto pageBaseDto)
            throws BusinessException {
        log.info("==获取用户所有的学习资源== userId:{},pageBaseDto:{}", userId, pageBaseDto);
        List<UserResource> userResources = userResourceService.listByFilter(
                UserResource.builder()
                        .userId(userId)
                        .build(), pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==获取用户所有的学习资源，获取结果== userResources:{}", userResources);
        Long sum = userResourceService.countByFilter(
                UserResource.builder()
                        .userId(userId)
                        .build());
        pageBaseDto.setSumAndPageNum(sum);
        List<UserResourceDto> userResourceDtos = convertToDtoList(userResources);
        pageBaseDto.setData(userResourceDtos);
        log.info("==搜索用户，转换后结果== pageBaseDto:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public UserResourceDto getUserResourceById(Integer id) {
        log.info("==获取用户学习资源==id：{}", id);
        UserResource userResource = userResourceService.getById(id);
        log.info("==获取用户学习资源,获取结果==userResource：{}", userResource);
        UserResourceDto userResourceDto = convertToDto(userResource);
        log.info("==获取用户学习资源，转换后结果== userResourceDto:{}", userResourceDto);
        return userResourceDto;
    }

    @Override
    public void saveUserResource(SessionInfo sessionInfo, UserResourceSaveDto userResourceSaveDto) {
        log.info("==用户添加学习资源== session:{},save:{}", sessionInfo, userResourceSaveDto);
        UserResource userResource = convertToEntity(userResourceSaveDto, sessionInfo);
        log.info("==用户添加学习资源，转换后实体== userResource:{}", userResource);
        userResourceService.upsert(userResource);
    }

    @Override
    public void deleteUserResource(Integer id) throws BusinessException {
        log.info("==用户删除学习资源== id:{}", id);
        UserResource userResource = validateNotNull(id);
        userResource.setStatus(null);
        userResourceService.upsert(userResource);
    }

    @Override
    public void updateUserResource(Integer id, UserResourceUpdateDto userResourceUpdateDto)
            throws BusinessException {
        log.info("==更新用户学习资源== id:{},update:{}", id, userResourceUpdateDto);
        validateNotNull(id);
        UserResource userResource = convertToEntity(userResourceUpdateDto, id);
        log.info("==更新用户学习资源，转换后实体== id:{},update:{}", id, userResourceUpdateDto);
        userResourceService.upsert(userResource);
    }

    @Override
    public PageBaseDto<UserResourceDto> listUserResourceBySearch(
            UserResourceSearchDto userResourceSearchDto,
            PageBaseDto pageBaseDto) throws RuntimeException {
        // TODO Auto-generated method stub
        log.info("==搜索用户学习资源== userResourceSearchDto:{},pageBaseDto:{}", userResourceSearchDto,
                pageBaseDto);
        UserResource userResource = new UserResource();
        BeanUtils.copyProperties(userResourceSearchDto, userResource);
        log.info("==搜索用户学习资源，转换后参数== userResource:{}", userResource);
        List<UserResource> res = userResourceService.listByFilter(userResource,
                pageBaseDto.getPage()
                , pageBaseDto.getRows());
        log.info("==搜索用户，数据库查询结果== res:{}", res);
        Long sum = userResourceService.countByFilter(userResource);
        log.info("==搜索用户，数据库查询总数== sum:{}", sum);
        List<UserResourceDto> userResourceDtos = convertToDtoList(res);
        pageBaseDto.setData(userResourceDtos);
        return pageBaseDto;
    }

    @Override
    public UserResourceDto getUserResource(Integer userId, Integer resourceId) {
        log.info("==获取用户学习资源== userId:{},resourceId:{}", userId, resourceId);
        List<UserResource> res = userResourceService.listByFilter(
                UserResource.builder()
                        .userId(userId)
                        .resourceId(resourceId)
                        .build(), 1, 1);
        log.info("==获取用户学习资源，数据库查询结果== res:{}", res);
        if (res == null || res.isEmpty()) {
            log.info("==获取用户学习资源，用户未学习过该资源，响应默认值==");
            return convertToDto(userId, resourceId);
        }
        UserResourceDto userResourceDto = convertToDtoList(res).get(0);
        log.info("==获取用户学习资源，数据库查询结果,转换后== userResourceDto:{}", userResourceDto);
        return userResourceDto;
    }

    private UserResource validateNotNull(Integer id) {
        UserResource userResource = userResourceService.getById(id);
        if (userResource == null) {
            throw new BusinessException(String.format("用户没有添加该学习资源,id:%s", id));
        }
        return userResource;
    }

    private UserResourceDto convertToDto(Integer userId, Integer resourceId) {
        UserResourceDto userResourceDto = UserResourceDto.undefine();
        userResourceDto.setUserId(userId);
        LearningResource learningResource = learningResourceService.getById(resourceId);
        if (learningResource != null) {
            userResourceDto.setName(learningResource.getName());
            userResourceDto.setType(learningResource.getType());
            Optional<LearningResourceType> typeOptional = LearningResourceType.getByType(
                    learningResource.getType());
            typeOptional.ifPresent(type -> userResourceDto.setTypeName(type.getName()));
            userResourceDto.setUrl(learningResource.getUrl());
            userResourceDto.setDescription(learningResource.getDescription());
            Integer duration = learningResource.getDuration();
            userResourceDto.setDuration(duration);
            userResourceDto.setDurationHour(FunctionUtil.getDurationHour(duration));
            userResourceDto.setDurationMinute(FunctionUtil.getDurationMinute(duration));
            userResourceDto.setDurationSecond(FunctionUtil.getDurationSecond(duration));
            userResourceDto.setPic(learningResource.getPic());
        }
        return userResourceDto;
    }

    private UserResourceDto convertToDto(UserResource userLearningResource) {
        UserResourceDto userLearningResourceDto = new UserResourceDto();
        BeanUtils.copyProperties(userLearningResource, userLearningResourceDto);

        // TODO 获取用户资源信息

        // 状态
        Optional<UserResourceStatus> userLearningResourceStatus = UserResourceStatus.getByStatus(
                userLearningResource.getStatus());
        userLearningResourceStatus.ifPresent(
                status -> userLearningResourceDto.setStatusName(status.getDescription()));

        // 填充资源信息
        LearningResource learningResource = learningResourceService.getById(
                userLearningResource.getResourceId());

        if (learningResource != null) {
            userLearningResourceDto.setDescription(learningResource.getDescription());
            userLearningResourceDto.setName(learningResource.getName());
            Integer duration = learningResource.getDuration();
            userLearningResourceDto.setDuration(duration);
            userLearningResourceDto.setDurationHour(FunctionUtil.getDurationHour(duration));
            userLearningResourceDto.setDurationMinute(FunctionUtil.getDurationMinute(duration));
            userLearningResourceDto.setDurationSecond(FunctionUtil.getDurationSecond(duration));
            // 资源类型
            Optional<LearningResourceType> resourceType = LearningResourceType.getByType(
                    learningResource.getType());
            resourceType.ifPresent(type -> userLearningResourceDto.setTypeName(type.getName()));

            userLearningResourceDto.setUrl(learningResource.getUrl());
            userLearningResourceDto.setPic(learningResource.getPic());
        }

        // 观看进度
        String time = userLearningResource.getTime();
        Integer processTime = 0;
        Integer learnedHour = 0;
        Integer learnedMinute = 0;
        Integer learnedSecond = 0;
        if (StringUtils.isNotBlank(time)) {
            String[] times = time.split(":");
            try {
                learnedHour = new BigDecimal(times[0]).intValue();
                learnedMinute = new BigDecimal(times[1]).intValue();
                learnedSecond = new BigDecimal(times[2]).intValue();
                processTime += learnedHour * 3600;
                processTime += learnedMinute * 60;
                processTime += learnedSecond;
            } catch (Exception e) {
                log.warn("==已观看时间计算出错== userResource:{}", userLearningResource);
            }
        }
        Double process = 0.0;
        if (processTime != 0) {
            process = 1.0 * processTime / learningResource.getDuration() * 100;
        }
        userLearningResourceDto.setProcess(process.intValue());
        userLearningResourceDto.setLearnedHour(learnedHour);
        userLearningResourceDto.setLearnedMinute(learnedMinute);
        userLearningResourceDto.setLearnedSecond(learnedSecond);

        return userLearningResourceDto;
    }

    private List<UserResourceDto> convertToDtoList(List<UserResource> userResources) {
        return userResources.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserResource convertToEntity(UserResourceSaveDto saveDto,
            SessionInfo sessionInfo) {
        UserResource userResource = new UserResource();
        BeanUtils.copyProperties(saveDto, userResource);
        userResource.setUserId(sessionInfo.getOtaUserId());
        userResource.setStatus(UserResourceStatus.STUDYING.getStatus());
        userResource.setTime("0");
        return userResource;
    }

    private UserResource convertToEntity(UserResourceUpdateDto updateDto, Integer id) {
        UserResource userResource = new UserResource();
        BeanUtils.copyProperties(updateDto, userResource);
        userResource.setId(id);
        userResource.setUpdateTime(new Date());
        return userResource;
    }
}
