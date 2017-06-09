#namespace("count")

  #sql("find")
    SELECT
    *
    FROM table_count
    WHERE system_status = 1
    AND object_id = #p(object_id)
    AND count_type = #p(count_type)
    AND count_key = #p(count_key)
  #end

  #sql("delete")
    UPDATE table_count SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_status = 0
    WHERE count_id = #p(count_id)
  #end

#end