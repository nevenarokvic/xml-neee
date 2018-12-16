package com.example.demo.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import schema.wsdl.*;

public class WSClient extends WebServiceGatewaySupport {

    @Value("${producer-uri}")
    private String PRODUCER_URI = "http://localhost:8080/rest/ws";


    public LoginAgentResponse loginAgent(String username, String password) {
        LoginAgentRequest request = new LoginAgentRequest();
        request.setUsername(username);
        request.setPassword(password);

        LoginAgentResponse response = (LoginAgentResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI+"/loginAgentRequest"));
        return response;
    }

    public CreateSmestajIdReponse createSmestajId() {
        CreateSmestajIdRequest request = new CreateSmestajIdRequest();
        CreateSmestajIdReponse reponse = (CreateSmestajIdReponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/createSmestajIdRequest"));

    return reponse;
    }

    public CreateSmestajResponse createSmestaj(SmestajWS smestajWs ){
        CreateSmestajRequest request = new CreateSmestajRequest();
        request.setSmestajWS(smestajWs);
        CreateSmestajResponse response = (CreateSmestajResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/createSmestajRequest"));

        return response;

    }


}
