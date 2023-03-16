CREATE TABLE IF NOT EXISTS users (
	id varchar(255) PRIMARY KEY,
	email varchar(255), 
	password varchar(255),
	role varchar(20),
	created_by varchar(255),
	created_at timestamp,
	updated_by varchar(255),
	updated_at timestamp
);