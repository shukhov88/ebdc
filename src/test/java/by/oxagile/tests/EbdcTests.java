package by.oxagile.tests;

import by.oxagile.model.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import by.oxagile.appmanager.ExcelParser;


public class EbdcTests extends TestBase {

    @Test
    public void testJira() throws InterruptedException, IOException {

        File excelFile = new File("c:\\Users\\shukhovvg\\ebdc\\src\\test\\resources\\test.xlsx");
        ExcelParser excel = new ExcelParser(excelFile);
        List<Issue> stories = excel.readFromExcel();

        app.jira().createProject("atir");
        app.jira().configureFields();
        //app.jira().createSprint(30);

        int counter = 0;

        for (int i = 0; i < stories.size(); i++) {
            if (i==0 || i%3==0) {
                app.jira().createSprint();
                counter++;
            }
            app.jira().initStoryCreation();
            app.jira().fillStory(stories.get(i));
        }

        for (int i = 0; i < counter; i++) {
            app.jira().closeSprints();
        }
    }
}
