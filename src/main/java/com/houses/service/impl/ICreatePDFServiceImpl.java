package com.houses.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.houses.common.vo.HouseItemVo;
import com.houses.service.ICreatePDFService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfFunction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
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
	 * @param projectName 工程名称
	 * @param doorNo 门牌号
	 * @param date 检测日期
	 * @param name 户主名称
	 */
	@Override
	public void showHousePdf(String path,String projectName,String doorNo,String date,String name) {
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
			PdfPCell cell12 = new PdfPCell(new Phrase(projectName, font));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell13 = new PdfPCell(new Phrase("门牌号", font));
			PdfPCell cell14 = new PdfPCell(new Phrase(doorNo, font));
			PdfPCell cell21 = new PdfPCell(new Phrase("检测日期", font));
			PdfPCell cell22 = new PdfPCell(new Phrase(date, font));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell23 = new PdfPCell(new Phrase("户主姓名", font));
			PdfPCell cell24 = new PdfPCell(new Phrase(name, font));
			table1.addCell(cell11);
			table1.addCell(cell12);
			table1.addCell(cell13);
			table1.addCell(cell14);
			table1.addCell(cell21);
			table1.addCell(cell22);
			table1.addCell(cell23);
			table1.addCell(cell24);
			
			PdfPCell cell = getPdfPTableCell(table1);
			
			Paragraph theme2 = new Paragraph("构件信息完善中。。。。。", font);
			theme.setAlignment(Element.ALIGN_CENTER);
			document.add(theme2);
			
			totalTable.addCell(cell);
			document.add(totalTable);
			document.close();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
