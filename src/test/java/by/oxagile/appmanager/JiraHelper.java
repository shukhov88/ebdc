package by.oxagile.appmanager;


import by.oxagile.model.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class JiraHelper extends BaseHelper {

    public JiraHelper(WebDriver wd) {
        super(wd);
    }

    public void createProject(String projectName) throws InterruptedException {
        click(By.id("browse_link"));
        click(By.id("project_template_create_link_lnk"));
        wd.findElements(By.cssSelector("div.template-meta")).get(0).click();
        click(By.cssSelector("button.create-project-dialog-create-button"));
        click(By.cssSelector("button.template-info-dialog-create-button"));
        type(By.id("name"), projectName);
        click(By.cssSelector("button.add-project-dialog-create-button"));
    }

    public void createSprint(int quantity) throws InterruptedException {
        for (int i = 0; i < quantity; i++) {
            Thread.sleep(1000);
            click(By.cssSelector("button.js-add-sprint"));
        }
    }

    public void createSprint() throws InterruptedException {
        Thread.sleep(2000);
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
        Thread.sleep(1500);
        click(By.id("create_link"));
    }

    public void fillStory(Issue issue) throws InterruptedException {

        type(By.id("summary"), issue.getSummary());
        type(By.id("customfield_10007"), issue.getCreatedInSprint());
        type(By.id("customfield_10006"), issue.getStoryPoints());

        //select the sprint number
        wd.findElement(By.id("customfield_10000-field")).sendKeys("WEED Sprint " + issue.getSprint());
        //wd.findElement(By.id("customfield_10000-field")).click();
        Thread.sleep(500);
        //wd.findElement(By.cssSelector("[id*='sprint-" + issue.getSprint() + "-']")).click();

        //wd.findElement(By.id("customfield_10000-field")).sendKeys(Keys.ESCAPE);

        wd.findElement(By.id("customfield_10000-field")).sendKeys(Keys.ENTER);

        //create
        click(By.id("create-issue-submit"));
    }

    public void closeSprints() throws InterruptedException {
        Thread.sleep(1000);
        click(By.cssSelector("button.js-sprint-start.aui-button.aui-button-primary"));
        click(By.cssSelector("button.button-panel-button.aui-button"));
        int storiesToDrag = wd.findElements(By.cssSelector("li.ghx-column.ui-sortable:first-child div.js-detailview.ghx-issue")).size();

        for (int i = 0; i < storiesToDrag; i++) {
            List<WebElement> targetsList = wd.findElements(By.cssSelector("li.ghx-column.ui-sortable:first-child div.js-detailview.ghx-issue"));
            WebElement source = wd.findElement(By.cssSelector("li.ghx-column.ui-sortable:nth-child(3)"));
            dragAndDrop(targetsList.get(0), source);
            Thread.sleep(2000);
        }
        Thread.sleep(1000);
        click(By.id("ghx-complete-sprint"));
        click(By.cssSelector("button.button-panel-button.aui-button.ghx-complete-button"));
        click(By.cssSelector("span.aui-icon.aui-icon-large.agile-icon-plan"));
    }
}
