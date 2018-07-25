package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.schedular_service.common.models.EventInfo;
import com.vuclip.premiumengg.automation.schedular_service.common.models.JobMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.QueueMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author rahul.sahu
 */
public class RabbitMQHelper {
    // private static Logger logger = Log4J.getLogger("GetJobTests");

    private static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecification() {

        if (requestSpecification == null) {
            requestSpecification = with()
                    .baseUri("http://" + Configuration.rabbitMQServer + ":1" + Configuration.rabbitMQPort + "/");
            requestSpecification = requestSpecification.auth().basic(Configuration.rabbitMQUser,
                    Configuration.rabbitMQPassword);
        }
        return requestSpecification;
    }

    public static Response sendMessage(QueueMessage jsonString) throws Exception {
        String c = "%2f";
        final Response response = given(getRequestSpecification()).auth()
                .basic(Configuration.rabbitMQUser, Configuration.rabbitMQPassword).contentType(ContentType.JSON)
                .header("Authorization", "Basic Z3Vlc3Q6Z3Vlc3Q=").log()

                .all().body(jsonString).urlEncodingEnabled(false).post("/api/exchanges/" + c + "/amq.default/publish");
        response.prettyPrint();
        return response;
    }

    public static void main(String aserw[]) {

        EventInfo eventInfo = EventInfo.builder().eventId("11").eventType("JOB").logTime(new Date()).build();
        JobMessage msg = JobMessage.builder().country("IN").jobId(1L).partnerId(1L).productId(1L)
                .executingDays("1111111").activityType("DEACTIVATION_RETRY").timeWindow(null).eventInfo(eventInfo)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9991/mock/schedular")
                .queryParam("referertime", new Date().getTime()).build().toUri();
        // logger.info("Pushing Job Message at url : {}, post data is {}",targetUrl,
        // request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<JobMessage> entity = new HttpEntity<JobMessage>(msg, headers);
        ResponseEntity<Void> response = null;
        try {
            response = restTemplate.exchange(targetUrl, HttpMethod.POST, entity, Void.class);
        } catch (HttpClientErrorException e) {
            // logger.info("Error Status code : {}",e.getStatusCode());
            // logger.info("Error Response body : {}",e.getResponseBodyAsString());
            throw e;
        } catch (RuntimeException e) {
            throw e;
        }
        if (HttpStatus.OK != response.getStatusCode()) {
            // logger.info("Http Status is not 200, response : {} ",response);
            throw new RuntimeException("Not able to push message");
        } else {
            // logger.info("Success fully Pushed Job message {}", request);
        }
    }

}
