CREATE SCHEMA IF NOT EXISTS gestion;
CREATE SEQUENCE IF NOT EXISTS gestion.plot_id_seq;
CREATE SEQUENCE IF NOT EXISTS gestion.plotconfig_id_seq;
CREATE SEQUENCE IF NOT EXISTS gestion.crop_id_seq;
CREATE SEQUENCE IF NOT EXISTS gestion.region_id_seq;
CREATE SEQUENCE IF NOT exists gestion.slot_id_seq;

CREATE TABLE IF NOT EXISTS gestion.crop(
    id integer NOT NULL DEFAULT nextval('gestion.crop_id_seq'),
    crop varchar(50),
    water_m3_by_area numeric(4,2),
    PRIMARY KEY (id));
   
CREATE TABLE IF NOT EXISTS gestion.irrigation_type(
    id integer NOT NULL,
    description varchar(20),
    PRIMARY KEY (id)); 
   
CREATE TABLE IF NOT EXISTS gestion.weather(
    id integer NOT NULL,
    description varchar(20) NOT NULL,
    hu_by_Area numeric(4,2) NOT NULL,
    PRIMARY KEY (id));    
     
CREATE TABLE IF NOT EXISTS gestion.region(
    id integer NOT NULL,
    region varchar(20),
    id_weather integer NOT NULL references gestion.weather,
    PRIMARY KEY (id)); 
 
   
 
CREATE TABLE IF NOT EXISTS gestion.plot_configuration(
    id integer  NOT NULL DEFAULT nextval('gestion.plotconfig_id_seq'),
    id_irrigation_type integer NOT null REFERENCES gestion.irrigation_type (id),
    duration_minutes integer,
    slot_time integer,
    amount_water_liters numeric(4,2),
    is_active boolean not null default false,
    is_automatic boolean not null default true,
    PRIMARY KEY (id));   

   
CREATE TABLE IF NOT EXISTS gestion.plot(
    id integer  NOT NULL DEFAULT nextval('gestion.plot_id_seq'),
    identifier varchar(20) NOT NULL UNIQUE,
    id_region integer NOT null REFERENCES gestion.region (id),
    id_configuration integer NOT null REFERENCES gestion.plot_configuration (id),
    id_crop integer NOT null REFERENCES gestion.crop (id),
    area numeric(10,2),
    PRIMARY KEY (id));
   

CREATE TABLE IF NOT EXISTS gestion.time_slot(
    id integer  NOT NULL DEFAULT nextval('gestion.slot_id_seq'),
    id_plot  integer NOT null REFERENCES gestion.plot (id),
    time_start timestamp with time zone,
    time_end timestamp with time zone,
    status integer NOT NULL default 0,
    PRIMARY KEY (id));  
   


 --INSERT CROP    
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Alfafa',9.2
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Alfafa'); 
       
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Apples',8.5
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Apples'); 

INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Apricote', 10.25
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Apricote'); 

INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Avocado', 0.2
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Avocado');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Barley', 7.6
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Barley');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Beans', 4.3
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Beans');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Cherry', 1.7
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Cherry');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Citrus', 1.4
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Citrus');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Cotton', 0.1
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Cotton');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Date Palm', 7.9
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Date Palm');
		
INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Grapes', 2.2
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Grapes');		

INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Kiwifruit', 3.8
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Kiwifruit'); 

INSERT INTO gestion.crop(id,crop,water_m3_by_area)
select nextval('gestion.crop_id_seq'), 'Olive', 2.7
    WHERE NOT EXISTS (
        SELECT crop FROM gestion.crop WHERE crop = 'Olive'); 

      

 --INSERT IRRIGATION TYPE   
INSERT INTO gestion.irrigation_type(id,description)
select 1, 'Flood'
    WHERE NOT EXISTS (
        SELECT description FROM gestion.irrigation_type WHERE description = 'Flood');  

INSERT INTO gestion.irrigation_type(id,description)
select 2, 'Aspersion'
    WHERE NOT EXISTS (
        SELECT description FROM gestion.irrigation_type WHERE description = 'Aspersion');        
       
INSERT INTO gestion.irrigation_type(id,description)
select 3, 'Microaspersion'
    WHERE NOT EXISTS (
        SELECT description FROM gestion.irrigation_type WHERE description = 'Microaspersion'); 

INSERT INTO gestion.irrigation_type(id,description)
select 4, 'Drip'
    WHERE NOT EXISTS (
        SELECT description FROM gestion.irrigation_type WHERE description = 'Drip');  
       
--INSERT WEATHER

INSERT INTO gestion.weather(id,description,hu_by_Area)
select 1, 'Sunny', 2.5
    WHERE NOT EXISTS (
        SELECT description FROM gestion.weather WHERE description = 'Sunny');  
       
INSERT INTO gestion.weather(id,description,hu_by_Area)
select 2, 'Windy', 4.2
    WHERE NOT EXISTS (
        SELECT description FROM gestion.weather WHERE description = 'Windy'); 

INSERT INTO gestion.weather(id,description,hu_by_Area)
select 3, 'Rainy', 9.9
    WHERE NOT EXISTS (
        SELECT description FROM gestion.weather WHERE description = 'Rainy'); 
	
INSERT INTO gestion.weather(id,description,hu_by_Area)
select 4, 'Cold', 5.6
    WHERE NOT EXISTS (
        SELECT description FROM gestion.weather WHERE description = 'Cold'); 	

INSERT INTO gestion.weather(id,description,hu_by_Area)
select 5, 'Cool',7.9
    WHERE NOT EXISTS (
        SELECT description FROM gestion.weather WHERE description = 'Cool');        
 
--INSERT REGION   

INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Maryland',1 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Maryland');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Wisconsin',2 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Wisconsin');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Illinois',1 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Illinois');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Indiana',5 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Indiana');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Ohio',3 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Ohio');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Kentucky',2 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Kentucky');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Tennessee',4 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Tennessee');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Alabama',1 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Alabama');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Misisipi',1 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Misisipi');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Minnesota',3 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Minnesota');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Iowa',2 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Iowa');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Nebraska',1 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Nebraska');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Misuri',2 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Misuri');
INSERT INTO gestion.region(id,region,id_weather) select nextval('gestion.region_id_seq'),'Kansas',3 WHERE NOT EXISTS (SELECT region FROM gestion.region WHERE region = 'Kansas');       