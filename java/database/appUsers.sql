-- ************************************************************************************
-- This script populates the users table with sample data.
-- Users with account_type "Beer Lover" needs role 'ROLE_USER'
-- Users with account_type "Brewer" or "Administrator" needs role 'ROLE_ADMIN'
-- ************************************************************************************

INSERT INTO users (username,password_hash,role, account_type, user_location) VALUES ('mrBeer1987','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Administrator', 'Ohio');
INSERT INTO users (username,password_hash,role, account_type, user_location) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Brewer', 'Wisconsin');
INSERT INTO users (username,password_hash,role, account_type, user_location) VALUES ('beer_lover_191','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'Beer Lover', 'Illinois');