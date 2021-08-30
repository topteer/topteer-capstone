INSERT INTO top_teer_db.users  (email, first_name, last_name, password, username)

values ('billbob@place.com','bill','bob','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','billybob'),
         ('roger@place.com','Roger','Sandoval','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Roger'),
         ('rick.sanchez@place.com','Rick','Sanchez','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Rick'),
         ('morty@place.com','Morty','Smith','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Morty'),
         ('joe@place.com','Joe','Hopkins','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Joe'),
         ('jeff@place.com','Jeff','Rowe','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Jeff'),
         ('Luke@place.com','Luke','Curran','$2a$10$V/au1n2mV6378Q6wdh9XiO66kowGV0Kk2kbbdS0jRl5DMwxtfkKGW','Luke');


INSERT INTO top_teer_db.events (date, description, end_time, hours, location, phone, start_time, title, org_id, e_coordid)

values (2021,'test event 1', '1:00',6,'a place','2222222222','9:00','testing',1,1),
 (2021,'test event 1', '1:00',6,'a place','2222222222','9:00','testing',1,1),
(2021,'test event 2', '1:00',6,'a place','2222222222','9:00','testing2',1,1),
(2021,'test event 3', '1:00',6,'a place','2222222222','9:00','testing3',1,1);
