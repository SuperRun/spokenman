package com.zust.itee.exam.service.impl.exam.member.certificate;

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
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.enums.OutputFilePathEnmu;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.exam.member.certificate.ExamMemberCertificateService;
import com.zust.itee.exam.util.ExcelUtil;
import com.zust.itee.exam.util.UploadPathUtil;

@Service
@Transactional
public class ExamMemberCertificateServiceImpl implements
        ExamMemberCertificateService {

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
    public List<DriverInfoInExamManagement> getCertificateDrivers(
            Integer examId, PageBaseDto pageBaseDto) throws RuntimeException {
        List<DriverInfoInExamManagement> driverInfos = new ArrayList<DriverInfoInExamManagement>();
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    +
                    // 筛选报名了该考试的驾驶员
                    "and sdy.texam.id=:examId "
                    +
                    /*
                     * 筛选满足证书条件的驾驶员
					 */
                    "and ( (sdy.texam.tpaper.passScore<>null and sdy.finalScore>=sdy.texam.tpaper"
                    + ".passScore) "
                    + "or (sdy.texam.tpaper.passNum<>null and sdy.finalNum >= sdy.texam.tpaper"
                    + ".passNum) ) "
                    +
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
            // 转换成dto
            driverInfos = transTexamSignupToSignupDriverInfo(texamSignups);
			/*
			 * 修改考试状态
			 */
            hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            texam.setStatus(ExamStatusEnum.CERTIFICATING.getState());
            examDao.saveOrUpdate(texam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return driverInfos;
    }

    @Override
    public PageBaseDto getCertificatePageBaseDto(Integer examId,
            PageBaseDto pageBaseDto) {
        try {
            String hql = "select count(*) from TexamSignup sdy where 1=1 "
                    +
                    // 筛选报名了该考试的驾驶员
                    "and sdy.texam.id=:examId "
                    +
					/*
					 * 筛选满足证书条件的驾驶员
					 */
                    "and ( (sdy.texam.tpaper.passScore<>null and sdy.finalScore>=sdy.texam.tpaper"
                    + ".passScore) "
                    + "or (sdy.texam.tpaper.passNum<>null and sdy.finalNum >= sdy.texam.tpaper"
                    + ".passNum) ) "
                    +
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
            long sum = examSignupDao.count(hql, params);
            pageBaseDto.setSum(sum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String createDriverCertificateExcel(HttpServletRequest request,
            Integer examId) {
        try {
			/*
			 * 获取所有驾驶员信息
			 */
            String hql = "from TexamSignup sdy where 1=1 "
                    +
                    // 筛选报名了该考试的驾驶员
                    "and sdy.texam.id=:examId "
                    +
					/*
					 * 筛选满足证书条件的驾驶员
					 */
                    "and ( (sdy.texam.tpaper.passScore<>null and sdy.finalScore>=sdy.texam.tpaper"
                    + ".passScore) "
                    + "or (sdy.texam.tpaper.passNum<>null and sdy.finalNum >= sdy.texam.tpaper"
                    + ".passNum) ) ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            List<TexamSignup> examSignups = examSignupDao.find(hql, params);
            List<DriverInfoInExamManagement> driverInfos = transTexamSignupToSignupDriverInfo(
                    examSignups);

            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("证书录入表");
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
            cell.setCellValue("证书号");
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
                // 证书号
                if (driverInfo.getCertificateNo() != null) {
                    row.createCell(5).setCellValue(
                            driverInfo.getCertificateNo());
                }
            }

			/*
			 * 将文件存到指定位置
			 */
            // 创建文件夹
            String savePath = UploadPathUtil.getWebRoot(request);
            File file = new File(savePath
                    + OutputFilePathEnmu.CERTIFICATEEXCEL.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(savePath
                    + OutputFilePathEnmu.CERTIFICATEEXCEL.getPath() + examId
                    + ".xls");

            wb.write(fout);
            logger.info(file.getAbsolutePath());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return OutputFilePathEnmu.CERTIFICATEEXCEL.getPath() + examId + ".xls";
    }

    @Override
    public boolean certificateAllDrivers(File file, Integer examId)
            throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            // 考试不存在或不在证书录入状态
            if (exam == null
                    || exam.getStatus() != ExamStatusEnum.CERTIFICATING
                    .getState()) {
                return false;
            }
            // 获取对应流
            InputStream inputStream = new FileInputStream(file);
            // 将excel读取成二维数组
            HSSFCell[][] certificateInfo = ExcelUtil.transExcel(inputStream,
                    "证书录入表");
			/*
			 * 遍历二维数组录入证书
			 */
            logger.info("excel有=" + certificateInfo.length + "=条数据");

            for (int i = 0; i < certificateInfo.length; i++) {
                // 获取驾驶员id
                String driverIdStr = certificateInfo[i][0].getStringCellValue();
                Integer driverId;
                try {
                    driverId = Integer.parseInt(driverIdStr);
                } catch (NumberFormatException e) {
                    // 驾驶员id被修改成非法格式，继续读下一条
                    continue;
                }

                if (certificateInfo[i][5] == null) {
                    //没有证书信息跳过
                    continue;
                }

                // 获取证书
                String certificateNo = certificateInfo[i][5]
                        .getStringCellValue();
				/*
				 * 录入证书
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
                examSignup.setCertificateNo(certificateNo);
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
    public boolean certificateOneDriver(Integer examId, Integer driverId,
            String certificateNo, String certificatePhoto)
            throws RuntimeException {
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            params.put("driverId", driverId);
            TexamSignup examSignup = examSignupDao.get(hql, params);
            if (examSignup == null) {
                throw new RuntimeException("不存在报名记录！");
            }
            examSignup.setCertificateNo(certificateNo);
            examSignup.setCertificatePhoto(certificatePhoto);
            examSignupDao.saveOrUpdate(examSignup);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean submitCertificate(Integer examId) throws RuntimeException {
        try {
			/*
			 * 修改考试状态为已完成
			 */
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            if (exam == null) {
                throw new RuntimeException("考试不存在");
            }
            exam.setStatus(ExamStatusEnum.FINISHED.getState());
            examDao.saveOrUpdate(exam);

			/*
			 * 修改驾驶员报名状态为证书已提交，驾驶员状态改为正式驾驶员
			 */
            Set<TexamSignup> texamSignups = exam.getTexamSignups();
            for (TexamSignup texamSignup : texamSignups) {
                texamSignup.setStatus(DriverExamEnum.FINISHED.getStatus());
                examSignupDao.saveOrUpdate(texamSignup);
                Tdriver tdriver = texamSignup.getTdriver();
                if (tdriver == null) {
                    continue;
                } else {
                    // 驾驶员状态改为正式驾驶员
                    tdriver.setStatus(DriverStatusEnum.AVAILABLE.getStatus());
                    //修改驾驶员证书号
                    tdriver.setTrainCentificateNo(texamSignup.getCertificateNo());
                    //修改驾驶员证书照片
                    tdriver.setTrainCentificatePhoto(texamSignup.getCertificatePhoto());
                    driverDao.saveOrUpdate(tdriver);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return false;
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
            if (texamSignup.getFinalScore() != 0.0) {
                signupDriverInfo.setScore(texamSignup.getFinalScore());
            }
            if (texamSignup.getCertificateNo() != null) {
                signupDriverInfo.setCertificateNo(texamSignup
                        .getCertificateNo());
            }
            if (texamSignup.getCertificatePhoto() != null) {
                signupDriverInfo.setCertificate(texamSignup
                        .getCertificatePhoto());
            }
        }
        return signupDriverInfos;
    }
}
