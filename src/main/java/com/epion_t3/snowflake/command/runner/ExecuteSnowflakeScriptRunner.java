/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.snowflake.command.model.ExecuteSnowflakeScript;
import com.epion_t3.snowflake.configuration.model.SnowflakeConnectionConfiguration;
import com.epion_t3.snowflake.messages.SnowflakeMessages;
import com.epion_t3.snowflake.util.SnowflakeAccessUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * RDBに対してスクリプト（SQL）実行処理．
 *
 * @author Nozomu Takashima
 */
@Slf4j
public class ExecuteSnowflakeScriptRunner extends AbstractCommandRunner<ExecuteSnowflakeScript> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(ExecuteSnowflakeScript command, Logger logger) throws Exception {

        // 接続先は必須
        if (StringUtils.isEmpty(command.getSnowflakeConnectConfigRef())) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9002);
        }

        // 接続先設定を参照
        SnowflakeConnectionConfiguration rdbConnectionConfiguration = referConfiguration(
                command.getSnowflakeConnectConfigRef());

        // スクリプトパスを取得
        String script = command.getValue();

        // クエリーは必須
        if (StringUtils.isEmpty(script)) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9003);
        }

        // スクリプトパスを解決
        Path scriptPath = Paths.get(getCommandBelongScenarioDirectory(), script);

        // スクリプトパスが存在しなかった場合はエラー
        if (Files.notExists(scriptPath)) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9004, scriptPath.toString());
        }

        // スクリプトの内容を読み込み
        String scriptContents = new String(Files.readAllBytes(scriptPath), Charset.forName("UTF-8"));

        // スクリプトの内容に対してバインド処理
        // クエリーの場合は直接YAMLに記載されるためバインドは前段で行われているが、
        // スクリプト（SQL）の場合はファイルの中身を読み込まなければならないため
        scriptContents = bind(scriptContents);

        // スクリプトを分割
        String[] queries = scriptContents.split(";");

        // データソースを取得
        DataSource dataSource = SnowflakeAccessUtils.getInstance().getDataSource(rdbConnectionConfiguration);

        try (Connection conn = dataSource.getConnection()) {
            for (String q : queries) {
                if (StringUtils.isNotEmpty(q)) {
                    try (Statement statement = conn.createStatement()) {
                        log.trace("execute query -> {}", q);
                        statement.executeUpdate(q);
                    }
                }
            }
        } catch (SQLException e) {
            log.debug("Error Occurred...", e);
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9002, e);
        }

        return CommandResult.getSuccess();
    }

}
