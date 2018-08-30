package com.zust.itee.manager.impl.training;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.TrainingDto;
import com.zust.itee.dto.TrainingResourceDto;
import com.zust.itee.dto.TrainingUserDto;
import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingUserSaveDto;
import com.zust.itee.dto.request.training.TrainingUserSearchDto;
import com.zust.itee.dto.request.training.TrainingUserUpdateDto;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.training.Training;
import com.zust.itee.entity.training.TrainingResource;
import com.zust.itee.entity.training.TrainingUser;
import com.zust.itee.entity.user.User;
import com.zust.itee.entity.user.UserResource;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.training.TrainingResourceRequiredType;
import com.zust.itee.enums.training.TrainingUserStatus;
import com.zust.itee.enums.user.UserResourceStatus;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.training.TrainingManager;
import com.zust.itee.manager.training.TrainingResourceManager;
import com.zust.itee.manager.training.TrainingUserManager;
import com.zust.itee.manager.user.UserManager;
import com.zust.itee.service.resource.LearningResourceService;
import com.zust.itee.service.training.TrainingResourceService;
import com.zust.itee.service.training.TrainingService;
import com.zust.itee.service.training.TrainingUserService;
import com.zust.itee.service.user.UserResourceService;
import com.zust.itee.service.user.UserService;
import com.zust.itee.util.FunctionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训用户 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingUserManagerImpl implements TrainingUserManager {

    @Resource
    private TrainingUserService trainingUserService;

    @Resource
    private TrainingService trainingService;

    @Resource
    private UserService userService;

    @Resource
    private TrainingManager trainingManager;

    @Resource
    private UserManager userManager;

    @Resource
    private TrainingResourceService trainingResourceService;

    @Resource
    private UserResourceService userResourceService;

    @Resource
    private TrainingResourceManager trainingResourceManager;

    @Resource
    private LearningResourceService learningResourceService;

    @Override
    public TrainingUserDto getById(Integer id) throws BusinessException {
        log.info("==获取用户培训详情== id:{}", id);
        TrainingUser trainingUser = validateNotNull(id);
        log.info("==获取用户培训详情，查库结果== trainingUser:{}", trainingUser);
        TrainingUserDto res = convertToDto(trainingUser);
        log.info("==获取用户培训详情，转换后结果== res:{}", res);
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<UserDto> listUnselectedUserBySearch(SessionInfo sessionInfo,
            Integer trainingId, UserSearchDto searchDto, PageBaseDto pageBaseDto)
            throws BusinessException {
        log.info("==搜索筛选为参加培训的用户== session:{},trainingId:{},search:{},page:{}", sessionInfo,
                trainingId, searchDto, pageBaseDto);
        User user = new User();
        BeanUtils.copyProperties(searchDto, user);
        if (UserType.COMPANY.getValue() == sessionInfo.getType()) {
            log.info("==搜索筛选未参加培训的用户，单位用户仅可选择本单位用户== session:{}", sessionInfo);
            user.setOrgId(sessionInfo.getOrgId());
        } else if (UserType.ROOT.getValue() != sessionInfo.getType()) {
            log.warn("==仅平台用户、单位用户可筛选未参加培训用户==");
            throw new BusinessException("仅平台、单位用户有此权限");
        }
        log.info("==搜索筛选未参加培训用户，筛选条件== user:{},trainingId:{},page:{}", user, trainingId,
                pageBaseDto);
        List<User> users = trainingUserService.listUnselectedUserBySearch(user, trainingId,
                pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==搜索筛选未参加培训用户，查库结果== users:{}", users);
        Long sum = trainingUserService.countUnselectedUserBySearch(user, trainingId);
        log.info("==搜索筛选未参加培训用户，查库总数== sum:{}", sum);

        pageBaseDto.setData(userManager.convertToDtoList(users));
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==搜索筛选未参加培训用户，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<TrainingUserDto> listByTraining(Integer trainingId,
            TrainingUserSearchDto searchDto, PageBaseDto pageBaseDto) throws BusinessException {
        log.info("==根据培训获取用户培训情况== trainingId:{},searchDto:{},page:{}", trainingId, searchDto,
                pageBaseDto);
        TrainingUser query = new TrainingUser();
        BeanUtils.copyProperties(searchDto, query);
        query.setTrainingId(trainingId);
        log.info("==根据培训获取用户培训情况，查询用户培训== query:{},page:{}", query, pageBaseDto);
        List<TrainingUser> trainingUsers = trainingUserService.listBySearchWithoutDeleted(query,
                pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==根据培训获取用户培训情况，查库结果== trainingUsers:{},trainingId:{},search:{},page:{}",
                trainingUsers, trainingId, searchDto, pageBaseDto);
        Long sum = trainingUserService.countBySearchWithoutDeleted(query);
        log.info("==根据培训获取用户培训情况，查库总数== sum:{}", sum);

        List<TrainingUserDto> res = convertToDtoList(trainingUsers);
        pageBaseDto.setData(res);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==根据培训获取用户培训情况，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<TrainingUserDto> listByUser(Integer userId, TrainingUserSearchDto searchDto,
            PageBaseDto pageBaseDto) throws BusinessException {
        log.info("==根据用户获取用户培训情况== trainingId:{},searchDto:{},page:{}", userId, searchDto,
                pageBaseDto);
        TrainingUser query = new TrainingUser();
        BeanUtils.copyProperties(searchDto, query);
        query.setUserId(userId);
        log.info("==根据用户获取用户培训情况，查询用户培训== query:{},page:{}", query, pageBaseDto);
        List<TrainingUser> trainingUsers = trainingUserService.listBySearchWithoutDeleted(query,
                pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==根据用户获取用户培训情况，查库结果== trainingUsers:{},userId:{},search:{},page:{}",
                trainingUsers, userId, searchDto, pageBaseDto);
        Long sum = trainingUserService.countBySearchWithoutDeleted(query);
        log.info("==根据用户获取用户培训情况，查库总数== sum:{}", sum);

        List<TrainingUserDto> res = convertToDtoList(trainingUsers);
        pageBaseDto.setData(res);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==根据用户获取用户培训情况，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public void saveTrainingUser(TrainingUserSaveDto saveDto) throws BusinessException {
        log.info("==添加用户培训记录== saveDto:{}", saveDto);
        Training training = trainingManager.validateNotNull(saveDto.getTrainingId());
        User user = userManager.validateNotNull(saveDto.getUserId());

        TrainingUser query = TrainingUser.builder()
                .trainingId(saveDto.getTrainingId())
                .userId(saveDto.getUserId())
                .build();
        List<TrainingUser> existTrainingUsers = trainingUserService.listByFilter(query, 1, 1);
        if (existTrainingUsers != null && !existTrainingUsers.isEmpty()) {
            log.info("==添加用户培训记录，用户原有培训记录== exists:{}",
                    JSONObject.toJSONString(existTrainingUsers));
            TrainingUser existTrainingUser = existTrainingUsers.get(0);
            existTrainingUser.setStatus(NormalStatusType.NORMAL.getStatus());
            trainingUserService.upsert(existTrainingUser);
            return;
        }

        TrainingUser trainingUser = TrainingUser.builder()
                .trainingId(training.getId())
                .trainingName(training.getName())
                .userId(user.getId())
                .userName(user.getName())
                .requiredScore((short) 0)
                .optionalScore((short) 0)
                .status(TrainingUserStatus.SIGNED_UP.getStatus())
                .userAreaId(user.getAreaId())
                .userLevel(user.getLevel())
                .userTypeId(user.getTypeId())
                .build();
        log.info("==添加用户培训记录，记录== trainingUser:{}", trainingUser);
        trainingUserService.upsert(trainingUser);
    }

    @Override
    public void updateTrainingUserStatus(Integer id, Short status) throws BusinessException {
        log.info("==更新用户培训记录状态== id:{},status:{}", id, status);
        Optional<TrainingUserStatus> trainingUserStatus = TrainingUserStatus.getByStatus(status);
        if (!trainingUserStatus.isPresent()) {
            throw new BusinessException("用户培训记录更改状态不合法");
        }
        TrainingUser trainingUser = validateNotNull(id);
        trainingUser.setStatus(status);
        trainingUserService.upsert(trainingUser);
    }

    @Override
    public void updateTrainingUser(Integer id, TrainingUserUpdateDto updateDto)
            throws BusinessException {
        log.info("==更新用户培训记录== id:{},update:{}", id, updateDto);
        TrainingUser trainingUser = validateNotNull(id);
        TrainingUser update = new TrainingUser();
        BeanUtils.copyProperties(updateDto, update);
        update.setId(trainingUser.getId());
        trainingUserService.upsert(update);
    }

    @Override
    public void deleteTrainingUser(Integer id) throws BusinessException {
        log.info("==删除用户培训记录== id:{}", id);
        TrainingUser trainingUser = validateNotNull(id);
        trainingUser.setStatus(TrainingUserStatus.CANCELED.getStatus());
        trainingUserService.upsert(trainingUser);
    }

    @Override
    public boolean judgeSignUp(Integer userId, Integer trainingId) throws BusinessException {
        log.info("==判断用户是否参加某培训== userId:{},trainingId:{}", userId, trainingId);
        List<TrainingUser> trainingUsers = trainingUserService.listByFilter(TrainingUser.builder()
                .trainingId(trainingId)
                .userId(userId)
                .build(), 1, 1);
        log.info("==判断用户是否参加某培训，用户培训记录== trainingUsers:{}", JSONObject.toJSONString(trainingUsers));
        if (trainingUsers != null && !trainingUsers.isEmpty()) {
            TrainingUser trainingUser = trainingUsers.get(0);
            return TrainingUserStatus.CANCELED.getStatus() != trainingUser.getStatus();
        }
        return false;
    }

    @Override
    public TrainingUserDto getUserTraining(Integer userId, Integer trainingId)
            throws BusinessException {
        log.info("==获取用户培训情况== userId:{},trainingId:{}", userId, trainingId);
        List<TrainingUser> trainingUsers = trainingUserService.listByFilter(
                TrainingUser.builder().trainingId(trainingId).userId(userId).build(), 1, 1);
        log.info("==获取用户培训情况，查库结果== trainingUsers:{}", trainingUsers);
        if (trainingUsers != null && !trainingUsers.isEmpty()) {
            TrainingUser trainingUser = trainingUsers.get(0);
            TrainingUserDto res = convertToDto(trainingUser);
            log.info("==获取用户培训情况，转换后结果== res:{}", res);
            return res;
        }
        log.info("==获取用户培训情况，库中无信息== userId:{},trainingId:{}", userId, trainingId);
        return null;
    }

    private TrainingUser validateNotNull(Integer id) {
        TrainingUser trainingUser = trainingUserService.getById(id);
        if (trainingUser == null) {
            log.warn("==用户培训记录不存在== id:{}", id);
            throw new BusinessException("用户培训记录不存在");
        }
        return trainingUser;
    }

    private TrainingUserDto convertToDto(TrainingUser trainingUser) {
        TrainingUserDto trainingUserDto = new TrainingUserDto();
        BeanUtils.copyProperties(trainingUser, trainingUserDto);
        // todo 考试成绩

        // 填充学分、进度
        fillScoreAndProcess(trainingUserDto, trainingUser);

        // 培训信息
        if (trainingUser.getTrainingId() != null) {
            Training training = trainingService.getById(trainingUser.getTrainingId());
            TrainingDto trainingDto = trainingManager.convertToDto(training);
            trainingUserDto.setTraining(trainingDto);
        }

        // 状态名称
        Optional<TrainingUserStatus> trainingUserStatusOptional = TrainingUserStatus.getByStatus(
                trainingUser.getStatus());
        // 用户信息
        User user = userService.getById(trainingUser.getUserId());
        if (user != null) {
            UserDto userDto = userManager.convertToDto(user);
            trainingUserDto.setUserDto(userDto);
        }
        trainingUserStatusOptional.ifPresent(
                trainingUserStatus -> trainingUserDto.setStatusName(trainingUserStatus.getName()));
        return trainingUserDto;
    }

    private void fillScoreAndProcess(TrainingUserDto trainingUserDto, TrainingUser trainingUser) {
        Training training = trainingManager.validateNotNull(trainingUser.getTrainingId());
        // 获取培训配置资源
        List<TrainingResource> trainingResources = trainingResourceService.listByFilter(
                TrainingResource.builder()
                        .trainingId(trainingUser.getTrainingId())
                        .build(), 1, Integer.MAX_VALUE);
        int requiredScore = 0;
        int optionalScore = 0;
        Date trainingStartTime = training.getStartTime();
        Date trainingEndTime = training.getEndTime();
        List<TrainingResourceDto> learnedResources = new LinkedList<>();
        List<TrainingResourceDto> toLearnResource = new LinkedList<>();
        List<TrainingResourceDto> resources = new LinkedList<>();
        List<TrainingResourceDto> requiredResources = new LinkedList<>();
        List<TrainingResourceDto> optionalResources = new LinkedList<>();
        Integer duration = 0;
        Integer learnedTime = 0;
        for (TrainingResource trainingResource : trainingResources) {
            TrainingResourceDto resourceDto = trainingResourceManager.convertToDto(
                    trainingResource);
            resources.add(resourceDto);
            if (TrainingResourceRequiredType.REQUIRED.getType() == resourceDto.getRequired()) {
                requiredResources.add(resourceDto);
            } else {
                optionalResources.add(resourceDto);
            }
            // 总时长
            LearningResource learningResource = learningResourceService.getById(
                    trainingResource.getResourceId());
            duration += learningResource.getDuration();
            // 获取对应用户学习资源情况
            List<UserResource> userResources = userResourceService.listByFilter(
                    UserResource.builder()
                            .userId(trainingUser.getUserId())
                            .resourceId(trainingResource.getResourceId())
                            .status(UserResourceStatus.FINISHED.getStatus())
                            .build(), 1, 1);
            if (userResources != null && !userResources.isEmpty()) {
                // 仅在培训期间学习的才可加学分
                UserResource userResource = userResources.get(0);
                if (userResource.getUpdateTime().before(trainingEndTime)
                        && userResource.getUpdateTime().after(trainingStartTime)) {
                    // 添加对应学分
                    if (TrainingResourceRequiredType.REQUIRED.getType()
                            == trainingResource.getRequired()) {
                        requiredScore += userResource.getScore();
                    } else {
                        optionalScore += userResource.getScore();
                    }
                }
                // 添加进已学习课程
                learnedResources.add(resourceDto);
                // 已学时长
                String time = userResource.getTime();
                if (StringUtils.isNotBlank(time)) {
                    try {
                        String[] times = time.split(":");
                        learnedTime += new BigDecimal(times[0]).intValue() * 3600;
                        learnedTime += new BigDecimal(times[1]).intValue() * 60;
                        learnedTime += new BigDecimal(times[2]).intValue();
                    } catch (Exception e) {
                        log.warn("==计算学习资源时长出错== userResource:{}", userResource);
                    }
                }
            } else {
                // 未学习学习资源
                toLearnResource.add(resourceDto);
            }
        }
        // 计算进度
        Double process = 0.0;
        Integer learnedHour = 0;
        Integer learnedMinute = 0;
        Integer learnedSecond = 0;
        if (learnedTime != 0) {
            process = 1.0 * learnedTime / duration * 100;

            learnedHour = learnedTime / 3600;
            learnedTime -= learnedHour * 3600;
            learnedMinute = learnedTime / 60;
            learnedTime -= learnedMinute * 60;
            learnedSecond = learnedTime;
        }
        trainingUserDto.setLearnedHour(learnedHour);
        trainingUserDto.setLearnedMinute(learnedMinute);
        trainingUserDto.setLearnedSecond(learnedSecond);
        trainingUserDto.setProcess(process.intValue());
        trainingUserDto.setDuration(duration);
        trainingUserDto.setDurationHour(FunctionUtil.getDurationHour(duration));
        trainingUserDto.setDurationMinute(FunctionUtil.getDurationMinute(duration));
        trainingUserDto.setDurationSecond(FunctionUtil.getDurationSecond(duration));
        trainingUserDto.setLearnedResources(learnedResources);
        trainingUserDto.setToLearnResources(toLearnResource);
        trainingUserDto.setTrainingResources(resources);
        trainingUserDto.setRequiredResources(requiredResources);
        trainingUserDto.setOptionalResources(optionalResources);
        trainingUserDto.setRequiredScore((short) requiredScore);
        trainingUserDto.setOptionalScore((short) optionalScore);
    }

    private List<TrainingUserDto> convertToDtoList(List<TrainingUser> trainingUsers) {
        return trainingUsers.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
