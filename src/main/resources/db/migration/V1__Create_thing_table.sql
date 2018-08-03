CREATE SEQUENCE thing_id_seq;

CREATE TABLE thing (
  id integer NOT NULL DEFAULT nextval('thing_id_seq') PRIMARY KEY,
  name text,
  second_name time
);

ALTER SEQUENCE thing_id_seq
OWNED BY thing.id;