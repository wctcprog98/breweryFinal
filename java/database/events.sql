-- ************************************************************************************
-- This script populates the events table with sample data (brewery_id must exist)
-- ************************************************************************************

INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Nitro Coconut Tasting', '2021-06-05', '14:00:00', 'Come sample our newest brew! Ale with sea salt, dark chocolate, and natural chocolate & coconut flavors.', 1);

INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Blueberry Maple Stout Tasting', '2021-06-04', '16:00:00', 'Come sample our newest brew! Rich, sweet stout with classic malt characteristics with a bold, unique twist of blueberry and maple.', 2);

INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Ottermelon Hefeweizen Tasting', '2021-06-05', '16:00:00', 'Come sample our newest brew! This Pale Ale is heavily dosed with Mosaic hops.', 3);
INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Hazy Ipa Tasting', '2021-06-04', '17:00:00', 'Come sample our newest brew! A soft, rounded mouthfeel asserts itself in this unfiltered IPA, loaded with newfangled hop varieties.', 4);

INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Giant Pacific Octopus Tasting', '2021-06-11', '17:00:00', 'Come sample our newest brew! The taste is of citrus (especially grapefruit, some lemon) with piney/floral spicing.', 5);
INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Delicious IPA Tasting', '2021-06-12', '18:00:00', 'Come sample our newest brew! An IPA that lives up to the name.', 6);

INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id)
VALUES ('Beer Tasting Fundraiser', '2021-06-19', '18:00:00', 'Come help us support local buisness', 7);
