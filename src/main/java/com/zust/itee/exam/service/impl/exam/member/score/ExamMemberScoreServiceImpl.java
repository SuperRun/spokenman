package com.zust.itee.exam.service.impl.exam.member.score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.enums.OutputFilePathEnmu;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.exam.member.score.ExamMemberScoreService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;
import com.zust.itee.exam.util.ExcelUtil;
import com.zust.itee.exam.util.UploadPathUtil;

@Service
@Transactional
public class ExamMemberScoreServiceImpl extends BaseServiceImpl<Texam>
        implements ExamMemberScoreService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<Tmember> memberDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Override
    public List<DriverInfoInExamManagement> getScoreDrivers(Integer examId,
            PageBaseDto pageBaseDto) throws RuntimeException {
        List<DriverInfoInExamManagement> driverInfos = new ArrayList<DriverInfoInExamManagement>();
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId " +
                    /*
                     * 添加搜索条件
					 */
                    "and sdy.status<>:status " +
                    // 驾驶员姓名
                    "and (sdy.tdriver.name like:key " +
                    // 驾驶员联系方式
                    "or sdy.tdriver.mobile like:key " +
                    // 车企名称
                    "or sdy.tdriver.torganization.name like:key " +
                    // 散装办名称
                    "or sdy.tdriver.torganization.torganization.name like:key) ";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("examId", examId);
            params.put("key", key);
            // 获取entity
            List<TexamSignup> texamSignups = examSignupDao.find(hql, params);
            // 转化为dto
            driverInfos = transTexamSignupToSignupDriverInfo(texamSignups);
			/*
			 * 修改考试状态
			 */
            hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            texam.setStatus(ExamStatusEnum.MARKING.getState());
            examDao.saveOrUpdate(texam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return driverInfos;
    }

    @Override
    public String createScoreDriversExcel(HttpServletRequest request,
            Integer examId) throws RuntimeException {
        try {
			/*
			 * 获取该场考试所有考生信息
			 */
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            List<TexamSignup> texamSignups = examSignupDao.find(hql, params);
            List<DriverInfoInExamManagement> driverInfos = transTexamSignupToSignupDriverInfo(
                    texamSignups);

            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("成绩录入表");
            sheet.setDefaultColumnWidth(22);
            sheet.setDefaultRowHeight((short) 400);
            // 创建单元格居中格式
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 创建第一行
            HSSFRow row = sheet.createRow((int) 0);
			/*
			 * 填写第一行表头信息
			 */
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("编号");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue("驾驶员姓名");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("联系方式");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("所属考生办");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("所属考试办");
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue("成绩");
            cell.setCellStyle(style);

			/*
			 * 写入实际数据
			 */
            for (int i = 0; i < driverInfos.size(); i++) {
                // 创建新的一行
                row = sheet.createRow(i + 1);
                // 获取数据
                DriverInfoInExamManagement driverInfo = driverInfos.get(i);
				/*
				 * 新建列，设置值
				 */
                // 编号
                row.createCell(0).setCellValue(driverInfo.getDriverId());
                // 姓名
                row.createCell(1).setCellValue(driverInfo.getName());
                // 联系方式
                row.createCell(2).setCellValue(driverInfo.getTel());
                // 车企
                row.createCell(3).setCellValue(driverInfo.getOrgName());
                // 散装办
                row.createCell(4).setCellValue(driverInfo.getOrgParentName());
                // 成绩
                if (driverInfo.getScore() != null) {
                    row.createCell(5).setCellValue(driverInfo.getScore());
                }
            }

			/*
			 * 将文件存到指定位置
			 */
            // 创建文件夹
            String savePath = UploadPathUtil.getWebRoot(request);
            File file = new File(savePath
                    + OutputFilePathEnmu.SCOREEXCEL.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(savePath
                    + OutputFilePathEnmu.SCOREEXCEL.getPath() + examId + ".xls");

            wb.write(fout);
            logger.info(file.getAbsolutePath());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return OutputFilePathEnmu.SCOREEXCEL.getPath() + examId + ".xls";
    }

    @Override
    public PageBaseDto getScorePageBaseDto(Integer examId,
            PageBaseDto pageBaseDto) throws RuntimeException {
        try {
            String hql = "select count(*) from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId " +
					/*
					 * 添加搜索条件
					 */
                    "and sdy.status<>:status " +
                    // 驾驶员姓名
                    "and (sdy.tdriver.name like:key " +
                    // 驾驶员联系方式
                    "or sdy.tdriver.mobile like:key " +
                    // 车企名称
                    "or sdy.tdriver.torganization.name like:key " +
                    // 散装办名称
                    "or sdy.tdriver.torganization.torganization.name like:key) ";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("examId", examId);
            params.put("key", key);
            // 获取entity
            Long sum = examSignupDao.count(hql, params);
            pageBaseDto.setSum(sum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean scoreOneDriver(Integer examId, Integer driverId, Double score)
            throws RuntimeException {
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            params.put("driverId", driverId);
            TexamSignup texamSignup = examSignupDao.get(hql, params);
            if (texamSignup.getStatus() == DriverExamEnum.SCORED.getStatus()) {
                // 已提交分数，不可再次修改
                return false;
            }
            texamSignup.setFinalScore(score);
            //设置驾驶员参加考试时间为考试开始时间
            texamSignup.setTakeExamTime(texamSignup.getTexam().getExamStartTime());
            examSignupDao.saveOrUpdate(texamSignup);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean scoreAllDrivers(File file, Integer examId)
            throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            if (exam == null
                    || exam.getStatus() != ExamStatusEnum.MARKING.getState()) {
                // 考试不在成绩录入状态
                return false;
            }
            InputStream inputStream = new FileInputStream(file);
            // 将excel读取成二维数组
            HSSFCell[][] scoreInfo = ExcelUtil.transExcel(inputStream, "成绩录入表");
			/*
			 * 遍历二维数组录入成绩
			 */
            logger.info("excel有=" + scoreInfo.length + "=条数据");
            for (int i = 0; i < scoreInfo.length; i++) {
                // 获取驾驶员id
                String driverIdStr = scoreInfo[i][0].getStringCellValue();
                Integer driverId;
                try {
                    driverId = Integer.parseInt(driverIdStr);
                } catch (NumberFormatException e) {
                    // 驾驶员id被修改成非法格式，继续读下一条
                    continue;
                }

                if (scoreInfo[i][5] == null) {
                    //如果没有输入分数跳过
                    continue;
                }

                // 获取分数
                String scoreStr = scoreInfo[i][5].getStringCellValue();
                Double score;
                try {
                    score = Double.parseDouble(scoreStr);
                } catch (NumberFormatException e) {
                    // 分数格式不合法，继续读下一条
                    continue;
                }
				/*
				 * 录入成绩
				 */
                hql = "from TexamSignup sdy where 1=1 "
                        + "and sdy.texam.id=:examId "
                        + "and sdy.tdriver.id=:driverId ";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("examId", examId);
                params.put("driverId", driverId);
                TexamSignup examSignup = examSignupDao.get(hql, params);
                if (examSignup == null) {
                    // 查找不到对应报名信息，继续读下一条
                    continue;
                }
                //更新考生参加考试时间
                examSignup.setTakeExamTime(examSignup.getTexam().getExamStartTime());
                examSignup.setFinalScore(score);
                // 更新成绩信息
                examSignupDao.saveOrUpdate(examSignup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        file.delete();
        return true;
    }

    @Override
    public boolean submitScore(Integer examId) {
        try {
			/*
			 * 修改考试状态为证书录入中
			 */
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            exam.setStatus(ExamStatusEnum.CERTIFICATING.getState());
            examDao.saveOrUpdate(exam);

			/*
			 * 修改驾驶员报名状态为成绩已提交
			 */
            Set<TexamSignup> texamSignups = exam.getTexamSignups();
            for (TexamSignup texamSignup : texamSignups) {
                texamSignup.setStatus(DriverExamEnum.SCORED.getStatus());
                examSignupDao.saveOrUpdate(texamSignup);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<DriverInfoInExamManagement> transTexamSignupToSignupDriverInfo(
            List<TexamSignup> texamSignups) {
        List<DriverInfoInExamManagement> signupDriverInfos = new
                ArrayList<DriverInfoInExamManagement>();
        for (TexamSignup texamSignup : texamSignups) {
            DriverInfoInExamManagement signupDriverInfo = new DriverInfoInExamManagement();
            signupDriverInfo.setDriverId(texamSignup.getTdriver().getId());
            signupDriverInfo.setName(texamSignup.getTdriver().getName());
            signupDriverInfo.setOrgName(texamSignup.getTdriver()
                    .getTorganization().getName());
            signupDriverInfo.setOrgParentName(texamSignup.getTdriver()
                    .getTorganization().getTorganization().getName());
            signupDriverInfo.setTel(texamSignup.getTdriver().getMobile());
            signupDriverInfos.add(signupDriverInfo);
            if (texamSignup.getFinalScore() != null && texamSignup.getFinalScore() != 0.0) {
                signupDriverInfo.setScore(texamSignup.getFinalScore());
            }
        }
        return signupDriverInfos;
    }
}
