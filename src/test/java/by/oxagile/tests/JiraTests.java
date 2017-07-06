package by.oxagile.tests;

import by.oxagile.model.Issue;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import by.oxagile.appmanager.ExcelParser;


public class JiraTests extends TestBase {

    @Test
    public void testJira() throws InterruptedException, IOException {

        File excelFile = new File("c:\\Users\\shukhovvg\\ebdc\\src\\test\\resources\\test.xlsx");
        ExcelParser excel = new ExcelParser(excelFile);
        List<Issue> stories = excel.readFromExcel();

        app.jira().createProject("yyyy", "YYYY");
        app.jira().configureFields();
        app.jira().createSprint(30);

        for (int i = 0; i < stories.size(); i++) {
            app.jira().initStoryCreation();
            app.jira().fillStory(stories.get(i));
        }

    }
}
