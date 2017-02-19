#namespace("code")

  #sql("listTable")
    SELECT table_name FROM information_schema.tables
    WHERE table_schema='Shop'
  #end

  #sql("listColumn")
    SELECT
    column_name,
    character_maximum_length,
    column_type,
    column_comment
    FROM information_schema.columns
    WHERE table_name LIKE #p(table_name)
  #end

#end