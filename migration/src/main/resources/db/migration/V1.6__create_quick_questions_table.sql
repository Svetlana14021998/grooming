create table quick_questions
(
    id           BIGSERIAL PRIMARY KEY,

    rus_question VARCHAR(500) NOT NULL,
    eng_question VARCHAR(500) NOT NULL,
    rus_answer   TEXT         NOT NULL,
    eng_answer   TEXT         NOT NULL,
    category     VARCHAR(50)  NOT NULL
);
