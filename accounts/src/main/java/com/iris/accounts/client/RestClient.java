package com.iris.accounts.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;



/*

    public CustomResponse executeI2Request(String url, Object payload, HttpMethod method, HttpHeaders customHttpHeaders) {
        ResponseEntity<Object> responseEntity = null;
        CustomResponse response = new CustomResponse();
        HttpEntity<Object> entity = new HttpEntity<>(payload, customHttpHeaders);

        try {
            responseEntity = restTemplate.exchange(url, method, entity, Object.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if (responseEntity.getBody() != null) {
                    response = PayloadMapper.convertToResponse(responseEntity.getBody());
                }
                    response.setStatus(HttpStatus.OK);

                if (response.getMessage() == null) {
                    response.setMessage("SUCCESS");
                }
            }
        } catch (HttpStatusCodeException e) {
            response.setObject(e.getResponseBodyAsString());
            response.setMessage(e.getMessage());
            response.setStatus(Constants.Status.EXC);
        }catch (NullPointerException e) {
            response.setMessage("NullPointerException occured");
            response.setStatus(Constants.Status.EXC);
        }
        catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(Constants.Status.EXC);
        }
        if (payload instanceof Payload) {
            try {
                StringBuilder targetInterface = new StringBuilder().append(method.toString()).append(": ").append(url);
                logInfo = new LogInfo.LogInfoWSCallSentBuilder(
                        ((Payload) payload).getHeader() != null && ((Payload) payload).getHeader().getPromotedFields() != null ? StringUtil.setDefaultIfNull(((Payload) payload).getHeader().getPromotedFields().get(Constants.PromotedFieldKeys.TARGET)) : "NA",
                        targetInterface.toString(),
                        response.getMessage(),
                        ((Payload) payload).getHeader().getUid(),
                        ((Payload) payload).getHeader().getAppContentID(),
                        response.getStatus()).build();
                loggingUtil.logWSCallSent(logInfo);
            } catch (Exception e) {
                LOGGER.error("error in Logging WS Call Sent");
            }
        }
        return response;
    }

*/

}
