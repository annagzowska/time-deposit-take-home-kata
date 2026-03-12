insert into time_deposits (id, plan_type, days, balance) values (1, 'BASIC', 60, 1000.00);
insert into time_deposits (id, plan_type, days, balance) values (2, 'STUDENT', 120, 1500.00);
insert into time_deposits (id, plan_type, days, balance) values (3, 'PREMIUM', 50, 3000.00);

insert into withdrawals (id, time_deposit_id, amount, date) values (1, 1, 100.00, '2026-03-01');
insert into withdrawals (id, time_deposit_id, amount, date) values (2, 1, 50.00, '2026-03-05');
insert into withdrawals (id, time_deposit_id, amount, date) values (3, 3, 250.00, '2026-03-10');