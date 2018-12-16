package com.example.demo.mapper;

import com.example.demo.model.Smestaj;
import org.springframework.beans.factory.annotation.Autowired;
import schema.wsdl.SmestajWS;

public class SmestajMapper {

    private Smestaj smestaj;
    private SmestajWS smestajWS;

    @Autowired
    public SmestajMapper(Smestaj smestaj, SmestajWS smestajWS) {
        this.smestaj = smestaj;
        this.smestajWS = smestajWS;
    }

    public Smestaj getSmestaj() {
        return smestaj;
    }

    public void setSmestaj(Smestaj smestaj) {
        this.smestaj = smestaj;
    }

    public SmestajWS getSmestajWS() {
        return smestajWS;
    }

    public void setSmestajWS(SmestajWS smestajWS) {
        this.smestajWS = smestajWS;
    }

    public SmestajWS namapiraj(){
        smestajWS.setNaziv(smestaj.getNaziv());
        smestajWS.setKategorija(smestaj.getKategorija());
        smestajWS.setOpis(smestaj.getOpis());
        smestajWS.setTip(smestaj.getTip());
        smestajWS.setUsluge(smestaj.getUsluge());

        return  smestajWS;
    }
}
