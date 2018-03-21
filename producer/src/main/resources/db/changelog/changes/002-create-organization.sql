--liquibase formatted sql

--changeset kafkademo:1

create table organization (
    id serial not null,
    organization_name varchar(255) not null,
    address_id int not null references address (id),
    ceo_id int references employee (id) on delete set null,
    created_date timestamp default current_timestamp not null,
    last_modified_date timestamp default current_timestamp not null,
    primary key (id)
);

--rollback drop table organization;

alter table employee drop column address_id;

alter table employee add column organization_id int;

alter table employee add constraint fk_employee_organization foreign key (organization_id) references organization (id);

insert into employee (first_name, last_name) values ('Brian', 'Cornell');

insert into organization(organization_name, address_id, ceo_id) values
    ('Target', (select id from address where street_address1 = '1000 Nicollet Mall'), (select id from employee where first_name = 'Brian' and last_name = 'Cornell')),
    ('Apple', (select id from address where street_address1 = 'One Infinite Loop'), (select id from employee where first_name = 'Tim' and last_name = 'Cook'))
;

update employee set organization_id = (select id from organization where organization_name = 'Target') where first_name = 'Brian' and last_name = 'Cornell';
update employee set organization_id = (select id from organization where organization_name = 'Apple') where first_name = 'Tim' and last_name = 'Cook';