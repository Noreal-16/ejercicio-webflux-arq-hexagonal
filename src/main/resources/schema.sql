create table if not exists product (
    id          SERIAL primary key,
    name    	varchar(50) not null ,
    price  		double precision,
    amount  	int not null ,
    status 		varchar(13) ,
    description  	varchar(100),
    create_at   DATE DEFAULT CURRENT_DATE
    );

create table if not exists category (
    id      		SERIAL primary key,
    name    		varchar(50) not null ,
    description  	varchar(100),
    status	        varchar(13),
    create_at   	DATE DEFAULT CURRENT_DATE
    );