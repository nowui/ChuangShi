#namespace("file")

  #sql("count")
    SELECT COUNT(*) FROM table_file
    WHERE system_status = 1
    #if(file_name)
      #set(file_name = "%" + file_name + "%")
      AND file_name LIKE #p(file_name)
    #end
    #if(file_type)
      AND file_type = #p(file_type)
    #end
  #end

  #sql("list")
    SELECT
    file_id,
    file_thumbnail_path,
    file_path
    FROM table_file
    WHERE system_status = 1
    #if(file_name)
      #set(file_name = "%" + file_name + "%")
      AND file_name LIKE #p(file_name)
    #end
    #if(file_type)
      AND file_type = #p(file_type)
    #end
    ORDER BY system_create_time DESC
    #if(n > 0)
      LIMIT #p(m), #p(n)
    #end
  #end

  #sql("find")
    SELECT
    *
    FROM table_file
    WHERE system_status = 1
    AND file_id = #p(file_id)
  #end

  #sql("findByUser_id")
    SELECT
    *
    FROM table_file
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("updateByAdmin_idAndUser_id")
    UPDATE table_file SET
    user_id = #p(user_id),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time)
    WHERE file_id = #p(file_id)
  #end

#end