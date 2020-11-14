/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.snowflake.command.runner.ExecuteSnowflakeScriptRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * Snowflakeに対するスクリプト（SQLファイル）を実行するコマンド処理.
 *
 * @author Nozomu Takashima
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteSnowflakeScript", runner = ExecuteSnowflakeScriptRunner.class)
public class ExecuteSnowflakeScript extends Command {

    /**
     * Snowflake接続設定参照.
     */
    @NotEmpty
    private String snowflakeConnectConfigRef;

}
