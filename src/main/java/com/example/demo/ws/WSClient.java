package com.example.demo.ws;

import com.example.demo.mapper.SmestajMapperS2SWS;
import com.example.demo.model.Smestaj;
import com.example.demo.model.SmestajPhoto;
import com.example.demo.model.User;
import com.example.demo.service.SmestajPhotoService;
import com.example.demo.service.SmestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import schema.wsdl.*;

import java.util.ArrayList;
import java.util.List;

public class WSClient extends WebServiceGatewaySupport {

    @Value("${producer-uri}")
    private String PRODUCER_URI = "localhost:8089/rest/ws";
    @Autowired
    SmestajService smestajService;

    @Autowired
    SmestajPhotoService smestajPhotoService;
/////////////////////////////LOGIN////////////////////////////////////////////////////////////////////////////////
    public LoginAgentResponse loginAgent(String username, String password) {
        LoginAgentRequest request = new LoginAgentRequest();
        request.setUsername(username);
        request.setPassword(password);

        LoginAgentResponse response = (LoginAgentResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/loginAgentRequest"));
        return response;
    }

///////////////////////////////SMESTAJ///////////////////////////////////////////////////////////////////////////////

    public CreateSmestajIdReponse createSmestajId() {
        CreateSmestajIdRequest request = new CreateSmestajIdRequest();
        CreateSmestajIdReponse reponse = (CreateSmestajIdReponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/createSmestajIdRequest"));

        return reponse;
    }

    public CreateSmestajResponse createSmestaj(SmestajWS smestajWs) {
        CreateSmestajRequest request = new CreateSmestajRequest();
        request.setSmestajWS(smestajWs);
        CreateSmestajResponse response = (CreateSmestajResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/createSmestajRequest"));

        return response;

    }

    public GetSmestajForUserResponse getSmestaj(Long agentId) {
        GetSmestajForUserRequest request = new GetSmestajForUserRequest();
        request.setId(agentId);
        GetSmestajForUserResponse response = (GetSmestajForUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/getSmestajForUserRequest"));
        return response;

    }

    public void syncSmestaji() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GetSmestajForUserResponse getAllSmestaj = this.getSmestaj(authUser.getId());

        List<Smestaj> smestajs = new ArrayList<Smestaj>();
        List<SmestajWS> smestajsWS = getAllSmestaj.getSmestajs();
        for (SmestajWS smestajWS : smestajsWS) {//NAMAPIRATI SVE
            Smestaj s = new Smestaj();
            s.setAgent(authUser);
            s.setId(smestajWS.getId());
            SmestajMapperS2SWS sm = new SmestajMapperS2SWS(s, smestajWS);
            sm.namapiraj();
            smestajs.add(s);
        }
        smestajService.addAllSmestaj(smestajs);
    }
////////////////////////////////SLIKE//////////////////////////////////////////////////////////////////////////////

    public CreatePhotoResponse createPhoto(PhotoWS photoWS) {
        CreatePhotoRequest request = new CreatePhotoRequest();
        Smestaj s = smestajService.findBySmestajId(photoWS.getSmestajId());
        request.setSmestajId(s.getId());
        request.setPhoto(photoWS.getPath());
        CreatePhotoResponse response = (CreatePhotoResponse) getWebServiceTemplate()
                .marshalSendAndReceive(PRODUCER_URI, request,
                        new SoapActionCallback(PRODUCER_URI + "/createPhotoRequest"));
        return response;
    }

//getAll photos, sync photos, delete(mora prvo getAll da postoji zbog trazenja po id-u



}






