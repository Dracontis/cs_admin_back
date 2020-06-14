create schema if not exists cs;

CREATE FUNCTION cs.sync_lastmod() RETURNS trigger AS
$$
BEGIN
    NEW.lastmodified := NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;