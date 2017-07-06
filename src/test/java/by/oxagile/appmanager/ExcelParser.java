package by.oxagile.appmanager;


import by.oxagile.model.Issue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {

    private File file;

    public ExcelParser(File file) {
        this.file = file;
    }


    public List<Issue> readFromExcel() throws IOException {
        String path = file.getAbsolutePath();
        List<Issue> stories = new ArrayList<>();

        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet sheet = myExcelBook.getSheetAt(0);

        int rowsNumber = sheet.getLastRowNum();

        for (int i = 1; i <= rowsNumber; i++) {

            String summary = "";
            String createdInSprit = "";
            String sprint = "";
            String storyPoints = "";
            Issue story = new Issue();

            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 4; j++) {
                XSSFCell cell = row.getCell(j);

                if (j==0) {
                    summary = cell.getStringCellValue();
                    story.withSummary(summary);
                }
                if (j==1) {
                    createdInSprit = String.valueOf((int) cell.getNumericCellValue());
                    story.withCreatedInSprint(createdInSprit);
                }
                if (j==2) {
                    sprint = String.valueOf((int) cell.getNumericCellValue());
                    story.withSprint(sprint);
                }
                if (j==3) {
                    storyPoints = String.valueOf((int) cell.getNumericCellValue());
                    story.withStoryPoints(storyPoints);
                }
            }

            stories.add(story);

        }

        myExcelBook.close();

        return stories;
    }
}