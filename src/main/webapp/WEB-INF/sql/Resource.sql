#namespace("resource")

  #sql("count")
    SELECT COUNT(*) FROM table_resource
    WHERE system_status = 1
    #if(category_id)
      AND category_id = #p(category_id)
    #end
    #if(resource_name)
      #set(resource_name = "%" + resource_name + "%")
      AND resource_name LIKE #p(resource_name)
    #end
  #end

  #sql("list")
    SELECT
    resource_id,
    category_id,
    resource_name,
    resource_key,
    resource_value,
    resource_sort
    FROM table_resource
    WHERE system_status = 1
    #if(category_id)
      AND category_id = #p(category_id)
    #end
    #if(resource_name)
      #set(resource_name = "%" + resource_name + "%")
      AND resource_name LIKE #p(resource_name)
    #end
    ORDER BY resource_sort, system_create_time DESC
    #if(n > 0)
      LIMIT #p(m), #p(n)
    #end
  #end

  #sql("allList")
    SELECT
    resource_id,
    category_id,
    resource_name
    FROM table_resource
    WHERE system_status = 1
  #end

  #sql("find")
    SELECT
    *
    FROM table_resource
    WHERE system_status = 1
    AND resource_id = #p(resource_id)
  #end

  #sql("delete")
    UPDATE table_resource SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_status = 0
    WHERE resource_id = #p(resource_id)
  #end

#end