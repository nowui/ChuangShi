#namespace("role_resource")

  #sql("list")
    SELECT
    role_resource_id,
    role_id,
    resource_id
    FROM table_role_resource
    WHERE system_status = 1
    AND role_id = #p(role_id)
  #end

  #sql("save")
    INSERT INTO table_role_resource (
      role_resource_id,
      role_id,
      resource_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_status
    ) VALUES (
      ?,
      ?,
      ?,
      ?,
      ?,
      ?,
      ?,
      ?
    )
  #end

  #sql("delete")
    UPDATE table_role_resource SET
    system_update_user_id = ?,
    system_update_time = ?,
    system_status = 0
    WHERE role_resource_id = ?
  #end

#end