INSERT INTO TB_EVENT (name, description, place, start_Date, end_Date, start_Time, end_Time, email_Contact) 
   VALUES
      ( 'NLW1', 'For React learners', 'Rocketseat', '2021-03-28', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2021', 'Projects presentation', 'zoom',  '2021-03-30', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'NLW2', 'For React learners', 'Rocketseat', '2021-03-30', '2021-04-05', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2022', 'Projects presentation', 'local',  '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'NLW3', 'For React learners', 'local', '2021-03-30', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2023', 'Projects presentation', 'local',  '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'NLW4', 'For React learners', 'youtube', '2021-03-30', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2024', 'teste', 'twitch',  '2021-04-01', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'NLW5', 'For React learners', 'twitch',  '2021-04-06', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2025', 'Projects presentation', 'twitch', '2021-04-06', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'NLW6', 'For React learners', 'Rocketseat plataform', '2021-04-06', '2021-03-10', '07:00:00', '12:00:00', 'JA@email.com'),
      ( 'TecnoFacens 2026', 'Projects presentation', 'zoom', '2021-04-06', '2021-04-10', '07:00:00', '12:00:00', 'JA@email.com');

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
