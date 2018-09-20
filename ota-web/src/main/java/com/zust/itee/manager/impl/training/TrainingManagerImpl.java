package com.zust.itee.manager.impl.training;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.TrainingDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.request.training.TrainingSaveDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.dto.request.training.TrainingUpdateDto;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.training.Training;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.enums.training.TrainingStatus;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.service.ExamBaseService;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.org.OrganizationManager;
import com.zust.itee.manager.training.TrainingManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;
import com.zust.itee.service.org.OrganizationService;
import com.zust.itee.service.training.TrainingService;
import com.zust.itee.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 培训 manager 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class TrainingManagerImpl implements TrainingManager {

    @Resource
    private TrainingService trainingService;

    @Resource
    private UserService userService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Resource
    private ExamBaseService examBaseService;

    @Resource
    private OrganizationManager organizationManager;

    @Override
    public void saveTraining(Integer userId, Integer orgId, TrainingSaveDto trainingSaveDto) {
        log.info("==创建培训== userId:{},orgId:{},saveDto:{}", userId, orgId, trainingSaveDto);
        Training training = new Training();
        BeanUtils.copyProperties(trainingSaveDto, training);
        training.setUserId(userId);
        User user = userService.getById(userId);
        training.setUserName(user.getName());
        training.setOrgId(orgId);
        training.setStatus(trainingService.judgeTrainingStatus(training));
        log.info("==创建培训，转换后培训== training:{}", training);
        trainingService.upsert(training);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<TrainingDto> listBySearch(Integer userId, Short userType, Integer orgId,
            TrainingSearchDto searchDto, PageBaseDto pageBaseDto) throws BusinessException {
        log.info("==搜索培训== userId:{},orgId:{},search:{},page:{}", userId, orgId, searchDto,
                pageBaseDto);
        Training training = new Training();
        BeanUtils.copyProperties(searchDto, training);
        if (UserType.ROOT.getValue() != userType) {
            // 非平台管理员仅能查看本组织培训
            training.setOrgId(orgId);
        }
        Organization root = organizationManager.getRootOrg();
        if (root.getId().equals(orgId)) {
            training.setOrgId(null);
        }
        DateFilter dateFilter = DateFilter.builder()
                .startTime(training.getStartTime())
                .endTime(training.getEndTime())
                .build();

        log.info("==搜索培训，转换后查询== search:{},date:{},page:{}", training, dateFilter, pageBaseDto);
        List<Training> trainings = trainingService.listBySearch(training, dateFilter,
                pageBaseDto.getPage(), pageBaseDto.getRows());
        log.info("==搜索培训，查库结果== trainings:{}", trainings);
        Long sum = trainingService.countBySearch(training, dateFilter);
        log.info("==搜索培训，查库总数== sum:{}", sum);

        List<TrainingDto> res = convertToDtoList(trainings);
        pageBaseDto.setData(res);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==搜索用户，转换后结果== res:{}", pageBaseDto);
        return pageBaseDto;
    }

    @Override
    public TrainingDto getById(Integer id) throws BusinessException {
        log.info("==查询培训详情== id:{}", id);
        Training training = validateNotNull(id);
        log.info("==查询培训详情，查库结果== training:{}", training);
        TrainingDto res = convertToDto(training);
        log.info("==查询培训详情，转换后结果== res:{}", res);
        return res;
    }

    @Override
    public void updateTraining(Integer id, TrainingUpdateDto updateDto) throws BusinessException {
        log.info("==更新培训== id:{},update:{}", id, updateDto);
        Training training = validateNotNull(id);
        BeanUtils.copyProperties(updateDto, training);
        training.setId(id);
        log.info("==更新培训，更新内容== update:{}", training);
        trainingService.upsert(training);
        // 更新新状态
        training = validateNotNull(id);
        Short status = trainingService.judgeTrainingStatus(training);
        training.setStatus(status);
        trainingService.upsert(training);
    }

    @Override
    public void deleteTraining(Integer id) throws BusinessException {
        log.info("==删除培训== id:{}", id);
        Training training = validateNotNull(id);
        training.setStatus(TrainingStatus.CANCELED.getStatus());
        trainingService.upsert(training);
    }

    @Override
    public Training validateNotNull(Integer id) {
        Training training = trainingService.getById(id);
        if (training == null) {
            throw new BusinessException(String.format("培训不存在,id:%s", id));
        }
        return training;
    }

    @Override
    public TrainingDto convertToDto(Training training) {
        TrainingDto trainingDto = new TrainingDto();
        BeanUtils.copyProperties(training, trainingDto);

        // 发布人组织
        Organization organization = organizationService.getById(training.getOrgId());
        if (organization != null) {
            trainingDto.setOrgName(organization.getName());
        }

        // 发布人姓名
        User user = userService.getById(training.getUserId());
        if (user != null) {
            trainingDto.setUserName(user.getName());
        }

        // 培训等级名称
        OrgLevelType orgLevelType = OrgLevelType.getByLevel(training.getLevel());
        if (orgLevelType != null) {
            trainingDto.setLevelName(orgLevelType.getName());
        }

        // 条线
        if (training.getTypeId() != null) {
            DataDictionary dataDictionary = dataDictionaryService.getById(training.getTypeId());
            trainingDto.setTypeName(dataDictionary.getValueStr());
        }

        // 状态名称
        Optional<TrainingStatus> trainingStatusOption = TrainingStatus.getByStatus(
                training.getStatus());
        trainingStatusOption.ifPresent(status -> trainingDto.setStatusName(status.getName()));

        return trainingDto;
    }

    private List<TrainingDto> convertToDtoList(List<Training> trainings) {
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
