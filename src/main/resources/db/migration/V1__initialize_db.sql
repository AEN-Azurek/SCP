DROP TABLE IF EXISTS dbo.item;
CREATE TABLE dbo.item (
    id bigserial NOT NULL,
    item_name character varying(255) NOT NULL,
    has_recipe boolean NOT NULL,
    sink_value bigint NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE dbo.item
    OWNER to scp;

DROP TABLE IF EXISTS dbo.building;
CREATE TABLE dbo.building (
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    footprint_area bigint NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE dbo.building
    OWNER to scp;

DROP TABLE IF EXISTS dbo.attribute;
CREATE TABLE dbo.attribute (
    id bigserial NOT NULL,
    attribute_name character varying(255) NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE dbo.attribute
    OWNER to scp;

DROP TABLE IF EXISTS dbo.building_attribute;
CREATE TABLE dbo.building_attribute (
    id bigserial NOT NULL,
    building_id bigint NOT NULL,
    attribute_id bigint NOT NULL,
    attribute_value character varying(255) NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (building_id)
        REFERENCES dbo.building (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    FOREIGN KEY (attribute_id)
        REFERENCES dbo.attribute (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE dbo.building_attribute
    OWNER to scp;

DROP TABLE IF EXISTS dbo.recipe;
CREATE TABLE dbo.recipe (
    id bigserial NOT NULL,
    recipe_name character varying(100) NOT NULL,
    building_id bigint NOT NULL,
    primary_output bigint NOT NULL,
    crafting_time numeric NOT NULL,
    default_recipe boolean NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (building_id)
        REFERENCES dbo.building (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    FOREIGN KEY (primary_output)
        REFERENCES dbo.item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE dbo.recipe
    OWNER to scp;


DROP TABLE IF EXISTS dbo.recipe_items;
CREATE TABLE dbo.recipe_items(
    id bigserial NOT NULL,
    recipe_id bigint NOT NULL,
    item_id bigint NOT NULL,
    direction character varying(3) NOT NULL,
    quantity bigint NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id)
        REFERENCES dbo.recipe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    FOREIGN KEY (item_id)
        REFERENCES dbo.item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE dbo.recipe_items
    OWNER to scp;

INSERT INTO
	dbo.item (item_name, has_recipe, sink_value)
VALUES
	('Iron Ore', false, 1), -- ID: 1
	('Copper Ore', false, 3), -- ID: 2
	('Caterium Ore', false, 7), -- ID: 3
	('Iron Ingot', true, 2), -- ID: 4
	('Copper Ingot', true, 6), -- ID: 5
	('Iron Rod', true, 4), -- ID: 6
	('Iron Plate', true, 6), -- ID: 7
	('Screw', true, 2), -- ID: 8
	('Reinforced Iron Plate', true, 120), -- ID: 9
	('Water', false, 0), -- ID: 10
	('Coal', false, 3), -- ID: 11
	('Steel Ingot', true, 8), -- ID: 12
	('Steel Beam', true, 64); -- ID: 13

INSERT INTO
    dbo.building (name, footprint_area)
VALUES
    ('Assembler', 150), -- ID: 1
    ('Blender', 288), -- ID: 2
    ('Constructor', 80), -- ID: 3
    ('Foundry', 90), -- ID: 4
    ('Manufacturer', 360), -- ID: 5
    ('Miner Mk1', 84), -- ID: 6
    ('Miner Mk2', 84), -- ID: 7
    ('Miner Mk3', 84), -- ID: 8
    ('Oil Extractor', 112), -- ID: 9
    ('Packager', 64), -- ID: 10
    ('Refinery', 200), -- ID: 11
    ('Smelter', 54), -- ID: 12
    ('Water Extractor', 390), -- ID: 13
    ('Particle Accelerator', 912), -- ID: 14
    ('Resource Well Pressurizer', 400), -- ID: 15
    ('Biomass Burner', 64), -- ID: 16
    ('Coal Generator', 260), -- ID: 17
    ('Fuel Generator', 400), -- ID: 18
    ('Geothermal Generator', 380), -- ID: 19
    ('Nuclear Power Plant', 1548), -- ID: 20
    ('Power Storage', 36); -- ID: 21

INSERT INTO
    dbo.attribute (attribute_name)
VALUES
    ('power_consumption'), -- ID: 1
    ('power_production'), -- ID: 2
    ('variable_min_power_consumption'), -- ID: 3
    ('variable_min_power_production'); -- ID: 4

INSERT INTO
    dbo.building_attribute (building_id, attribute_id, attribute_value)
VALUES
    (1, 1, 15),
    (2, 1, 75),
    (3, 1, 4),
    (4, 1, 16),
    (5, 1, 55),
    (6, 1, 5),
    (7, 1, 12),
    (8, 1, 30),
    (9, 1, 40),
    (10, 1, 10),
    (11, 1, 30),
    (12, 1, 4),
    (13, 1, 20),
    (14, 1, 1500),
    (14, 3, 250),
    (15, 1, 150),
    (16, 2, 30),
    (17, 2, 75),
    (18, 2, 150),
    (19, 2, 600),
    (19, 4, 50),
    (20, 2, 2500),
    (21, 1, 100),
    (21, 2, 100);

INSERT INTO
	dbo.recipe (recipe_name, primary_output, building_id, crafting_time, default_recipe)
VALUES
	('Iron Ingot', 4, 12, 2, true), -- ID: 1
	('Pure Iron Ingot', 4, 11, 12, false), -- ID: 2
	('Iron Alloy Ingot', 4, 4, 6, false), -- ID: 3
	('Copper Ingot', 5, 12, 2, true), -- ID: 4
	('Pure Copper Ingot', 5, 11, 24, false), -- ID: 5
	('Copper Alloy Ingot', 5, 4, 12, false), -- ID: 6
    ('Steel Ingot', 12, 4, 4, true), -- ID: 7
    ('Solid Steel Ingot', 12, 4, 3, false), -- ID: 8
	('Iron Rod', 6, 3, 4, true), -- ID: 9
	('Steel Rod', 6, 3, 5, true), -- ID: 10
	('Steel Beam', 13, 3, 4, true), -- ID: 11
	('Screw', 8, 3, 6, true), -- ID: 12
	('Cast Screw', 8, 3, 24, false), -- ID: 13
	('Steel Screw', 8, 3, 12, false); -- ID: 14

INSERT INTO
	dbo.recipe_items (recipe_id, item_id, direction, quantity)
VALUES
	( 1,  1, 'in',   1),
	( 1,  4, 'out',  1),
	( 2, 10, 'in',   4),
	( 2,  1, 'in',   7),
	( 2,  4, 'out', 13),
	( 3,  1, 'in',   2),
	( 3,  2, 'in',   2),
	( 3,  4, 'out',  5),
	( 4,  2, 'in',   1),
	( 4,  5, 'out',  1),
	( 5,  2, 'in',   6),
	( 5, 10, 'in',   4),
	( 5,  5, 'out', 15),
	( 6,  2, 'in',  10),
	( 6,  1, 'in',   5),
	( 6,  5, 'out', 20),
    ( 7,  1, 'in',   3),
    ( 7, 11, 'in',   3),
    ( 7, 12, 'out',  3),
    ( 8,  4, 'in',   2),
    ( 8, 11, 'in',   2),
    ( 8, 12, 'out',  3),
	( 9,  4, 'in',   1),
	( 9,  6, 'out',  1),
	(10, 12, 'in',   1),
	(10,  6, 'out',   4),
	(11, 12, 'in',   4),
	(11, 13, 'out',  1),
	(12,  6, 'in',   1),
	(12,  8, 'out',  4),
	(13,  4, 'in',   5),
	(13,  8, 'out', 20),
	(14, 13, 'in',   1),
	(14,  8, 'out', 52);