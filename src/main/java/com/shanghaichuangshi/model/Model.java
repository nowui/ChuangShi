package com.shanghaichuangshi.model;

public abstract class Model {

//    @Column
//    @Comment("新增人员")
//    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String system_create_user_id;

//    @Column
//    @Comment("新增时间")
//    @ColDefine(type = ColType.DATETIME)
    private String system_create_time;

//    @Column
//    @Comment("修改人员")
//    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String system_update_user_id;

//    @Column
//    @Comment("修改时间")
//    @ColDefine(type = ColType.DATETIME)
    private String system_update_time;

//    @Column
//    @Comment("删除标记")
//    @ColDefine(type = ColType.DATETIME)
    private String system_status;

    public String getSystem_create_user_id() {
        return system_create_user_id;
    }

    public void setSystem_create_user_id(String system_create_user_id) {
        this.system_create_user_id = system_create_user_id;
    }

    public String getSystem_create_time() {
        return system_create_time;
    }

    public void setSystem_create_time(String system_create_time) {
        this.system_create_time = system_create_time;
    }

    public String getSystem_update_user_id() {
        return system_update_user_id;
    }

    public void setSystem_update_user_id(String system_update_user_id) {
        this.system_update_user_id = system_update_user_id;
    }

    public String getSystem_update_time() {
        return system_update_time;
    }

    public void setSystem_update_time(String system_update_time) {
        this.system_update_time = system_update_time;
    }

    public String getSystem_status() {
        return system_status;
    }

    public void setSystem_status(String system_status) {
        this.system_status = system_status;
    }

}
