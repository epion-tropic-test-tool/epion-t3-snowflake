/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.util;

import com.epion_t3.core.exception.SystemException;
import com.epion_t3.snowflake.configuration.model.SnowflakeConnectionConfiguration;
import com.epion_t3.snowflake.messages.SnowflakeMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author takashno
 */
@Slf4j
public final class SnowflakeAccessUtils {

    /**
     * シングルトンインスタンス.
     */
    private static final SnowflakeAccessUtils instance = new SnowflakeAccessUtils();

    /**
     * プライベートコンストラクタ.
     */
    private SnowflakeAccessUtils() {
    }

    /**
     * シングルトンインスタンスを取得.
     *
     * @return
     */
    public static SnowflakeAccessUtils getInstance() {
        return instance;
    }

    /**
     * データソースを取得.
     *
     * @param rdbConnectionConfiguration RDB接続設定
     * @return {@link DataSource}
     */
    public DataSource getDataSource(SnowflakeConnectionConfiguration rdbConnectionConfiguration) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(rdbConnectionConfiguration.getDriverClassName());
        dataSource.setUrl(rdbConnectionConfiguration.getUrl());
        dataSource.setUsername(rdbConnectionConfiguration.getUsername());
        dataSource.setPassword(rdbConnectionConfiguration.getPassword());
        return dataSource;
    }

    public IDatabaseConnection getDatabaseConnection(SnowflakeConnectionConfiguration rdbConnectionConfiguration) {
        DataSource dataSource = getDataSource(rdbConnectionConfiguration);
        return getDatabaseConnection(rdbConnectionConfiguration, dataSource);
    }

    public IDatabaseConnection getDatabaseConnection(SnowflakeConnectionConfiguration rdbConnectionConfiguration,
            DataSource dataSource) {

        if (StringUtils.isEmpty(rdbConnectionConfiguration.getRdbKind())) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9013);
        }

        String schema = rdbConnectionConfiguration.getSchema();

        IDatabaseConnection conn = null;

        try {
            if (StringUtils.isNotEmpty(schema)) {
                conn = new DatabaseDataSourceConnection(dataSource, schema);
            } else {
                conn = new DatabaseDataSourceConnection(dataSource);
            }
            DatabaseConfig configOracle = conn.getConfig();
//            configOracle.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
            return conn;
        } catch (SQLException e) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9015);
        }

    }
}
