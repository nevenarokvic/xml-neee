INSERT INTO user(username, password, authorities)
VALUES('user', '$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa', 'USER');

INSERT INTO user(username, password, authorities)
VALUES('admin', '$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa', 'ADMIN');

INSERT INTO smestaj(usluge, tip, kategorija,cena,ocena,naziv,opis)
VALUES('asd','asd','asd',1500, 0,'konaci','jako jedno lepo do jaja mesto');

INSERT INTO smestaj(usluge, tip, kategorija,cena,ocena,naziv,opis)
VALUES('asd','asd','asd',1500, 0,'konaci1','jako jedno lepo do jaja mesto');


INSERT INTO smestaj(usluge, tip, kategorija,cena,ocena,naziv,opis)
VALUES('asd','asd','asd',1500, 0,'konaci2','jako jedno lepo do jaja mesto');


INSERT INTO smestaj(usluge, tip, kategorija,cena,ocena,naziv,opis)
VALUES('asd','asd','asd',1500, 0,'konaci3','jako jedno lepo do jaja mesto');

INSERT INTO rezervacija(confirmed, end_date, id_smestaja,start_date,agent_id)
VALUES('false','2016-2-2',1,'2016-2-2', 1);

INSERT INTO rezervacija(confirmed, end_date, id_smestaja,start_date,agent_id)
VALUES('false','2016-2-2',1,'2016-2-2', 1);

INSERT INTO rezervacija(confirmed, end_date, id_smestaja,start_date,agent_id)
VALUES('false','2018-12-4',1,'2018-12-2', 2);

INSERT INTO rezervacija(confirmed, end_date, id_smestaja,start_date,agent_id)
VALUES('false','2018-2-2',1,'2016-2-2', 2);