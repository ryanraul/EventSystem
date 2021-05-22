INSERT INTO TB_EVENT (name, description, start_Date, end_Date, start_Time, end_Time, email_Contact, amount_free_tickets, amount_payed_tickets, price_ticket) 
   VALUES
      ( 'NLW1', 'For React learners', '2021-03-28', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      ( 'TecnoFacens 2021', 'Projects presentation', '2021-03-30', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      ( 'NLW2', 'For React learners', '2021-03-30', '2021-04-05', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      ( 'TecnoFacens 2022', 'Projects presentation', '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200);
      -- ( 'NLW3', 'For React learners', 'local', '2021-03-30', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'TecnoFacens 2023', 'Projects presentation', 'local',  '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'NLW4', 'For React learners', 'youtube', '2021-03-30', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'TecnoFacens 2024', 'teste', 'twitch',  '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'NLW5', 'For React learners', 'twitch',  '2021-04-06', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'TecnoFacens 2025', 'Projects presentation', 'twitch', '2021-04-06', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'NLW6', 'For React learners', 'Rocketseat plataform', '2021-04-06', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200),
      -- ( 'TecnoFacens 2026', 'Projects presentation', 'zoom', '2021-04-06', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com', 2, 4, 200);

INSERT INTO TB_PLACE (name, address)
   VALUES
      ('Ipero', 'Rua tal 1'),
      ('Tatui', 'Rua tal 2'),
      ('Sorocaba', 'Rua tal 3'),
      ('Boituva', 'Rua tal 4');

INSERT INTO TB_EVENT_PLACE (event_id, place_id)
   VALUES
      (1, 2),
      (2, 1),
      (3, 3),
      (4, 4);


INSERT INTO TB_USER(name, email)
   VALUES
      ( 'Raul', 'raul@email.com'),
      ( 'Ryan', 'ryan@email.com'),
      ( 'Deaque', 'deaque@email.com');

INSERT INTO TB_ADMIN(user_id, phone_number)
   VALUES
      ( 1, '(15) 9999-99999');

INSERT INTO TB_ATTENDEE(user_id, balance)
   VALUES
      ( 2, 100),
      ( 3, 300);
