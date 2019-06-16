package com.houses.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class PrintPDFUtils {

	// 图片路径放在classpath img包下
	public static String ztoImgPath = "/img/ZTO2.png";
	public static String tiaoImgPath = "/img/test.jpg";
	public static String ewmImgPath = "/img/EWM.png";
	public static String tbImgPath = "/img/TB.jpg";
	public static String jiImgPath = "/img/ji.png";

	// 字体文件，放在classpath fonts包下
	public static String boldPATH = "/fonts/SourceHanSansK-Bold.ttf";
	public static String lightPATH = "/fonts/SourceHanSansK-Light.ttf";
	public static String normalPATH = "/fonts/SourceHanSansK-Normal.ttf";

	// 面单信息
	public static String receiver = "receiver";
	public static String receiverPhone = "receiverPhone";
	public static String receiverAddr = "receiverAddr-4";
	public static String sender = "sender";
	public static String senderPhone = "senderPhone";
	public static String senderAddr = "senderAddr 22";
	public static String barCode = "barCode";
	public static String topCode = "topCode";
	public static String topAddr = "topAddr";
	public static String printDate = "printDate";
	public static String orderCode = "orderCode";
	
	
	static {
		boldPATH = PrintPDFUtils.class.getResource(boldPATH).getFile();
		lightPATH = PrintPDFUtils.class.getResource(lightPATH).getFile();
		normalPATH = PrintPDFUtils.class.getResource(normalPATH).getFile();
		
		ztoImgPath = PrintPDFUtils.class.getResource(ztoImgPath).getFile();
		tiaoImgPath = PrintPDFUtils.class.getResource(tiaoImgPath).getFile();
		ewmImgPath = PrintPDFUtils.class.getResource(ewmImgPath).getFile();
		tbImgPath = PrintPDFUtils.class.getResource(tbImgPath).getFile();
		jiImgPath = PrintPDFUtils.class.getResource(jiImgPath).getFile();
	}

	public static void printPDF(OutputStream os) {

		try {
			Rectangle rectangle = new Rectangle(280, 426);
			Document document = new Document(rectangle, 0, 0, 0, 0);
			PdfWriter writer = PdfWriter.getInstance(document, os);
			
			//粗体
			BaseFont boldFont = BaseFont.createFont(boldPATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font boldFont12 = new Font(boldFont, 12, Font.NORMAL);
			Font boldFont16 = new Font(boldFont, 16, Font.NORMAL);
			Font boldFont10 = new Font(boldFont, 10, Font.NORMAL);
			Font boldFont8 = new Font(boldFont, 8, Font.NORMAL);
			//细体
			BaseFont lightFont = BaseFont.createFont(lightPATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font lightFont16 = new Font(lightFont, 16, Font.NORMAL);
			Font lightFont12 = new Font(lightFont, 12, Font.NORMAL);
			Font lightFont10 = new Font(lightFont, 10, Font.NORMAL);
			Font lightFont9 = new Font(lightFont, 9, Font.NORMAL);
			Font lightFont8 = new Font(lightFont, 8, Font.NORMAL);
			Font lightFont6 = new Font(lightFont, 6, Font.NORMAL);
			//普通
			BaseFont normalFont = BaseFont.createFont(normalPATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font normalFont21 = new Font(normalFont, 21, Font.NORMAL);
			Font normalFont16 = new Font(normalFont, 16, Font.NORMAL);
			Font normalFont12 = new Font(normalFont, 12, Font.NORMAL);
			Font normalFont11 = new Font(normalFont, 11, Font.NORMAL);
			Font normalFont10 = new Font(normalFont, 10, Font.NORMAL);
			Font normalFont8 = new Font(normalFont, 8, Font.NORMAL);
			Font normalFont7 = new Font(normalFont, 7, Font.NORMAL);
			
			document.open();
			// 第一行
			int[] table1Width = { 30, 40, 30 };
			PdfPTable table1 = getPdfPTable(3, table1Width);

			Image table1Img = Image.getInstance(ztoImgPath);

			PdfPCell l1c2 = new PdfPCell();
			PdfPCell l1c3 = new PdfPCell(new Phrase("国际快递", normalFont16));
			
			l1c2.setBorder(0);
			l1c3.setBorder(0);
			l1c3.setHorizontalAlignment(Element.ALIGN_RIGHT);

			table1.addCell(table1Img);
			table1.addCell(l1c2);
			table1.addCell(l1c3);
			//document.add(table1);
			
			// 虚线
			//document.add(getDashLineParagraph());
			
			//第二行
			PdfPTable table2 = getPdfPTable(3, new int[]{20, 60, 20});
			
			PdfPCell table2C1 = getPdfPCell(new Phrase(topCode, normalFont21));
			table2C1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2C1.setVerticalAlignment(Element.ALIGN_CENTER);
			
			table2.addCell(getPdfPCell());
			table2.addCell(table2C1);
			table2.addCell(getPdfPCell());
			//document.add(table2);
			
			//虚线
			//document.add(getDashLineParagraph());
			
			//第三行
			PdfPTable table3 = getPdfPTable(4, new int[]{10, 15, 55, 20});
			
			//PdfPCell table3C1 = getPdfPCell(new Phrase("集", fontChinese14Bold));
			Image table3JIImg = Image.getInstance(jiImgPath);
			table3JIImg.scalePercent(45);
			PdfPCell table3C1 = new PdfPCell(table3JIImg);
			
			PdfPCell table3C2 = getPdfPCell(new Phrase("Exchange Office", lightFont8));
			PdfPCell table3C3 = getPdfPCell(new Phrase(topAddr, boldFont16), Element.ALIGN_CENTER);
			PdfPCell table3C4 = getPdfPCell();
			
			table3C1.setBorder(0);
			table3C1.setPaddingLeft(6);
			//table3C1.setPaddingBottom(5.5f);
			table3C1.setVerticalAlignment(Element.ALIGN_CENTER);
			table3C2.setVerticalAlignment(Element.ALIGN_CENTER);
			table3C3.setVerticalAlignment(Element.ALIGN_CENTER);
			
			
			table3.addCell(table3C1);
			table3.addCell(table3C2);
			table3.addCell(table3C3);
			table3.addCell(table3C4);
			//document.add(table3);
			
			//虚线
			//document.add(getDashLineParagraph());
			
			//第4、5行
			PdfPTable table45 = getPdfPTable(3, new int[]{20, 55, 25});
			Phrase t45C1P = new Phrase();
			t45C1P.add(new Chunk("收件人", normalFont10));
			t45C1P.add(Chunk.NEWLINE);
			t45C1P.add(new Chunk("To/Consignee", normalFont7));
			
			PdfPCell table45C1 = getPdfPCell(t45C1P);
			
			Phrase t45C2P = new Phrase();
			t45C2P.add(new Chunk(receiver + " " + receiverPhone, boldFont8));
			t45C2P.add(Chunk.NEWLINE);
			t45C2P.add(new Chunk(receiverAddr, normalFont7));
			
			PdfPCell table45C2 = getPdfPCell(t45C2P);
			
			/*Phrase tableC3P = new Phrase();
			tableC3P.add(new Chunk("□ 重量/Weight:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);
			tableC3P.add(new Chunk("□ 价值/Value:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);
			tableC3P.add(new Chunk("□ 代收/Payable:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);
			tableC3P.add(new Chunk("□ 长/L:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);
			tableC3P.add(new Chunk("□ x宽/W:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);
			tableC3P.add(new Chunk("□ 高/H:", fontChinese8Normal));
			tableC3P.add(Chunk.NEWLINE);*/
			//替换为单列表格
			PdfPTable table45_Cell3_table = getTable45Cell3(lightFont6);
			
			PdfPCell table45C3 = new PdfPCell(table45_Cell3_table);
			
			Phrase t45C4P = new Phrase();
			t45C4P.add(new Chunk("寄件人", normalFont10));
			t45C4P.add(Chunk.NEWLINE);
			t45C4P.add(new Chunk("From/Shipper", lightFont6));
			t45C4P.add(Chunk.NEWLINE);
			t45C4P.add(new Chunk("Return Add.", lightFont6));
			
			PdfPCell table45C4 = getPdfPCell(t45C4P);
			
			Phrase t45C5P = new Phrase();
			t45C5P.add(new Chunk(sender + " " + senderPhone, boldFont8));
			t45C5P.add(Chunk.NEWLINE);
			t45C5P.add(new Chunk(senderAddr, lightFont6));
			
			//table45设置样式	
			int table45Hight = 35;
			PdfPCell table45C5 = getPdfPCell(t45C5P);
			table45C1.setFixedHeight(table45Hight);
			table45C1.setPadding(5);
			setBoder(table45C1, null, 1, null, 1);
			
			table45C2.setPadding(5);
			table45C2.setFixedHeight(table45Hight);
			setBoder(table45C2, null, 1, null, 1);
			
			table45C3.setBorder(0);
			table45C3.setPadding(1);
			table45C3.setRowspan(2);
			table45C3.setFixedHeight(table45Hight);
			
			table45C4.setPadding(5);
			table45C4.setFixedHeight(table45Hight);
			table45C4.setBorderWidthRight(1);
			
			table45C5.setPadding(5);
			table45C5.setFixedHeight(table45Hight);
			table45C5.setBorderWidthRight(1);
			
			table45.addCell(table45C1);
			table45.addCell(table45C2);
			table45.addCell(table45C3);
			table45.addCell(table45C4);
			table45.addCell(table45C5);
			//document.add(table45);
			//虚线
			//document.add(getDashLineParagraph());
			
			//第6行（条形码）
			PdfPTable table6 = getPdfPTable(3, new int[]{15, 70 ,15});
			
			Image tiaoImg = Image.getInstance(tiaoImgPath);
			tiaoImg.scalePercent(70);
			PdfPCell table6C2 = new PdfPCell(tiaoImg);
			table6C2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6C2.setPadding(3);
			table6C2.setBorder(0);
			
			PdfPCell table6C5 = getPdfPCell(new Phrase(barCode, boldFont16));
			table6C5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6C5.setPadding(3);
			table6C5.setPaddingTop(-3);
			
			table6.addCell(getPdfPCell());
			table6.addCell(table6C2);
			table6.addCell(getPdfPCell());
			table6.addCell(getPdfPCell());
			table6.addCell(table6C5);
			table6.addCell(getPdfPCell());
			//document.add(table6);
			
			//虚线
			//document.add(getDashLineParagraph());
			
			//第七行
			PdfPTable table7 = getPdfPTable(3, new int[]{40, 35 ,25});
			
			String table7C1_s1 = "快件送达收件人地址,经收件人或收件人(寄件人)允许的代收件人签字，视为送达，"
					+ "您的签字代表您已签收此包裹，并以确认商品信息无误，包装完好，没有划痕，破损等方面质量问题。";
			Phrase table7C1P = new Phrase();
			table7C1P.add(new Chunk(table7C1_s1, lightFont6));
			table7C1P.add(Chunk.NEWLINE);
			table7C1P.add(Chunk.NEWLINE);
			table7C1P.add(new Chunk("                                " + printDate, lightFont6));
			
			PdfPCell table7C1 = getPdfPCell(table7C1P);
			
			Phrase table7C2P = new Phrase();
			table7C2P.add(new Chunk("签收", normalFont8));
			table7C2P.add(Chunk.NEWLINE);
			table7C2P.add(new Chunk("Signature required:", normalFont7));
			table7C2P.add(Chunk.NEWLINE);
			table7C2P.add(Chunk.NEWLINE);
			table7C2P.add(new Chunk("时间", normalFont8));
			table7C2P.add(Chunk.NEWLINE);
			table7C2P.add(new Chunk("Date:", normalFont7));
			
			PdfPCell table7C2 = getPdfPCell(table7C2P);
			
			Image table7Img = Image.getInstance(ewmImgPath);
			table7Img.scalePercent(20);
			PdfPCell table7C3 = new PdfPCell(table7Img);
			
			//table7
			int table7Width = 60;
			table7C1.setPadding(3);
			table7C1.setFixedHeight(table7Width);
			setBoder(table7C1, null, 1, null, 1);
			
			table7C2.setPadding(3);
			table7C2.setFixedHeight(table7Width);
			setBoder(table7C2, null, 1, null, 1);
			
			table7C3.setPadding(2);
			table7C3.setPaddingLeft(5);
			table7C3.setPaddingBottom(2);
			table7C3.setFixedHeight(table7Width);
			table7C3.setBorder(0);
			setBoder(table7C3, null, 1, null, null);
			
			table7.addCell(table7C1);
			table7.addCell(table7C2);
			table7.addCell(table7C3);
			//document.add(table7);
			
			//第8行
			PdfPTable table8 = getPdfPTable(2, new int[]{40, 60});
			
			Image table8ZTO = Image.getInstance(ztoImgPath);
			table8ZTO.scalePercent(38);
			PdfPCell table8C1 = new PdfPCell(table8ZTO);
			
			Image table8Img = Image.getInstance(tiaoImgPath);
			table8Img.scalePercent(70);
			PdfPCell table8C2 = new PdfPCell(table8Img);
			PdfPCell table8C4 = getPdfPCell(new Phrase(barCode, boldFont10));
			
			//table8样式
			int table8Hight = 50;
			table8C1.setPadding(1);
			table8C1.setPaddingTop(11f);
			table8C1.setPaddingLeft(5);
			table8C1.setBorder(0);
			table8C1.setRowspan(2);
			table8C1.setVerticalAlignment(Element.ALIGN_CENTER);
			
			
			table8C2.setPadding(1);
			table8C2.setBorder(0);
			table8C2.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table8C4.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table8.addCell(table8C1);
			table8.addCell(table8C2);
			table8.addCell(table8C4);
			
			//第9行
			PdfPTable table9 = getPdfPTable(2, new int[]{50, 50});
			
			Phrase table9C1P = new Phrase();
			table9C1P.add(new Chunk("收件人信息/To:", normalFont8));
			table9C1P.add(Chunk.NEWLINE);
			table9C1P.add(new Chunk(receiver + " " + receiverPhone, normalFont7));
			table9C1P.add(Chunk.NEWLINE);
			table9C1P.add(new Chunk(receiverAddr, normalFont7));
			
			PdfPCell table9C1 = getPdfPCell(table9C1P);
			table9C1.setPadding(2);
			setBoder(table9C1, null, null, null, 1);
			
			
			Phrase table9C2P = new Phrase();
			table9C2P.add(new Chunk("寄件人/From:", normalFont8));
			table9C2P.add(Chunk.NEWLINE);
			table9C2P.add(new Chunk(sender + " " + senderPhone, normalFont7));
			table9C2P.add(Chunk.NEWLINE);
			table9C2P.add(new Chunk(senderAddr, normalFont7));
			
			PdfPCell table9C2 = getPdfPCell(table9C2P);
			table9C2.setPadding(2);
			
			table9.addCell(table9C1);
			table9.addCell(table9C2);
			//document.add(table9);
			
			//虚线
			//document.add(getDashLineParagraph());
			
			//第10行
			PdfPTable table10 = getPdfPTable(2, new int[]{70, 30});
			
			PdfPCell table10C1 = getPdfPCell(new Phrase("内件描述/Description of Contents:", normalFont8));
			PdfPCell table10C2 = getPdfPCell(new Phrase("已验视/Visual inspection", normalFont8));
			
			table10C1.setPadding(1);
			table10C2.setPadding(1);
			
			table10.addCell(table10C1);
			table10.addCell(getPdfPCell());
			table10.addCell(getPdfPCell());
			table10.addCell(table10C2);
			//document.add(table10);
			
			//虚线
			//document.add(getDashLineParagraph());
			
			//第11行
			float table11Height = 22f;
			PdfPTable table11 = getPdfPTable(3, new int[]{30, 60, 10});
			
			Image table11Img = Image.getInstance(tbImgPath);
			table11Img.scaleToFit(80, 25);
			PdfPCell table11C1 = new PdfPCell(table11Img);
			PdfPCell table11C2 = getPdfPCell(new Phrase(orderCode, boldFont12));
			
			table11C1.setPadding(2);
			table11C1.setBorder(0);
			table11C1.setFixedHeight(table11Height);
			
			table11C2.setPadding(2);
			table11C2.setFixedHeight(table11Height);
			
			table11.addCell(table11C1);
			table11.addCell(table11C2);
			table11.addCell(getPdfPCell());
			//document.add(table11);
			
			//创建totalTable
			PdfPTable totalTable = new PdfPTable(1);
			totalTable.setWidthPercentage(100);
			
			PdfPCell tableCell1 = getPdfPTableCell(table1);
			PdfPCell tableCell2 = getPdfPTableCell(table2);
			PdfPCell tableCell3 = getPdfPTableCell(table3);
			PdfPCell tableCell45 = getPdfPTableCell(table45);
			PdfPCell tableCell6 = getPdfPTableCell(table6);
			PdfPCell tableCell7 = getPdfPTableCell(table7);
			PdfPCell tableCell8 = getPdfPTableCell(table8);
			PdfPCell tableCell9 = getPdfPTableCell(table9);
			PdfPCell tableCell10 = getPdfPTableCell(table10);
			PdfPCell tableCell11 = getPdfPTableCell(table11);
			
			setBoder(tableCell1, 2, null, 2, 2);
			setBoder(tableCell2, null, null, 2, 2);
			setBoder(tableCell3, null, null, 2, 2);
			setBoder(tableCell45, null, null, 2, 2);
			setBoder(tableCell6, null, null, 2, 2);
			setBoder(tableCell7, null, null, 2, 2);
			setBoder(tableCell8, null, null, 2, 2);
			setBoder(tableCell9, null, null, 2, 2);
			setBoder(tableCell10, null, null, 2, 2);
			setBoder(tableCell11, null, 2, 2, 2);
			
			totalTable.addCell(tableCell1);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell2);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell3);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell45);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell6);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell7);
			totalTable.addCell(tableCell8);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell9);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell10);
			totalTable.addCell(getDashLineInCell());
			totalTable.addCell(tableCell11);
			
			document.add(totalTable);
			document.close();
		} catch (Exception e) {
			System.out.println("生成失敗！");
			e.printStackTrace();

			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static PdfPTable getTable45Cell3(Font font) {
		PdfPTable table45_Cell3_table = new PdfPTable(1);

		PdfPCell table45_Cell3_table_C1 = getPdfPCell(new Phrase("□ 重量/Weight:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C2 = getPdfPCell(new Phrase("□ 价值/Value:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C3 = getPdfPCell(new Phrase("□ 重量/Weight:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C4 = getPdfPCell(new Phrase("□ 代收/Payable:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C5 = getPdfPCell(new Phrase("□ 长/L:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C6 = getPdfPCell(new Phrase("□ 宽/W:", font), Element.ALIGN_LEFT);
		PdfPCell table45_Cell3_table_C7 = getPdfPCell(new Phrase("□ 高/H:", font), Element.ALIGN_LEFT);

		table45_Cell3_table_C1.setPadding(2);
		table45_Cell3_table_C2.setPadding(2);
		table45_Cell3_table_C3.setPadding(2);
		table45_Cell3_table_C4.setPadding(2);
		table45_Cell3_table_C5.setPadding(2);
		table45_Cell3_table_C6.setPadding(2);
		table45_Cell3_table_C7.setPadding(2);

		table45_Cell3_table_C1.setPaddingBottom(1);
		table45_Cell3_table_C2.setPaddingBottom(1);
		table45_Cell3_table_C3.setPaddingBottom(1);
		table45_Cell3_table_C4.setPaddingBottom(1);
		table45_Cell3_table_C5.setPaddingBottom(1);
		table45_Cell3_table_C6.setPaddingBottom(1);
		table45_Cell3_table_C7.setPaddingBottom(1);

		table45_Cell3_table.addCell(table45_Cell3_table_C1);
		table45_Cell3_table.addCell(table45_Cell3_table_C2);
		table45_Cell3_table.addCell(table45_Cell3_table_C3);
		table45_Cell3_table.addCell(table45_Cell3_table_C4);
		table45_Cell3_table.addCell(table45_Cell3_table_C5);
		table45_Cell3_table.addCell(table45_Cell3_table_C6);
		table45_Cell3_table.addCell(table45_Cell3_table_C7);
		return table45_Cell3_table;
	}

	public static Paragraph getDashLineParagraph() {
		Paragraph p2 = new Paragraph();
		p2.add(new Chunk(new DottedLineSeparator()));
		p2.setSpacingBefore(-15);
		// p2.setSpacingAfter(-6);
		return p2;
	}

	public static PdfPCell getDashLineInCell() {
		Phrase phrase = new Phrase();
		DottedLineSeparator lineSeparator = new DottedLineSeparator();
		lineSeparator.setPercentage(98);
		phrase.add(new Chunk(lineSeparator));

		PdfPCell pdfPCell = new PdfPCell(phrase);
		pdfPCell.setBorder(0);
		pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
		pdfPCell.setPaddingTop(-10);
		// pdfPCell.setFixedHeight(10);
		setBoder(pdfPCell, null, null, 2, 2);
		return pdfPCell;
	}

	public static PdfPTable getPdfPTable(int column, int[] tableWidth) throws Exception {
		PdfPTable table = new PdfPTable(column);
		table.setWidths(tableWidth);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);
		return table;
	}

	public static PdfPCell getPdfPCell() {
		return getPdfPCell(null, null);
	}

	public static PdfPCell getPdfPCell(Phrase phrase) {
		return getPdfPCell(phrase, null);
	}

	public static PdfPCell getPdfPCell(Phrase phrase, Integer horizontalAlignment) {
		PdfPCell cell = null;
		if (phrase != null) {
			cell = new PdfPCell(phrase);
		} else {
			cell = new PdfPCell();
		}
		if (horizontalAlignment != null) {
			cell.setHorizontalAlignment(horizontalAlignment);
		}
		cell.setBorder(0);
		return cell;
	}

	public static void setBoder(PdfPCell pdfPCell, Integer top, Integer bottom, Integer left, Integer right) {
		if (top != null) {
			pdfPCell.setBorderWidthTop(top);
		}

		if (bottom != null) {
			pdfPCell.setBorderWidthBottom(bottom);
		}

		if (left != null) {
			pdfPCell.setBorderWidthLeft(left);
		}

		if (right != null) {
			pdfPCell.setBorderWidthRight(right);
		}
	}

	public static PdfPCell getPdfPTableCell(PdfPTable pdfPTable) throws Exception {
		if (pdfPTable == null) {
			throw new Exception("pdfPTable不能为空");
		}
		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.addElement(pdfPTable);
		pdfPCell.setBorder(0);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return pdfPCell;
	}

}
