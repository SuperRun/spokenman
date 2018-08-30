package com.zust.itee.exam.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.OrganizationExcelUpdateInfoDto;

/**
 * 表格读写的工具类
 * Created by dxb on 2016/9/17.
 */
public class MyExcelUtil {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";

    // *****.xls , 2,2
    //    public static List<StudentExcelModel> readExcelStudentExcelModel(String path, int
    // start, int end) throws IOException, ExcelUtilException {
    //        try {
    //            if (MyStrUtil.hasEmpty(path)) {
    //                return null;
    //            }
    //            Sheet sheet = null;
    //            if (isXls(path)) {
    //                sheet = readXlsToSheet(path);
    //            } else if (isXlsx(path)) {
    //                sheet = readXlsxToSheet(path);
    //            } else {
    //                throw new ExcelUtilException("文件不是EXCEL表格");
    //            }
    //            if (sheet == null) {
    //                throw new ExcelUtilException("EXCEL中读取不到SHEET");
    //            } else {
    //
    //
    //
    //                List<StudentExcelModel> list = new ArrayList<>();
    //                for (int i = start; i < end; i++) {
    //                    Row row = sheet.getRow(i);
    //
    //                    if (row != null) {
    //                        Cell username = row.getCell(0);
    //                        Cell trueName = row.getCell(1);
    //                        Cell majorName = row.getCell(2);
    //                        Cell clazzName = row.getCell(3);
    //
    //                        StudentExcelModel student = new StudentExcelModel(getValue
    // (username), getValue(clazzName), getValue(majorName), getValue(trueName));
    //                        list.add(student);
    //                    }
    //                }
    //                return list;
    //            }
    //        } catch (IOException | ExcelUtilException e) {
    //            e.printStackTrace();
    //            throw e;
    //        }
    //
    //    }

