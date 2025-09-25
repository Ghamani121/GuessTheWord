create database guess__the_word;
use guess__the_word;

create table user(
	user_id int auto_increment primary key,
    username varchar(50) not null unique,
    hash_password varchar(255) not null,
    is_admin boolean default false,
    created_at datetime default current_timestamp,
    constraint username_length check (char_length(username)>=5)
);
    
create table word(
	word_id int auto_increment primary key,
    word_text char(5) not null unique,
    created_at datetime default current_timestamp,
    constraint word_uppercase check(word_text=upper(word_text))
);

create table game_session(
	session_id int auto_increment primary key,
    user_id int not null,
    word_id int not null,
    start_time datetime default current_timestamp,
    end_time datetime null,
    status enum('progress','won','lost') default 'progress',
    foreign key (user_id) references user(user_id),
    foreign key (word_id) references word(word_id)
);

CREATE TABLE guess (
    guess_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT NOT NULL,
    guess_text CHAR(5) NOT NULL,
    is_correct BOOLEAN,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES game_session(session_id),
    CONSTRAINT chk_guess_uppercase CHECK (guess_text = UPPER(guess_text))
);

INSERT INTO word (word_text) VALUES
('APPLE'), ('GRAPE'), ('MANGO'), ('LEMON'), ('BERRY'),
('PEACH'), ('PLUMS'), ('MELON'), ('CHILI'), ('OLIVE'),
('WHEAT'), ('BREAD'), ('PIZZA'), ('PASTA'), ('SUGAR'),
('HONEY'), ('SPICE'), ('SALAD'), ('CREAM'), ('JUICE');

INSERT INTO user (username, hash_password, is_admin)
VALUES ('Admin', 'admin@123', true);