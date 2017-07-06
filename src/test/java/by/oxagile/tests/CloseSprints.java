package by.oxagile.tests;


import org.testng.annotations.Test;
import java.io.IOException;


public class CloseSprints extends TestBase {

    @Test
    public void testJira() throws InterruptedException, IOException {

        for (int i = 0; i < 30; i++) {
            app.jira().closeSprints();
        }
    }
}
