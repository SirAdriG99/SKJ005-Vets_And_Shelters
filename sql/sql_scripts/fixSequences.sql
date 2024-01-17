SELECT setval('animal_id_seq', (SELECT MAX(id) FROM animal)+1);
SELECT setval('animal_photos_id_seq', (SELECT MAX(id) FROM animal_photos)+1);
SELECT setval('breed_id_seq', (SELECT MAX(id) FROM breed)+1);
SELECT setval('customer_id_seq', (SELECT MAX(id) FROM customer)+1);
SELECT setval('customer_pwd_id_seq', (SELECT MAX(id) FROM customer_pwd)+1);