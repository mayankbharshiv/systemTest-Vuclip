package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import com.vuclip.premiumengg.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author rahul.sahu
 */
public class SSHelper {

    private static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecification() {

        if (requestSpecification == null) {
            requestSpecification = with().baseUri(Configuration.ssServer);

        }
        return requestSpecification;
    }

    public static Response getAllJobs() throws Exception {

        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .get("/job/all-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response getRunningJobs(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/running-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response getPauseJobs(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/pause-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response runJob(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/admin/run-now-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response pauseJob(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/admin/pause-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response resumeJob(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/admin/resume-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response removeJob(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/admin/remove-job.mvc");
        response.prettyPrint();
        return response;
    }

    public static Response interruptJob(String jobkeyName, String jobGroupName) throws Exception {
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("jobkeyName", jobkeyName);
        parametersMap.put("jobGroupName", jobGroupName);
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
                .queryParams(parametersMap).get("/job/admin/interrupt-job.mvc");
        response.prettyPrint();
        return response;
    }

}
