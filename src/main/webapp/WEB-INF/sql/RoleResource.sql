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

  #sql("listByRole_key")
    SELECT
    table_resource.resource_value
    FROM table_role_resource
    INNER JOIN table_resource ON table_resource.resource_id = table_role_resource.resource_id
    INNER JOIN table_role ON table_role.role_id = table_role_resource.role_id
    WHERE table_role_resource.system_status = 1
    AND table_role.role_key = #p(role_key)
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