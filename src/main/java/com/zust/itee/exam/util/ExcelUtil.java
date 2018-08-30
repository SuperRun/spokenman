package com.zust.itee.exam.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {

	/**
	 *
	 * @param sheetName
	 *            sheetName(需要处理的工作表的名字) 传入 *.xls的文件输入流，需要保存成EXCEL97-2003样式
	 * @return 二维数组，不包含表头
	 * @throws IOException
	 *             输入流错误
	 * @throws NullPointerException
	 *             表格为空
	 */
	public static HSSFCell[][] transExcel(InputStream inputStream,
			String sheetName) throws IOException, NullPointerException {
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheet(sheetName);

		int height = sheet.getLastRowNum(); // sheet的行数，带表头有3行返回2。
		int width = sheet.getRow(0).getLastCellNum()
				- sheet.getRow(0).getFirstCellNum(); // sheet的列数

		System.out.println("存在列数："+width);


		HSSFCell[][] objects = new HSSFCell[height][width];

		// 遍历提取数据
		for (int i = 0; i < height; i++) {
			//从第1行开始读，第0行是表头
			HSSFRow row=sheet.getRow(i+1);
			//System.out.println("第"+i+1+"行数据：");
			if(row==null){
				//空行
				continue;
			}
			for (int j = 0; j < width; j++) {
				HSSFCell cell=row.getCell(j);
				if(cell!=null){
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}
				objects[i][j] = cell;
				//System.out.print(cell.toString()+" ");
			}
			//System.out.println();
		}

		// 关闭资源
		workbook.close();
		inputStream.close();

		return objects;
	}
}
