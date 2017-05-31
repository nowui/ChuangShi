#namespace("authorization")

  #sql("count")
    SELECT COUNT(*) FROM table_authorization
    WHERE system_status = 1
    #if(authorization_token)
      #set(authorization_token = "%" + authorization_token + "%")
      AND authorization_token LIKE #p(authorization_token)
    #end
  #end

  #sql("list")
    SELECT
    authorization_id,
    authorization_token,
    authorization_platform,
    authorization_version,
    authorization_create_time
    FROM table_authorization
    WHERE system_status = 1
    #if(authorization_token)
      #set(authorization_token = "%" + authorization_token + "%")
      AND authorization_token LIKE #p(authorization_token)
    #end
    ORDER BY system_create_time DESC
    #if(n > 0)
      LIMIT #p(m), #p(n)
    #end
  #end

  #sql("find")
    SELECT
    *
    FROM table_authorization
    WHERE system_status = 1
    AND authorization_id = #p(authorization_id)
  #end

  #sql("delete")
    UPDATE table_authorization SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_status = 0
    AND authorization_id = #p(authorization_id)
  #end

#end