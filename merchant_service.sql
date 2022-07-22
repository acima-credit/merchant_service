-- DROP DATABASE IF EXISTS merchant_service;

-- CREATE DATABASE merchant_service;

-- Extension: uuid-ossp

CREATE EXTENSION IF NOT EXISTS "uuid-ossp"
	SCHEMA "public"
	VERSION "1.1";

-- Drop Foreign Key Constraints
DO $$
DECLARE r record;
BEGIN
	FOR r IN (SELECT table_schema, table_name, constraint_name FROM information_schema.table_constraints WHERE constraint_schema = 'public' AND constraint_type = 'FOREIGN KEY' ) 
	LOOP 
		RAISE info '%','dropping '||r.constraint_name;
		execute CONCAT('ALTER TABLE ' || quote_ident(r.table_schema) || '.' || quote_ident(r.table_name) || ' DROP CONSTRAINT ' || quote_ident(r.constraint_name));
	END LOOP;
END$$;

-- Drop tables it they exist

DROP TABLE IF EXISTS public.merchant_industries;
DROP TABLE IF EXISTS public.location_phones;
DROP TABLE IF EXISTS public.location_users;
DROP TABLE IF EXISTS public.merchant_location;
DROP TABLE IF EXISTS public.district;
DROP TABLE IF EXISTS public.merchant_agreements;
DROP TABLE IF EXISTS public.merchant;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.roles;
DROP TABLE IF EXISTS public.email;
DROP TABLE IF EXISTS public.assigned_representatives;
DROP TABLE IF EXISTS public.agreement;
DROP TABLE IF EXISTS public.address;
DROP TABLE IF EXISTS public.phone;
DROP TABLE IF EXISTS public.phone_type;
DROP TABLE IF EXISTS public.industry;
DROP TABLE IF EXISTS public.signing_method;
DROP TABLE IF EXISTS public.sources;

-- Table definition

CREATE TABLE public.sources (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	display_name varchar NOT NULL,
	CONSTRAINT source_pk PRIMARY KEY (id)
);

CREATE TABLE public.signing_method (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	display_name varchar NOT NULL,
	CONSTRAINT signing_method_pk PRIMARY KEY (id)
);

CREATE TABLE public.industry (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	display_name varchar NOT NULL,
	primary_industry boolean NOT NULL,
	CONSTRAINT industy_pk PRIMARY KEY (id)
);

CREATE TABLE public.phone_type (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	display_name varchar NOT NULL,
	CONSTRAINT phone_type_pk PRIMARY KEY (id)
);

CREATE TABLE public.phone (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	phone_number varchar NOT NULL,
	phone_type_id uuid NOT NULL,
	primary_phone bool NOT NULL DEFAULT false,
	CONSTRAINT phone_pk PRIMARY KEY (id)
);

CREATE TABLE public.address (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deleted_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	street_1 varchar NOT NULL,
	street_2 varchar NULL,
	apt_number varchar NULL,
	city varchar NOT NULL,
	zipcode varchar NOT NULL,
	state_code varchar NOT NULL,
	latitude varchar NULL,
	longitude varchar NULL,
	CONSTRAINT address_pk PRIMARY KEY (id)
);

CREATE TABLE public.agreement(
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	signing_method_id uuid NULL,
	signed_at timestamp NULL,
	signed_name varchar NULL, 
	signed_title varchar NULL, 
	signed_agreement_url varchar NULL,
	CONSTRAINT agreement_pk PRIMARY KEY (id)
);

CREATE TABLE public.assigned_representatives (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	merchant_id uuid NOT NULL,
	assigned_position varchar NOT NULL,
	employee_id uuid NOT NULL,
	CONSTRAINT assigned_representatives_pk PRIMARY KEY (id)
);

CREATE TABLE public.email (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	email_address varchar NOT NULL,
	CONSTRAINT email_pk PRIMARY KEY (id)
);

