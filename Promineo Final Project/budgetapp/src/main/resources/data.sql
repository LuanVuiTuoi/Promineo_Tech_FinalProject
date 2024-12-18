INSERT IGNORE INTO user
(user_id, email, first_name, last_name)
VALUES(1, 'luan.nguyen@email.com', 'luan', 'nguyen');

INSERT IGNORE INTO budget_book
(budget_book_id, book_created_date, book_name, book_total_expense, book_total_income)
VALUES(1,'01-01-2024', 'Budget Book - 2024', 10.0, 100.0);

INSERT IGNORE INTO transaction
(transaction_amount, transaction_category, transaction_date, transaction_name, transaction_type, budget_book_id)
VALUES(10.0, 'Food', '01-02-2024', 'Coffee', 'Withdraw', 1);

INSERT IGNORE INTO transaction
(transaction_amount, transaction_category, transaction_date, transaction_name, transaction_type, budget_book_id)
VALUES(100.0, 'Income', '01-02-2024', 'Company Payroll', 'Deposit', 1);

INSERT IGNORE INTO user_budget_book
(user_id, budget_book_id)
VALUES(1, 1);