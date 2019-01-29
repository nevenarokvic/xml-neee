package com.example.demo.mapper;

import com.example.demo.model.Smestaj;
import org.springframework.beans.factory.annotation.Autowired;
import schema.wsdl.SmestajWS;

public class SmestajMapperS2SWS {

        private Smestaj smestaj;
        private SmestajWS smestajWS;

        @Autowired
        public SmestajMapperS2SWS(Smestaj smestaj, SmestajWS smestajWS) {
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

        public Smestaj namapiraj(){
            smestaj.setNaziv(smestajWS.getNaziv());
            smestaj.setKategorija(smestajWS.getKategorija());
            smestaj.setOpis(smestajWS.getOpis());
            smestaj.setTip(smestajWS.getTip());
            smestaj.setUsluge(smestajWS.getUsluge());
            smestaj.setId(smestajWS.getId());

            return  smestaj;
        }


}
