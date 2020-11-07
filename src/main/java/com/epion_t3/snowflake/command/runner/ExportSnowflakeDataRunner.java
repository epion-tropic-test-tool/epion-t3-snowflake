/* Copyright (c) 2017-2020 Nozomu Takashima. */
package com.epion_t3.snowflake.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.rdb.bean.TargetTable;
import com.epion_t3.rdb.type.DataSetType;
import com.epion_t3.rdb.writer.XlsxDataSetWriter;
import com.epion_t3.snowflake.command.model.ExportSnowflakeData;
import com.epion_t3.snowflake.configuration.model.SnowflakeConnectionConfiguration;
import com.epion_t3.snowflake.messages.SnowflakeMessages;
import com.epion_t3.snowflake.util.SnowflakeAccessUtils;
import lombok.extern.slf4j.Slf4j;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.dataset.xml.XmlDataSetWriter;
import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.nio.file.Path;
import java.sql.SQLException;

/**
 * RDBに対してデータセットをインポート実行処理.
 *
 * @author takashno
 */
@Slf4j
public class ExportSnowflakeDataRunner extends AbstractCommandRunner<ExportSnowflakeData> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(ExportSnowflakeData command, Logger logger) throws Exception {

        // 接続先設定を参照
        SnowflakeConnectionConfiguration rdbConnectionConfiguration = referConfiguration(
                command.getSnowflakeConnectConfigRef());

        // データセット種別
        DataSetType dataSetType = DataSetType.valueOfByValue(command.getDataSetType());

        // データセット種別が解決できなかった場合はエラー
        if (dataSetType == null) {
            throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9007, command.getDataSetType());
        }

        // データセット読み込み
        IDataSet iDataSet = null;

        IDatabaseConnection conn = null;
        try {
            // コネクションを取得
            conn = SnowflakeAccessUtils.getInstance().getDatabaseConnection(rdbConnectionConfiguration);

            // クエリーデータセットを作成
            iDataSet = new QueryDataSet(conn);

            // 対象テーブルを登録
            for (TargetTable t : command.getTables()) {
                ((QueryDataSet) iDataSet).addTable(t.getTable(), t.getQuery());
            }

            // データセットの種類によって出力処理を行う
            switch (dataSetType) {
            case CSV:
                // TODO
                // iDataSet = new CsvDataSet(dataSetPath.toFile());
                // break;
                throw new SystemException(SnowflakeMessages.SNOWFLAKE_ERR_9008);
            case XML:
                Path xmlPath = getEvidencePath("export.xml");
                try (FileWriter fileWriter = new FileWriter(xmlPath.toFile());) {
                    XmlDataSetWriter writer2 = new XmlDataSetWriter(fileWriter);
                    writer2.setPrettyPrint(true);
                    writer2.write(iDataSet);
                }
                registrationFileEvidence(xmlPath);
                break;
            case FLAT_XML:
                Path flatXmlPath = getEvidencePath("export_flat.xml");
                try (OutputStream os = new FileOutputStream(flatXmlPath.toFile());) {
                    FlatXmlWriter writer = new FlatXmlWriter(os);
                    writer.write(iDataSet);
                }
                registrationFileEvidence(flatXmlPath);
                break;
            case EXCEL:
                Path xlsxPath = getEvidencePath("export.xlsx");
                try (OutputStream os = new FileOutputStream(xlsxPath.toFile())) {
                    XlsxDataSetWriter writer = new XlsxDataSetWriter();
                    writer.write(iDataSet, os);
                }
                registrationFileEvidence(xlsxPath);
                break;
            default:
                // ありえない
                break;
            }
        } catch (DatabaseUnitException e) {
            log.debug("Error Occurred...", e);
            throw new SystemException(e, SnowflakeMessages.SNOWFLAKE_ERR_9011);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Ignore
                    log.trace("Error Occurred... -> Ignore", e);
                }
            }
        }

        return CommandResult.getSuccess();

    }

}
