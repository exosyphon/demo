CREATE SEQUENCE other_thing_id_seq;

CREATE TABLE other_thing (
  id integer NOT NULL DEFAULT nextval('other_thing_id_seq') PRIMARY KEY,
  name text,
  second_name time
);

ALTER SEQUENCE other_thing_id_seq
OWNED BY other_thing.id;