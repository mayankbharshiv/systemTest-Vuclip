package com.vuclip.premiumengg.automation.schedular_service.tests;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSUtils;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * @author rahul s
 */

public class GetJobTests {
    private static Logger logger = Log4J.getLogger("GetJobTests");

    @Test(groups = {"positive"})
    public void getAllJobsTest() {
        try {
            logger.info("get All job Test");
            Response response = SSHelper.getAllJobs();
            AppAssert.assertEqual(response.getStatusCode(), 200);
            String strResponse = response.getBody().asString();
            logger.info("RESPONSE :" + strResponse);
            AppAssert.assertTrue(strResponse.contains("JobName -> " + SSUtils.jobName), "verification for name");
            AppAssert.assertTrue(strResponse.contains("gorupName -> " + SSUtils.groupName),
                    "Verification for group name");
            logger.info("get All job Test end");
        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false, "Test case failed due to exception " + e.getMessage());
        }

    }

    @Test(groups = {"positive"}, enabled = false)
    public void runJobTest() {
        logger.info("run All job Test");
        try {
            int beforecount = JDBCTemplate.getDbConnection().queryForObject(
                    "SELECT count(*) FROM schedular.jobs_history where job_key='auto-job-job';", int.class);

            Response response = SSHelper.runJob(SSUtils.jobName, SSUtils.groupName);
            AppAssert.assertEqual(response.getStatusCode(), 200);
            String strResponse = response.getBody().asString();
            AppAssert.assertTrue(strResponse.equals("done"), "Verification for done response");
            int aftercount = JDBCTemplate.getDbConnection().queryForObject(
                    "SELECT count(*) FROM schedular.jobs_history where job_key='auto-job-job';", int.class);
            AppAssert.assertTrue(beforecount < aftercount);
            logger.info("run All job Test end");
        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false, "Test case failed due to exception " + e.getMessage());
        }
    }

}
