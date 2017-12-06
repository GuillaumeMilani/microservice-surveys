package io.lozzikit.survey.api.spec.helpers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by Tony on 17.11.2017.
 */
public class HTTPRequest {
    public static CloseableHttpResponse sendPatchRequest(String requestUrl, String payload) throws IOException {
        HttpPatch updateRequest = new HttpPatch(requestUrl);
        updateRequest.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
        return HttpClients.createDefault().execute(updateRequest);
    }

    public static CloseableHttpResponse sendPostRequest(String requestUrl, String payload, ContentType contentType) throws IOException {
        HttpPost postRequest = new HttpPost(requestUrl);
        postRequest.setEntity(new StringEntity(payload, contentType));
        return HttpClients.createDefault().execute(postRequest);
    }
}
