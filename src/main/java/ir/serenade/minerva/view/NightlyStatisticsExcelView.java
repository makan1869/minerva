package ir.serenade.minerva.view;

import ir.serenade.minerva.domain.NightlyStatistics;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class NightlyStatisticsExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"activities.xls\"");

        @SuppressWarnings("unchecked")
        List<NightlyStatistics> activities = (List<NightlyStatistics>) model.get("activities");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Activity Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Date");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Service");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Aggregator");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Book Publisher");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Publisher");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Author");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("Artist");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Count");
        header.getCell(7).setCellStyle(style);


        int rowCount = 1;

        for(NightlyStatistics activity : activities){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(activity.getDate());
            userRow.createCell(1).setCellValue(activity.getService());
            userRow.createCell(2).setCellValue(activity.getAggregator());
            userRow.createCell(3).setCellValue(activity.getBookPublisher());
            userRow.createCell(4).setCellValue(activity.getPublisher());
            userRow.createCell(5).setCellValue(activity.getAuthor());
            userRow.createCell(6).setCellValue(activity.getArtist());
            userRow.createCell(7).setCellValue(activity.getCount());
        }

    }

}