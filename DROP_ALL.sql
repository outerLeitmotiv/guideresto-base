BEGIN
 FOR drops_table IN (
  select 'drop ' || object_type || ' "' || object_name || '" CASCADE CONSTRAINTS' AS command_sql
  from user_objects
  where object_type in ('TABLE')
 ) LOOP
  EXECUTE IMMEDIATE (drops_table.command_sql);
 END LOOP;
 
 FOR drops_objects IN (
  select 'drop ' || object_type || ' ' || object_name || '' AS command_sql
  from user_objects
  where object_type in ('VIEW','PACKAGE','SEQUENCE', 'PROCEDURE', 'FUNCTION', 'INDEX')
 ) LOOP
  EXECUTE IMMEDIATE (drops_objects.command_sql);
 END LOOP;
END;
