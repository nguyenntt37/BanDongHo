/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.ChiTietSanPham;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import viewmodel.ChiTietSPCustom;

/**
 *
 * @author asus_vinh
 */
public class ExportExcelChiTietSP {

    public static final int COLUMN_MA = 0;
    public static final int COLUMN_TEN = 1;
    public static final int COLUMN_NBH = 2;
    public static final int COLUMN_ThuongHieu = 3;
    public static final int COLUMN_MAU = 4;
    public static final int COLUMN_NSX = 5;
    public static final int COLUMN_SOLUONG = 6;
    public static final int COLUMN_GIANHAP = 7;
    public static final int COLUMN_GIABAN = 8;
    public static final int COLUMN_MOTA = 9;
    public static final int COLUMN_NGAYTAO = 10;
    public static final int COLUMN_NGAYSUA = 11;
    public static final int COLUMN_TRANGTHAI = 12;
    public static final int COLUMN_XuatXu = 13;
    public static final int COLUMN_Kinh = 14;
    public static final int COLUMN_DayDeo = 15;
    public static final int COLUMN_ChucNang = 16;

    public static void writeExcel(List<ChiTietSPCustom> list, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        // Create sheet
        Sheet sheet = workbook.createSheet("Chi Tiết Sản Phẩm");
        int rowIndex = 0;
        // Write header
        writeHeader(sheet, rowIndex);
        // Write data
        rowIndex++;
        for (ChiTietSPCustom ctsps : list) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(ctsps, row);
            rowIndex++;
        }
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleHeader(sheet);

        Row row = sheet.createRow(rowIndex);

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã CTSP");

        Cell cel = row.createCell(COLUMN_TEN);
        cel.setCellStyle(cellStyle);
        cel.setCellValue("Tên sản phẩm");

        cell = row.createCell(COLUMN_NBH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Năm Bảo Hàng");

        cell = row.createCell(COLUMN_ThuongHieu);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thương hiệu");

        cell = row.createCell(COLUMN_MAU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Màu sắc");

        cell = row.createCell(COLUMN_NSX);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Nhà sản xuất");

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số lượng");

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá nhập");

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giá bán");

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mô tả");

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày thêm");

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày sửa");

        cell = row.createCell(COLUMN_TRANGTHAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trạng thái");
        
        
        cell = row.createCell(COLUMN_XuatXu);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Xuất xứ");
        
        
        cell = row.createCell(COLUMN_Kinh);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Loại Kính");
        
        
        cell = row.createCell(COLUMN_DayDeo);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Dây Đeo");
        
        
        cell = row.createCell(COLUMN_ChucNang);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chức năng");

    }

    private static void writeBook(ChiTietSPCustom ctsp, Row row) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Cell cell = row.createCell(COLUMN_MA);
        cell.setCellValue(ctsp.getMaSP());

        Cell cel = row.createCell(COLUMN_TEN);
        cel.setCellValue(ctsp.getTenSP());

        cell = row.createCell(COLUMN_NBH);
        cell.setCellValue(ctsp.getNBH());

        cell = row.createCell(COLUMN_ThuongHieu);
        cell.setCellValue(ctsp.getThuongHieu());

        cell = row.createCell(COLUMN_MAU);
        cell.setCellValue(ctsp.getMau());

        cell = row.createCell(COLUMN_NSX);
        cell.setCellValue(ctsp.getNSX());

        cell = row.createCell(COLUMN_SOLUONG);
        cell.setCellValue(ctsp.getSoLuongTon());

        cell = row.createCell(COLUMN_GIANHAP);
        cell.setCellValue(ctsp.getGiaNhap().doubleValue());

        cell = row.createCell(COLUMN_GIABAN);
        cell.setCellValue(ctsp.getGiaBan().doubleValue());

        cell = row.createCell(COLUMN_MOTA);
        cell.setCellValue(ctsp.getMoTa());

        cell = row.createCell(COLUMN_NGAYTAO);
        cell.setCellValue(ctsp.getNgayTao());

        cell = row.createCell(COLUMN_NGAYSUA);
        cell.setCellValue(ctsp.getNgaySua());
      
        
         cell = row.createCell(COLUMN_XuatXu);
        cell.setCellValue(ctsp.getXuatXu());
        
         cell = row.createCell(COLUMN_Kinh);
        cell.setCellValue(ctsp.getKinh());
        
         cell = row.createCell(COLUMN_DayDeo);
        cell.setCellValue(ctsp.getDayDeo());
        
         cell = row.createCell(COLUMN_ChucNang);
        cell.setCellValue(ctsp.getChucNang());
    }

    // Create CellStyle for header
    private static CellStyle createStyleHeader(Sheet sheet) {
        // Create font
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 13); // font size
        font.setColor(IndexedColors.RED.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try ( OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
    
    public static void main(String[] args) {
//        List<ChiTietSanPham> list = new ArrayList<>();
//       
//            final List<ChiTietSanPham> books = getBooks();
//        final String excelFilePath = "C:/demo/books.xlsx";
//        writeExcel(books, excelFilePath);
        
    }
}
