package by.oxagile.appmanager;


import by.oxagile.model.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class JiraHelper extends BaseHelper {

    public JiraHelper(WebDriver wd) {
        super(wd);
    }

    public void createProject(String projectName, String projectKey) {
        click(By.id("browse_link"));
        click(By.id("project_template_create_link_lnk"));
        wd.findElements(By.cssSelector("div.template-meta")).get(0).click();
        click(By.cssSelector("button.create-project-dialog-create-button"));
        click(By.cssSelector("button.template-info-dialog-create-button"));
        type(By.id("name"), projectName);
        wd.findElement(By.id("key")).clear();
        wd.findElement(By.id("key")).sendKeys(projectKey);

        click(By.cssSelector("button.add-project-dialog-create-button"));
    }

    public void createSprint(int quantity) throws InterruptedException {
        for (int i = 0; i < quantity; i++) {
            click(By.cssSelector("button.js-add-sprint"));
        }
    }

    public void createSprint() throws InterruptedException {
        Thread.sleep(1000);
        click(By.cssSelector("button.js-add-sprint"));
    }

    public void configureFields() throws InterruptedException {
        Thread.sleep(5000);
        click(By.cssSelector("span.aui-icon.aui-icon-small.aui-iconfont-configure"));
        click(By.id("admin_issues_menu"));
        click(By.id("view_custom_fields"));
        click(By.cssSelector("[aria-controls='field-actions-customfield_10007']"));
        click(By.id("associate_customfield_10007"));
        List<WebElement> list = wd.findElements(By.cssSelector("[name='associatedScreens']:not([checked=checked])"));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).click();
        }
        click(By.id("update_submit"));
        click(By.id("view_custom_fields"));
        click(By.cssSelector("[aria-controls='field-actions-customfield_10006']"));
        click(By.id("associate_customfield_10006"));
        List<WebElement> list2 = wd.findElements(By.cssSelector("[name='associatedScreens']:not([checked=checked])"));
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).click();
        }
        click(By.id("update_submit"));
        click(By.cssSelector("[accesskey='p']"));
        click(By.cssSelector("a#admin_main_proj_link_lnk"));

    }


    public void initStoryCreation() throws InterruptedException {
        Thread.sleep(1000);
        //init story creation
        click(By.id("create_link"));

        //select project
        /*if (!wd.findElement(By.id("project-field")).getAttribute("value").equals("test (TEST)")) {
            click(By.id("project-field"));
            click(By.id("test-(test)-13"));
        }

        //select issue type - story
        if (!wd.findElement(By.id("issuetype-field")).getAttribute("value").equals("Story")) {
            click(By.id("issuetype-field"));
            click(By.cssSelector("li.aui-list-item.aui-list-item-li-story"));
        }*/
    }

    public void fillStory(Issue issue) {

        //fill the summary of issue
        type(By.id("summary"), issue.getSummary());

        //fill the created in sprint field
        type(By.id("customfield_10007"), issue.getCreatedInSprint());

        //fill the story points field
        type(By.id("customfield_10006"), issue.getStoryPoints());

        //select the sprint number
        wd.findElement(By.id("customfield_10000-field")).sendKeys("YYYY Sprint " + issue.getSprint());
        //wd.findElement(By.id("customfield_10000-field")).click();
        //wd.findElement(By.id("[id*='sprint-" + issue.getSprint() + "-']")).click();

        //wd.findElement(By.id("customfield_10000-field")).sendKeys(Keys.ESCAPE);

        wd.findElement(By.id("customfield_10000-field")).sendKeys(Keys.ENTER);

        //create
        click(By.id("create-issue-submit"));

    }
}
