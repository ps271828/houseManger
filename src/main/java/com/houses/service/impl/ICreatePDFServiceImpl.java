package com.houses.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.houses.common.model.HouseMainInfo;
import com.houses.common.model.ItemCrack;
import com.houses.common.vo.HouseItemVo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.common.vo.ItemCrackVo;
import com.houses.service.ICreatePDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ICreatePDFServiceImpl implements ICreatePDFService {
	
	
	public static PdfPCell getPdfPTableCell(PdfPTable pdfPTable) throws Exception {
		if (pdfPTable == null) {
			throw new Exception("pdfPTable不能为空");
		}
		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.addElement(pdfPTable);
//		pdfPCell.setBorder(0);
		pdfPCell.setBorder(Rectangle.NO_BORDER);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return pdfPCell;
	}
	
	public static PdfPTable getPdfPTable(int column, int[] tableWidth) throws Exception {
		PdfPTable table = new PdfPTable(column);
		table.setWidths(tableWidth);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);
		return table;
	}
	
	/**
	 * 获取PDF字体
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Font getFont() {
		BaseFont bfChinese;
		Font font = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			font = new Font(bfChinese);// 正常字体
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
	/**
	 * 
	 * @param path PDF保存路径
	 * 	 * @param projectName 工程名称
	 * 	 * @param doorNo 门牌号
	 * 	 * @param date 检测日期
	 * 	 * @param name 户主名称
	 */
	@Override
	public void showHousePdf(String path,HouseMainInfoVo houseMainInfoVo) {
		Document document = null;
		PdfWriter writer = null;
		try {
			File file = new File(path);
			document = new Document(PageSize.A4);
			writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			Font font = getFont();
			Paragraph theme = new Paragraph("分户现状调查表", font);
			theme.setAlignment(Element.ALIGN_CENTER);
			document.add(theme);
			
			//创建totalTable
			PdfPTable totalTable = new PdfPTable(1);
			// 第一行
			
			int[] table1Width = { 20,30,20,30 };
			PdfPTable table1 = getPdfPTable(4, table1Width);
			PdfPCell cell11 = new PdfPCell(new Phrase("工程名称", font));
			PdfPCell cell12 = new PdfPCell(new Phrase(houseMainInfoVo.getProjectName(), font));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell13 = new PdfPCell(new Phrase("门牌号", font));
			PdfPCell cell14 = new PdfPCell(new Phrase(houseMainInfoVo.getHouseNum(), font));
			PdfPCell cell21 = new PdfPCell(new Phrase("检测日期", font));
			Long timeLong = houseMainInfoVo.getCheckDate();
			Date datetime = new Date(timeLong);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatDate = sdf.format(datetime);
			PdfPCell cell22 = new PdfPCell(new Phrase(formatDate, font));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell23 = new PdfPCell(new Phrase("户主姓名", font));
			PdfPCell cell24 = new PdfPCell(new Phrase(houseMainInfoVo.getMasterName(), font));
			table1.addCell(cell11);
			table1.addCell(cell12);
			table1.addCell(cell13);
			table1.addCell(cell14);
			table1.addCell(cell21);
			table1.addCell(cell22);
			table1.addCell(cell23);
			table1.addCell(cell24);
			
			PdfPCell cell1 = getPdfPTableCell(table1);
			
			/*
			 * 第三行分三个table显示
			 * 第一个显示裂缝的文字信息
			 * 第二个显示图片信息
			 * 第三个显示图片下面的文字描述
			 */
			PdfPTable table3 = new PdfPTable(1);
			PdfPCell cell31 = new PdfPCell();
			PdfPCell cell32 = new PdfPCell();
			PdfPCell cell33 = new PdfPCell();
			int[] table31Width = { 50,50 };
			//显示裂缝文字描述
			PdfPTable table31 =  new PdfPTable(1);
			PdfPTable table32 =  getPdfPTable(2, table31Width);
			PdfPTable table33 =  getPdfPTable(2, table31Width);
			int i = 1;
			for(HouseItemVo houseItemVo : houseMainInfoVo.getHouseItemVoList()) {
				List<ItemCrackVo> itemCrackVoList = houseItemVo.getItemCrackVoList();
				for(ItemCrack itemCrackVo : itemCrackVoList) {
					System.out.println(itemCrackVo.toString());
					PdfPCell cell311 = new PdfPCell(new Phrase(houseItemVo.toString() + itemCrackVo.toString() + i++, font));
					table31.addCell(cell311);
					
					Image image = Image.getInstance(itemCrackVo.getExampleImage());
					table32.addCell(image);
					
					table32.addCell(new PdfPCell(new Phrase("图"+i, font)));
				}
			}
			cell31.addElement(table31);
			cell32.addElement(table32);
			cell33.addElement(table33);
			
			table3.addCell(cell31);
			table3.addCell(cell32);
			table3.addCell(cell33);
			
			PdfPCell cell3 = getPdfPTableCell(table3);
			
			totalTable.addCell(cell3);			
			totalTable.addCell(cell1);
			document.add(totalTable);
			document.close();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
