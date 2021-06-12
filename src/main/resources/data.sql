
INSERT INTO TB_USER(name, email)
   VALUES
      ( 'Admin', 'admin@email.com'),
      ( 'Admin2', 'admin2@email.com'),
      ( 'Admin3', 'admin3@email.com'),
      ( 'Raul', 'raul@email.com'),
      ( 'Ryan', 'ryan@email.com'),
      ( 'Deaque', 'deaque@email.com');
      

INSERT INTO TB_ADMIN(user_id, phone_number)
   VALUES
      ( 1, '(15) 1111-11111'),
      ( 2, '(15) 2222-22222'),
      ( 3, '(15) 3333-33333');

INSERT INTO TB_ATTEND(user_id, balance)
   VALUES
      ( 4, 400),
      ( 5, 1500),
      ( 6, 200);

INSERT INTO TB_EVENT (name, description, start_Date, end_Date, start_Time, end_Time, email_Contact, amount_free_tickets, amount_payed_tickets, price_ticket, admin_user_id) 
   VALUES
      ( 'NLW1', 'For React learners', '2021-06-14', '2021-06-22', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200, 1),
      ( 'TecnoFacens 2021', 'Projects presentation', '2021-06-25', '2021-06-30', '13:00:00', '17:00:00', 'JA@email.com', 2, 4, 200, 1),
      ( 'NLW2', 'For React learners', '2021-06-15', '2021-06-18', '17:00:00', '20:00:00', 'JA@email.com', 2, 4, 200, 2),
      ( 'TecnoFacens 2022', 'Projects presentation', '2021-06-01', '2021-06-10', '19:00:00', '22:00:00', 'JA@email.com', 2, 4, 200, 2);

INSERT INTO TB_PLACE (name, address)
   VALUES
      ('Ipero', 'Rua ipero 1'),
      ('Tatui', 'Rua tatui 2'),
      ('Sorocaba', 'Rua sorocaba 3'),
      ('Boituva', 'Rua boituva 4');

INSERT INTO TB_EVENT_PLACE (event_id, place_id)
   VALUES
      (1, 2),
      (2, 1),
      (3, 3),
      (4, 4),
      (4, 3);
