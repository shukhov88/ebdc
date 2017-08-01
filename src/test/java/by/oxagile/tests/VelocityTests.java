package by.oxagile.tests;


import by.oxagile.appmanager.ExcelParser;
import by.oxagile.model.Issue;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class VelocityTests extends TestBase {

    @Test
    public void testJira() throws InterruptedException, IOException {

        File excelFile = new File("c:\\Users\\shukhovvg\\ebdc\\src\\test\\resources\\testVelocity.xlsx");
        ExcelParser excel = new ExcelParser(excelFile);
        List<Issue> stories = excel.readFromVelocityExcel();

        app.jira().createProject("atir");
        app.jira().configureVelocityFields();

        int sprintNum = 0;

        for (int i = 0; i < stories.size(); i++) {
            if (i==0 || i%5==0) {
                app.jira().createSprint();
                sprintNum++;
            }
            app.jira().initStoryCreation();
            app.jira().fillStoryVelocity(stories.get(i), sprintNum);
        }

        for (int i = 0; i < sprintNum; i++) {
            app.jira().closeSprints();
        }
    }
}
