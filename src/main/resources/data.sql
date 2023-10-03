DROP TABLE IF EXISTS poll;

CREATE TABLE poll (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    question varchar(300) NOT NULL,
    first_answer varchar(300) NOT NULL,
    second_answer varchar(300) NOT NULL,
    third_answer varchar(300) NOT NULL,
    fourth_answer varchar(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE answers (
    user_id int(11) NOT NULL,
    poll_id int(11) NOT NULL,
    answer_number varchar(300) NOT NULL,
    PRIMARY KEY (user_id, poll_id)
);