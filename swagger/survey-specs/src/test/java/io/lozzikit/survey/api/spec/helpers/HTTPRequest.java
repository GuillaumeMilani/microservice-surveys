package io.lozzikit.survey.api.spec.helpers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Tony on 17.11.2017.
 */
public class HTTPRequest {

    public static HTTPResponse sendPostRequest(String requestUrl, String payload, String contentType) {
        int statusCode = 0;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", contentType + "; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            statusCode = connection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();

            return new HTTPResponse(connection.getResponseCode(), jsonString.toString());
        } catch (UnsupportedEncodingException e) {
            return new HTTPResponse(statusCode, null);
        } catch (ProtocolException e) {
            return new HTTPResponse(statusCode, null);
        } catch (MalformedURLException e) {
            return new HTTPResponse(statusCode, null);
        } catch (IOException e) {
            return new HTTPResponse(statusCode, null);
        }
    }

    public static class HTTPResponse {
        private int statusCode;
        private String content;

        public HTTPResponse(int statusCode, String content) {
            this.statusCode = statusCode;
            this.content = content;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getContent() {
            return content;
        }
    }
}