    public static List<OrganizationExcelUpdateInfoDto> readOrganizationExcelUpdateInfoDtos(
            String path) throws IOException, RuntimeException {
        if (MyStrUtil.hasEmpty(path)) {
            return null;
        }
        Sheet sheet = null;
        if (isXls(path)) {
            sheet = readXlsToSheet(path);
        } else if (isXlsx(path)) {
            sheet = readXlsxToSheet(path);
        } else {
            throw new RuntimeException("文件不是EXCEL表格");
        }

        if (sheet == null) {
            throw new RuntimeException("EXCEL中读取不到SHEET");
        } else {
            List<OrganizationExcelUpdateInfoDto> list = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    Cell provinceCell = row.getCell(0);
                    Cell cityCell = row.getCell(1);
                    Cell districtCell = row.getCell(2);
                    Cell orgNameCell = row.getCell(3);
                    Cell orgShortNameCell = row.getCell(4);
                    Cell orgIntroCell = row.getCell(5);
                    Cell managerNameCell = row.getCell(6);
                    Cell managerTelCell = row.getCell(7);
                    Cell managerPhoneCell = row.getCell(8);
                    Cell managerEmailCell = row.getCell(9);
                    Cell contactNameCell = row.getCell(10);
                    Cell contactTelCell = row.getCell(11);
                    Cell contactPhoneCell = row.getCell(12);
                    Cell contactEmailCell = row.getCell(13);

                    OrganizationExcelUpdateInfoDto o = new OrganizationExcelUpdateInfoDto(
                            getValue(provinceCell),
                            getValue(cityCell),
                            getValue(districtCell),
                            getValue(orgNameCell),
                            getValue(orgShortNameCell),
                            getValue(orgIntroCell),
                            getValue(managerNameCell),
                            getValue(managerTelCell),
                            getValue(managerPhoneCell),
                            getValue(managerEmailCell),
                            getValue(contactNameCell),
                            getValue(contactTelCell),
                            getValue(contactPhoneCell),
                            getValue(contactEmailCell)
                    );

                    list.add(o);
                }
            }
            return list;
        }
    }

    public static List<OrganizationExcelDto> readExcelOrganizationExcelDtos(String path)
            throws IOException, RuntimeException {
        if (MyStrUtil.hasEmpty(path)) {
            return null;
        }
        Sheet sheet = null;
        if (isXls(path)) {
            sheet = readXlsToSheet(path);
        } else if (isXlsx(path)) {
            sheet = readXlsxToSheet(path);
        } else {
            throw new RuntimeException("文件不是EXCEL表格");
        }
        if (sheet == null) {
            throw new RuntimeException("EXCEL中读取不到SHEET");
        } else {
            List<OrganizationExcelDto> list = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    Cell companyName = row.getCell(0);
                    Cell superRegion = row.getCell(1);
                    Cell region = row.getCell(2);
                    Cell address = row.getCell(3);
                    Cell legalPerson = row.getCell(4);
                    Cell legalPersonTel = row.getCell(5);
                    Cell legalPersonEmail = row.getCell(6);
                    Cell leaderName = row.getCell(7);
                    Cell leaderTel = row.getCell(8);
                    Cell leaderEmail = row.getCell(9);
                    Cell driverName = row.getCell(10);
                    Cell driverTel = row.getCell(11);
                    Cell driverEmail = row.getCell(12);
                    Cell driverSfzNo = row.getCell(13);

                    OrganizationExcelDto o = new OrganizationExcelDto(
                            getValue(companyName),
                            getValue(superRegion),
                            getValue(address),
                            getValue(region),
                            getValue(legalPerson),
                            getValue(legalPersonTel),
                            getValue(legalPersonEmail),
                            getValue(leaderName),
                            getValue(leaderTel),
                            getValue(leaderEmail),
                            getValue(driverName),
                            getValue(driverTel),
                            getValue(driverEmail),
                            getValue(driverSfzNo)
                    );
                    list.add(o);
                }
            }
            return list;
        }
    }

    public static boolean isXls(String path) {
        return MyExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(getPostfix(path));
    }

    public static boolean isXlsx(String path) {
        return MyExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(getPostfix(path));
    }

    public static Sheet readXlsxToSheet(String path) throws IOException {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            if (xssfWorkbook.getNumberOfSheets() >= 1) {
                return xssfWorkbook.getSheetAt(0);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static Sheet readXlsToSheet(String path) throws IOException {

        InputStream is = null;
        try {
            is = new FileInputStream(path);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            if (hssfWorkbook.getNumberOfSheets() >= 1) {
                return hssfWorkbook.getSheetAt(0);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String getValue(Cell cell) {
        if (cell == null) {
            return null;
            //        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            //            return String.valueOf(cell.getBooleanCellValue());
            //        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //            return String.valueOf(cell.getNumericCellValue());
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);

            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }

    /**
     * 获取后缀
     *
     * @param path 文件路径
     * @return 后缀类型
     */
    public static String getPostfix(String path) {
        if (path == null || MyExcelUtil.EMPTY.equals(path.trim())) {
            return MyExcelUtil.EMPTY;
        }
        if (path.contains(MyExcelUtil.POINT)) {
            return path.substring(path.lastIndexOf(MyExcelUtil.POINT) + 1, path.length());
        }
        return MyExcelUtil.EMPTY;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getDateCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = cell.getStringCellValue();
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    public static String readCellValues(Cell cell) throws Exception {
        // 用于返回结果
        String result = new String();

        try {
            // 如果单元格为空，返回null
            if (cell == null) {
                result = "null";
            } else {
                // 判断单元格类型
                switch (cell.getCellType()) {
                    // 数字类型
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        // 处理日期格式、时间格式
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat sdf = null;
                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                                    .getBuiltinFormat("h:mm")) {
                                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            } else {// 日期
                                sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }
                            Date date = cell.getDateCellValue();
                            result = sdf.format(date);
                        } else if (cell.getCellStyle().getDataFormat() == 58) {
                            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            double value = cell.getNumericCellValue();
                            Date date = org.apache.poi.ss.usermodel.DateUtil
                                    .getJavaDate(value);
                            result = sdf.format(date);
                        } else {
                            double value = cell.getNumericCellValue();
                            CellStyle style = cell.getCellStyle();
                            DecimalFormat format = new DecimalFormat();
                            String temp = style.getDataFormatString();
                            // 单元格设置成常规
                            if (temp.equals("General")) {
                                format.applyPattern("#");
                            }
                            result = format.format(value);
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:// String类型
                        result = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        result = "";
                        break;
                    default:
                        result = "";
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //    private static String saveHSSFWorkbookToFile(String webroot, HSSFWorkbook hssfWorkbook,
    // Integer userId) throws IOException {
    //        String returnPath = UploadPathUtil.virtualRoot + "/" + UploadPathUtil.ATTACHMENT +
    // "/" + userId + "_" + System.currentTimeMillis() + "." + MyExcelUtil
    // .OFFICE_EXCEL_2003_POSTFIX;
    //        String name = webroot + "/" + returnPath;
    //        Logger logger = LoggerFactory.getLogger(MyExcelUtil.class);
    //
    //
    //        logger.info("拼接name = " + name);
    //
    //
    //        File file = new File(name);
    //        //创建目录
    //        file.getParentFile().mkdirs();
    //
    //        logger.info("文件 绝对路径 =" + file.getAbsolutePath());
    //
    //        FileOutputStream os = null;
    //        try {
    //            os = new FileOutputStream(name);
    //            hssfWorkbook.write(os);
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        } finally {
    //            if (os != null) os.close();
    //        }
    //
    //        return returnPath;
    //    }

    //    public static String buildStudentScoreExcelFile(Integer userId,
    // List<StudentListItemModel> listItemModels, String webroot) throws IOException {
    //        HSSFWorkbook workbook = new HSSFWorkbook();//excel 文件
    //
    //        HSSFSheet sheetStudentScore = workbook.createSheet("学生成绩");//sheet
    //
    //
    //        //表头
    //        HSSFRow rowHeader = sheetStudentScore.createRow(0);
    //
    //        HSSFCell cell = rowHeader.createCell(0);
    //        cell.setCellValue("学号");
    //
    //        cell = rowHeader.createCell(1);
    //        cell.setCellValue("专业班级");
    //
    //        cell = rowHeader.createCell(2);
    //        cell.setCellValue("姓名");
    //
    //        cell = rowHeader.createCell(3);
    //
    //        cell.setCellValue("得分");
    //
    //        if (listItemModels != null && listItemModels.size() > 0) {
    //            for (int i = 1; i <= listItemModels.size(); i++) {
    //                HSSFRow row = sheetStudentScore.createRow(i);
    //                StudentListItemModel studentListItemModel = listItemModels.get(i - 1);
    //                HSSFCell cellStudent = null;
    //
    //                if (studentListItemModel.getUsername() != null) {
    //                    cellStudent = row.createCell(0);
    //                    cellStudent.setCellValue(studentListItemModel.getUsername());
    //                }
    //
    //                if (studentListItemModel.getClazzName() != null) {
    //
    //                    cellStudent = row.createCell(1);
    //                    cellStudent.setCellValue(studentListItemModel.getClazzName());
    //                }
    //
    //                if (studentListItemModel.getTrueName() != null) {
    //                    cellStudent = row.createCell(2);
    //                    cellStudent.setCellValue(studentListItemModel.getTrueName());
    //                }
    //
    //                if (studentListItemModel.getSumScore() != null) {
    //                    cellStudent = row.createCell(3);
    //                    cellStudent.setCellValue(studentListItemModel.getSumScore());
    //                }
    //
    //            }
    //        } else {
    //            HSSFRow row = sheetStudentScore.createRow(1);
    //            HSSFCell cellStudent = row.createCell(0);
    //            cellStudent.setCellValue("无学生");
    //        }
    //
    //        return saveHSSFWorkbookToFile(webroot, workbook, userId);
    //
    //    }
}