--liquibase formatted sql

--changeset kafkademo:1

create table address (
    id serial not null,
    street_address1 varchar(255) not null,
    street_address2 varchar(255),
    city varchar(255),
    state varchar(255),
    country varchar(255),
    zip_code varchar(100),
    created_date timestamp default current_timestamp not null,
    last_modified_date timestamp default current_timestamp not null,
    primary key (id)
);



insert into address (street_address1, city, state, zip_code, country) values
    ('11000 Optum Circle', 'Eden Prairie', 'Minnesota', '55344', 'United States'), -- Optum HQ
    ('9900 Bren Rd E', 'Minnetonka', 'Minnesota', '55343', 'United States'), -- UnitedHealth Group HQ
    ('1000 Nicollet Mall', 'Minneapolis', 'Minnesota', '55403', 'United States'), -- Target HQ
    ('7601 Penn Ave S', 'Richfield', 'Minnesota', '55423', 'United States'), -- Best Buy HQ
    ('3M Center', 'St. Paul', 'Minnesota', '55144-1000', 'United States'), -- 3M HQ
    ('800 Nicollet Mall', 'Minneapolis', 'Minnesota', '55402', 'United States'), -- US Bancorp HQ
    ('1 General Mills Blvd', 'Minneapolis', 'Minnesota', '55426-1347', 'United States'), -- General Mills HQ
    ('1 Ecolab Place', 'St. Paul', 'Minnesota', '55102', 'United States'), -- Ecolab HQ
    ('702 S.W. Eighth St.', 'Bentonville', 'Arkansas', '72716', 'United States'), -- Walmart HQ
    ('420 Montgomery Street', 'San Francisco', 'California', '94104', 'United States'), -- Wells Fargo HQ
    ('One Infinite Loop', 'Cupertino', 'California', '95014', 'United States'), -- Apple HQ
    ('One Microsoft Way', 'Redmond', 'Washington', '98052-6399', 'United States'), -- Microsoft HQ
    ('440 Terry Avenue North', 'Seattle', 'Washington', '98109', 'United States') -- Amazon HQ
;

--rollback drop table address;

--changeset kafkademo:2

create table employee (
    id serial not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    address_id int references address (id),
    created_date timestamp default current_timestamp not null,
    last_modified_date timestamp default current_timestamp not null,
    primary key (id)
);

insert into employee (first_name, last_name, address_id) values
    ('Bill', 'Gates', (select id from address where street_address1 = 'One Microsoft Way')),
    ('Tim', 'Cook', (select id from address where street_address1 = 'One Infinite Loop')),
    ('Bulent', 'Ugurlu', null),
    ('Mark', 'Eyberg', null)
;

--rollback drop table employee;
