package com.shanghaichuangshi.model;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Id;
import com.shanghaichuangshi.annotation.Table;
import com.shanghaichuangshi.type.ColumnType;

public class Log extends Model<Log> {

    @Table()
    public static final String TABLE_USER = "table_user";

    @Id
    @Column(type = ColumnType.VARCHAR, length = 32, comment = "日志编号")
    public static final String LOG_ID = "log_id";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "请求地址")
    public static final String LOG_URL = "log_url";

    @Column(type = ColumnType.VARCHAR, length = 0, comment = "请求参数")
    public static final String LOG_REQUEST = "log_request";

    @Column(type = ColumnType.VARCHAR, length = 0, comment = "响应数据")
    public static final String LOG_RESPONSE = "log_response";

    @Column(type = ColumnType.VARCHAR, length = 10, comment = "响应时间")
    public static final String LOG_RUN_TIME = "log_run_time";

}
