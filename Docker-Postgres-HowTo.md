#### Launch a blank (test) postgres container
1. start up docker
2. in terminal
```
docker pull postgres
cd [/path/to/financeApp]
mkdir PostgresTestVolume
cd PostgresTestVolume/
docker run --rm --name pg-docker-test \
-e POSTGRES_PASSWORD=docker -d \
-p 9432:5432 \
-v $(pwd)/:/var/lib/postgresql/data \
postgres
```

#### Connect to that postgres container
`psql "dbname=postgres host=localhost user=postgres password=docker port=9432"`

#### SQL - list tables
`SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';`

#### SQL - show count for all table:
//1. define count function
```
create or replace function
count_rows(schema text, tablename text) returns integer
as
$body$
declare
  result integer;
  query varchar;
begin
  query := 'SELECT count(1) FROM ' || schema || '.' || tablename;
  execute query into result;
  return result;
end;
$body$
language plpgsql;
```

//2. query
```
select
  table_schema,
  table_name,
  count_rows(table_schema, table_name)
from information_schema.tables
where
  table_schema not in ('pg_catalog', 'information_schema')
  and table_type='BASE TABLE'
```

#### quit psql
`postgres=# \q

#### stop that container

`docker stop pg-docker-test`

And check that nothing still running with

`docker ps -a`

Then you can remove PostgresTestVolume...

NB : 
* such a blank PostGresTestVolume will take up 41.6 Mo
* such volumes as reusable - ie are a good way to persist from session to the next
