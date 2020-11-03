/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.configuration.model;

import com.epion_t3.core.common.annotation.CustomConfigurationDefinition;
import com.epion_t3.core.common.bean.scenario.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * Snowflakeへの接続定義.
 *
 * @author Nozomu Takashima
 */
@Getter
@Setter
@CustomConfigurationDefinition(id = "SnowflakeConnectionConfiguration")
public class SnowflakeConnectionConfiguration extends Configuration {

    /**
     * ドライバクラス名.
     */
    private String driverClassName;

    /**
     * JDBC接続URL.
     */
    private String url;

    /**
     * ユーザ名.
     */
    private String username;

    /**
     * パスワード.
     */
    private String password;

    /**
     * スキーマ.
     */
    private String schema;

    /**
     * RDB種別.
     */
    private String rdbKind;

    /**
     * データベース名.
     */
    private String dbName;

}
