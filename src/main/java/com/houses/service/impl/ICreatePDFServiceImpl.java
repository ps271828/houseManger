package com.houses.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.houses.common.model.HouseMainInfo;
import com.houses.common.model.ItemCrack;
import com.houses.common.vo.HouseItemVo;
import com.houses.common.vo.HouseMainInfoVo;
import com.houses.common.vo.ItemCrackVo;
import com.houses.service.ICreatePDFService;
import com.itextpdf.text.BadElementException;
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
//		pdfPCell.setBorder(1);
//		pdfPCell.setBorder(Rectangle.NO_BORDER);
//		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			int[] totalTableWidth = {100};
			PdfPTable totalTable = getPdfPTable(1, totalTableWidth);
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
			cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell23 = new PdfPCell(new Phrase("", font));
			PdfPCell cell24 = new PdfPCell(new Phrase("", font));
			table1.addCell(cell11);
			table1.addCell(cell12);
			table1.addCell(cell13);
			table1.addCell(cell14);
			table1.addCell(cell21);
			table1.addCell(cell22);
			table1.addCell(cell23);
			table1.addCell(cell24);
			
			PdfPCell cell1 = getPdfPTableCell(table1);
			cell1.setBorder(Rectangle.NO_BORDER);
			totalTable.addCell(cell1);
			
			int i = 1;
			for(HouseItemVo houseItemVo : houseMainInfoVo.getHouseItemVoList()) {
				int[] table3Width = {100};
				PdfPTable table3 = getPdfPTable(1,table3Width);
				
				List<ItemCrackVo> itemCrackVoList = houseItemVo.getItemCrackVoList();
				//设置文字
				PdfPCell houseTextCell = setCreakTextList(itemCrackVoList, houseItemVo.toString(),i);
				table3.addCell(houseTextCell);
				
				//设置图片
				PdfPCell houseImageCell = setCreakImageList(itemCrackVoList, houseItemVo.getFullItemExampleImage(),i);
				if(houseImageCell != null) {
					table3.addCell(houseImageCell);
				}
				
				
				PdfPCell table3Cell = getPdfPTableCell(table3);
				totalTable.addCell(table3Cell);			
			}
			
			if(!StringUtils.isEmpty(houseMainInfoVo.getSignPath())) {
				
				int[] table4Width = {30,70};
				PdfPTable table4 = getPdfPTable(2,table4Width);
				Image image;
				try {
					PdfPCell cell42 = new PdfPCell(new Phrase("签名：", getFont()));
					cell42.setBorder(Rectangle.NO_BORDER);
					cell42.setHorizontalAlignment(Element.ALIGN_RIGHT);
					table4.addCell(cell42);
					//图片
					image = Image.getInstance(houseMainInfoVo.getSignPath());
					PdfPCell cell41= new PdfPCell(image,true);
					cell41.setBorder(Rectangle.NO_BORDER);
					cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
					table4.addCell(cell41);
					//图片对应的文字 
				} catch (BadElementException | IOException e) {
					e.printStackTrace();
				}
				PdfPCell table4Cell = getPdfPTableCell(table4);
				table4Cell.setBorder(Rectangle.NO_BORDER);
				totalTable.addCell(table4Cell);
			}
			
			document.add(totalTable);
			document.close();
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置裂缝项图片文体描述
	 * @param imagePath 图片路径
	 * @param text 图片描述
	 * @return
	 * @throws Exception
	 */
	public static PdfPCell setCreakImageList(List<ItemCrackVo> itemCrackVoList,String imagePath,int i) throws Exception {
		PdfPTable table1 = new PdfPTable(2);
		for(ItemCrack itemCrackVo : itemCrackVoList) {
			PdfPTable table11 = new PdfPTable(1);
			PdfPCell creakItemCell = setCreakItem(itemCrackVo.getExampleImage(), "图" + i++);
			table11.addCell(creakItemCell);
			
			PdfPCell table11Cell = getPdfPTableCell(table11);
			table11Cell.setBorder(Rectangle.NO_BORDER);
			table1.addCell(table11Cell);
		}
		
		PdfPCell fullImageCell = setCreakItem(imagePath, "全景图");
		table1.addCell(fullImageCell);
		
		PdfPCell table1Cell = getPdfPTableCell(table1);
		table1Cell.setBorder(Rectangle.NO_BORDER);
		return table1Cell;
	}
	
	/**
	 * 设置全景图
	 * @param imagePath 图片路径
	 * @param text 图片描述
	 * @return
	 * @throws Exception
	 */
	public static PdfPCell setCreakItem(String imagePath,String text) throws Exception {
		PdfPTable table1 = new PdfPTable(1);
		Image image;
		try {
			File file = new File(imagePath);
			if(!file.exists()) {
				return null;
			}
			//图片
			image = Image.getInstance(imagePath);
			PdfPCell cell1= new PdfPCell(image,true);
			cell1.setBorder(Rectangle.NO_BORDER);
			
			table1.addCell(cell1);
			//图片对应的文字 
			PdfPCell cell2 = new PdfPCell(new Phrase(text, getFont()));
			cell2.setBorder(Rectangle.NO_BORDER);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell2);
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		}
		PdfPCell table1Cell = getPdfPTableCell(table1);
		table1Cell.setBorder(Rectangle.NO_BORDER);
		return table1Cell;
	}
	
	
	public static PdfPCell setCreakTextList(List<ItemCrackVo> itemCrackVoList,String text,int i) throws Exception {
		PdfPTable table1 = new PdfPTable(1);
		//设置构件项文字
		PdfPCell cell1 = new PdfPCell(new Phrase(text, getFont()));
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell1);
		for(ItemCrack itemCrackVo : itemCrackVoList) {
			PdfPCell cell2 = new PdfPCell(new Phrase(itemCrackVo.toString() + i++, getFont()));
			cell2.setBorder(Rectangle.NO_BORDER);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell2);
		}
		
		PdfPCell table1Cell = getPdfPTableCell(table1);
		table1Cell.setBorder(Rectangle.NO_BORDER);
		return table1Cell;
	}
	
}
