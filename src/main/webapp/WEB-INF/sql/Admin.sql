#namespace("admin")

  #sql("count")
    SELECT COUNT(*) FROM table_admin
    WHERE system_status = 1
    #if(admin_name)
      #set(admin_name = "%" + admin_name + "%")
      AND admin_name LIKE #p(admin_name)
    #end
  #end

  #sql("list")
    SELECT
    admin_id,
    admin_name,
    user_account
    FROM table_admin
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    WHERE table_admin.system_status = 1
    #if(admin_name)
      #set(admin_name = "%" + admin_name + "%")
      AND admin_name LIKE #p(admin_name)
    #end
    ORDER BY table_admin.system_create_time DESC
    #if(n > 0)
      LIMIT #p(m), #p(n)
    #end
  #end

  #sql("find")
    SELECT
    table_admin.*,
    table_user.user_account
    FROM table_admin
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    WHERE table_admin.system_status = 1
    AND table_admin.admin_id = #p(admin_id)
  #end

#end