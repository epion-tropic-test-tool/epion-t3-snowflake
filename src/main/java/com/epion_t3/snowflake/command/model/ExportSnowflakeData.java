/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.rdb.bean.TargetTable;
import com.epion_t3.snowflake.command.runner.ExportSnowflakeDataRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

/**
 * Snowflakeデータのエクスポートコマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExportSnowflakeData", runner = ExportSnowflakeDataRunner.class)
public class ExportSnowflakeData extends Command {

    /**
     * Snowflake接続設定参照.
     */
    @NotEmpty
    private String snowflakeConnectConfigRef;

    /**
     * データセット種別.
     */
    @NotEmpty
    private String dataSetType = "excel";

    /**
     * テーブル指定.
     */
    private List<TargetTable> tables;

}