CREATE TABLE public.roles (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	value varchar NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE public.users (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);

 CREATE TABLE public.merchant (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	denied_at timestamp NULL,
	legal_business_name varchar NOT NULL,
	display_name varchar NULL,
	primary_address_id uuid NULL,
	primary_phone_id uuid NULL,
	year_established int4 NULL,
	email_id uuid NULL,
	website_url varchar NULL,
	funding_email_id uuid NULL,
	primary_industry_id uuid NULL,
	buying_group_id uuid NULL,
	primary_rep_id uuid NULL,
	source_id uuid NULL,
	application_short_link varchar NULL,
	tax_id_number varchar NULL,
	acima_merchant_guid varchar NULL,
	CONSTRAINT merchant_pk PRIMARY KEY (id)
);

CREATE TABLE public.merchant_agreements (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	merchant_id uuid NOT NULL,
	agreement_id uuid NOT NULL,
	CONSTRAINT merchant_agreements_pk PRIMARY KEY (id)
);

CREATE TABLE public.district (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	display_name varchar NOT NULL,
	merchant_id uuid NOT NULL,
	CONSTRAINT district_pk PRIMARY KEY (id)
);

CREATE TABLE public.merchant_location (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	display_name varchar NOT NULL,
	district_id uuid NOT NULL,
	address_id uuid NOT NULL,
	CONSTRAINT merchant_location_pk PRIMARY KEY (id)
);

CREATE TABLE public.location_users (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	location_id uuid NOT NULL,
	user_id uuid NOT NULL,
	role_id uuid NOT NULL,
	CONSTRAINT location_users_pk PRIMARY KEY (id)
);

CREATE TABLE public.location_phones (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	merchant_location_id uuid NOT NULL,
	phone_id uuid NOT NULL,
	is_primary boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT location_phone_pk PRIMARY KEY (id)
);

CREATE TABLE public.merchant_industries (
	id uuid NOT NULL DEFAULT uuid_generate_v1(),
	created_at timestamp DEFAULT now(),
	updated_at timestamp NOT NULL,
	deactivated_at timestamp NULL,
	deleted_at timestamp NULL,
	merchant_id uuid NOT NULL,
	industry_id uuid NOT NULL,
	CONSTRAINT merchant_industries_pk PRIMARY KEY (id)
);

-- Foreign Keys

ALTER TABLE public.phone  ADD CONSTRAINT phone_fk_phone_type FOREIGN KEY (phone_type_id) REFERENCES phone_type(id);
ALTER TABLE public.agreement ADD CONSTRAINT agreement_fk_signing_method FOREIGN KEY (signing_method_id) REFERENCES signing_method(id);
ALTER TABLE public.assigned_representatives ADD CONSTRAINT assigned_represenatives_fk_merchant FOREIGN KEY (merchant_id) REFERENCES merchant(id);
ALTER TABLE public.merchant ADD CONSTRAINT merchant_fk_address FOREIGN KEY (primary_address_id) REFERENCES public.address(id);
ALTER TABLE public.merchant ADD CONSTRAINT merchant_fk_phone FOREIGN KEY (primary_phone_id) REFERENCES public.phone(id);
ALTER TABLE public.merchant ADD CONSTRAINT merchant_fk_primary_email FOREIGN KEY (email_id) REFERENCES public.email(id);
ALTER TABLE public.merchant ADD CONSTRAINT merchant_fk_funding_email FOREIGN KEY (funding_email_id) REFERENCES public.email(id);  
ALTER TABLE public.merchant ADD CONSTRAINT merchant_fk_source FOREIGN KEY (source_id) REFERENCES public.sources(id);
ALTER TABLE public.merchant_agreements ADD CONSTRAINT merchant_agreements_fk_merchant FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
ALTER TABLE public.merchant_agreements ADD CONSTRAINT merchant_agreements_fk_agreement FOREIGN KEY (agreement_id) REFERENCES public.agreement(id);
ALTER TABLE public.district ADD CONSTRAINT district_fk_merchant FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
ALTER TABLE public.merchant_location ADD CONSTRAINT merchant_location_fk_district FOREIGN KEY (district_id) REFERENCES public.district(id);
ALTER TABLE public.merchant_location ADD CONSTRAINT merchant_location_fk_address FOREIGN KEY (address_id) REFERENCES public.address(id);
ALTER TABLE public.location_users ADD CONSTRAINT location_users_fk_location FOREIGN KEY (location_id) REFERENCES public.merchant_location(id);
ALTER TABLE public.location_users ADD CONSTRAINT location_users_fk_user FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public.location_users ADD CONSTRAINT loation_users_fk_role FOREIGN KEY (role_id) REFERENCES public.roles(id);
ALTER TABLE public.location_phones ADD CONSTRAINT location_phones_fk_location FOREIGN KEY (merchant_location_id) REFERENCES public.merchant_location(id);
ALTER TABLE public.location_phones ADD CONSTRAINT location_phones_fk_phone FOREIGN KEY (phone_id) REFERENCES public.phone(id);
ALTER TABLE public.merchant_industries ADD CONSTRAINT merchant_industries_fk_merchant FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
ALTER TABLE public.merchant_industries ADD CONSTRAINT merchant_industries_fk_industry FOREIGN KEY (industry_id) REFERENCES public.industry(id);