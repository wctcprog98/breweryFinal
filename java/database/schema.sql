BEGIN TRANSACTION;

DROP TABLE IF EXISTS news cascade;
DROP TABLE IF EXISTS events cascade;
DROP TABLE IF EXISTS beers cascade;
DROP TABLE IF EXISTS breweries cascade; 
DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS reviews cascade;


DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_brewery_id;
DROP SEQUENCE IF EXISTS seq_beer_id;
DROP SEQUENCE IF EXISTS seq_reviews_id;
DROP SEQUENCE IF EXISTS seq_event_id;
DROP SEQUENCE IF EXISTS seq_news_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_brewery_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_beer_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;  
CREATE SEQUENCE seq_reviews_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;  
  
CREATE SEQUENCE seq_event_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_news_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1; 
  

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
        account_type varchar(25) NOT NULL,
        user_location varchar(35),  
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE breweries (
	brewery_id int DEFAULT nextval('seq_brewery_id'::regclass) NOT NULL,
	brewery_name varchar(100) NOT NULL,
        brewer_id int,
        brewery_street_address varchar,
        brewery_city varchar,
        brewery_state varchar,
        brewery_zip int,   
        brewery_website varchar, 
        active boolean DEFAULT TRUE NOT NULL, 
	CONSTRAINT PK_brewery PRIMARY KEY (brewery_id),
	CONSTRAINT FK_brewer_id FOREIGN KEY(brewer_id) REFERENCES users(user_id)
);

CREATE TABLE beers (
        beer_id int DEFAULT nextval('seq_beer_id'::regclass) NOT NULL,
        beer_name varchar(75) NOT NULL,
        beer_style varchar(50) NOT NULL,
        beer_description varchar(500) NOT NULL,
        beer_abv numeric,
        brewery_id int NOT NULL,
        image varchar(255) default null,
        active boolean DEFAULT TRUE NOT NULL,
        CONSTRAINT PK_beer PRIMARY KEY (beer_id),
        CONSTRAINT FK_brewery_id FOREIGN KEY(brewery_id) REFERENCES breweries(brewery_id)
);
CREATE TABLE reviews (
        reviews_id int DEFAULT nextval('seq_reviews_id'::regclass) NOT NULL,
        beer_id int,
        reviewer_id int NOT NULL,
        review_text varchar(5000) NOT NULL,
        star_rating numeric,
        active boolean DEFAULT TRUE NOT NULL,
        CONSTRAINT PK_reviews_id PRIMARY KEY (reviews_id),
        CONSTRAINT FK_beer_id FOREIGN KEY(beer_id) REFERENCES beers(beer_id),
        CONSTRAINT FK_reviewer_id FOREIGN KEY(reviewer_id) REFERENCES users(user_id)      
        
);


CREATE TABLE events (
        event_id int DEFAULT nextval('seq_event_id'::regclass) NOT NULL,
        event_name varchar(75) NOT NULL,
        event_date DATE NOT NULL,
        event_time TIME,
        event_description varchar(500) NOT NULL,
        brewery_id int NOT NULL,
        active boolean DEFAULT TRUE NOT NULL,
        CONSTRAINT FK_brewery_id FOREIGN KEY(brewery_id) REFERENCES breweries(brewery_id)
);

CREATE TABLE news (
        news_id int DEFAULT nextval('seq_news_id'::regclass) NOT NULL,
        news_name varchar(200) NOT NULL,
        news_body varchar(5000) NOT NULL,
        brewery_id int NOT NULL,
        active boolean DEFAULT TRUE NOT NULL,
        CONSTRAINT FK_brewery_id FOREIGN KEY(brewery_id) REFERENCES breweries(brewery_id)
);


INSERT INTO users (username,password_hash,role, account_type, user_location) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'Beer Lover', 'Ohio');
INSERT INTO users (username,password_hash,role, account_type, user_location) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Administrator', 'Texas');

COMMIT TRANSACTION;

