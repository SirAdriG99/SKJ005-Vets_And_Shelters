psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
-- \i ./var/sql_scripts/user_creator.sql doen't work
\i ./var/sql_scripts/create.sql
\i ./var/sql_scripts/procedence_type.sql
\i ./var/sql_scripts/sex.sql
\i ./var/sql_scripts/breed.sql
\i ./var/sql_scripts/animal_satus.sql
\i ./var/sql_scripts/animal.sql
\i ./var/sql_scripts/animal_status_hist.sql
\i ./var/sql_scripts/animal_photos.sql
\i ./var/sql_scripts/alterTables.sql
\i ./var/sql_scripts/fixSequences.sql
-- \i ./var/sql_scripts/addAllPrivilegies.sql

-- CREATE DATABASE develop;
-- ALTER DATABASE develop OWNER TO hibernate;
-- GRANT ALL PRIVILEGES ON DATABASE develop TO hibernate;
-- CREATE DATABASE production;
EOSQL