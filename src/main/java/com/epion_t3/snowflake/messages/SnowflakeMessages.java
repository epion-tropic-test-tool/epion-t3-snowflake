/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.messages;

import com.epion_t3.core.message.Messages;

/**
 * snowflake用メッセージ定義Enum.<br>
 *
 * @author epion-t3-devtools
 */
public enum SnowflakeMessages implements Messages {

    /** DataSetのインポートに失敗しました. */
    SNOWFLAKE_ERR_9010("com.epion_t3.snowflake.err.9010"),

    /** Scriptのパスが指定されていません. */
    SNOWFLAKE_ERR_9003("com.epion_t3.snowflake.err.9003"),

    /**
     * Snowflakeへの接続先定義のSnowflake種別が不正です.対応するSnowflakeの値を設定してください.Snowflake種別：{0}
     */
    SNOWFLAKE_ERR_9014("com.epion_t3.snowflake.err.9014"),

    /** Queryの実行時にエラーが発生しました. */
    SNOWFLAKE_ERR_9002("com.epion_t3.snowflake.err.9002"),

    /** Snowflakeへの接続先定義のSnowflake種別は必須です. */
    SNOWFLAKE_ERR_9013("com.epion_t3.snowflake.err.9013"),

    /** Queryが指定されていません. */
    SNOWFLAKE_ERR_9001("com.epion_t3.snowflake.err.9001"),

    /** Snowflakeへの接続先定義は必須です. */
    SNOWFLAKE_ERR_9012("com.epion_t3.snowflake.err.9012"),

    /** Snowflakeアクセスに失敗したため、DataSetのエクスポートに失敗しました. */
    SNOWFLAKE_ERR_9011("com.epion_t3.snowflake.err.9011"),

    /** DataSetの種別が解決できません.種別：{0} */
    SNOWFLAKE_ERR_9007("com.epion_t3.snowflake.err.9007"),

    /** 結果値を参照するためのFlowIDが指定されていません. */
    SNOWFLAKE_ERR_9018("com.epion_t3.snowflake.err.9018"),

    /** DataSetのパスが存在しません.パス：{0} */
    SNOWFLAKE_ERR_9006("com.epion_t3.snowflake.err.9006"),

    /** 期待値のDataSetのパスが指定されていません. */
    SNOWFLAKE_ERR_9017("com.epion_t3.snowflake.err.9017"),

    /** DataSetのパスが指定されていません. */
    SNOWFLAKE_ERR_9005("com.epion_t3.snowflake.err.9005"),

    /** DataSetの読み込みに失敗しました.パス：{0} */
    SNOWFLAKE_ERR_9016("com.epion_t3.snowflake.err.9016"),

    /** Scriptのパスが存在しません.パス：{0} */
    SNOWFLAKE_ERR_9004("com.epion_t3.snowflake.err.9004"),

    /** Snowflakeへのコネクションの確率に失敗しました. */
    SNOWFLAKE_ERR_9015("com.epion_t3.snowflake.err.9015"),

    /** インポート用のオペレーションではありません.オペレーション：{0} */
    SNOWFLAKE_ERR_9009("com.epion_t3.snowflake.err.9009"),

    /** CSVによるDataSetには現状対応していません. */
    SNOWFLAKE_ERR_9008("com.epion_t3.snowflake.err.9008"),

    /** カラムの型が解決できません.テーブル：{0},カラム：{1} */
    SNOWFLAKE_ERR_9019("com.epion_t3.snowflake.err.9019");

    /** メッセージコード */
    private String messageCode;

    /**
     * プライベートコンストラクタ<br>
     *
     * @param messageCode メッセージコード
     */
    private SnowflakeMessages(final String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * messageCodeを取得します.<br>
     *
     * @return messageCode
     */
    public String getMessageCode() {
        return this.messageCode;
    }
}
