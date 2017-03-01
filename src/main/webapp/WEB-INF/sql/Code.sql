#namespace("code")

  #sql("listTable")
    SELECT table_name FROM information_schema.tables
    WHERE table_schema=#p(table_schema)
  #end

  #sql("listColumn")
    SELECT
    column_name,
    character_maximum_length,
    column_type,
    data_type,
    column_comment
    FROM information_schema.columns
    WHERE table_schema=#p(table_schema)
    AND table_name = #p(table_name)
  #end

#end