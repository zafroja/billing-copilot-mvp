package com.billingcopilot.provider.jobber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Map;
@Component
public class JobberGraphQlClient {
 private final RestClient client;
 private final String apiVersion;
 public JobberGraphQlClient(RestClient.Builder builder,
   @Value("${jobber.graphql-url:https://api.getjobber.com/api/graphql}") String url,
   @Value("${jobber.api-version:2026-05-12}") String apiVersion) {
   this.client=builder.baseUrl(url).build(); this.apiVersion=apiVersion;
 }
 public String execute(String accessToken, String query, Map<String,Object> variables){
  return client.post().contentType(MediaType.APPLICATION_JSON)
    .header("Authorization","Bearer "+accessToken)
    .header("X-JOBBER-GRAPHQL-VERSION",apiVersion)
    .body(Map.of("query",query,"variables",variables==null?Map.of():variables))
    .retrieve().body(String.class);
 }
}
