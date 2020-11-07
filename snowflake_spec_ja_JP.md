# Snowflake カスタム機能ドキュメント

このドキュメントは、Snowflake のカスタム機能が提供する、
Flow、コマンド、設定定義についての説明及び出力するメッセージの定義について説明する。

- Contents
  - [Information](#Information)
  - [Description](#Description)
  - [Flow List](#Flow-List)
  - [Command List](#Command-List)
  - [Configuration List](#Configuration-List)
  - [Message List](#Message-List)

## Information

本カスタム機能の基本情報は以下の通り。

Snowflakeに対するアクセス機能を提供します。

- Name : `snowflake`
- Custom Package : `com.epion_t3.snowflake`

## Description
Snowflakeに対するアクセス機能を提供します。

## Flow List

本カスタム機能が提供するFlowの一覧及び詳細。

|Name|Summary|
|:---|:---|


## Command List

本カスタム機能が提供するコマンドの一覧及び詳細。

|Name|Summary|Assert|Evidence|
|:---|:---|:---|:---|
|[ExportSnowflakeData](#ExportSnowflakeData)|Snowflakeのデータを抽出（エクスポート）します。エクスポートしたデータはエビデンスとしても保存可能になります。  ||X|
|[ExecuteSnowflakeScript](#ExecuteSnowflakeScript)|Snowflakeに対してスクリプト（SQL）を実行します。  |||
|[ExecuteSnowflakeScriptQuery](#ExecuteSnowflakeScriptQuery)|Snowflakeに対してスクリプト（SQL）を実行します。  |||

------

### ExportSnowflakeData
Snowflakeのデータを抽出（エクスポート）します。エクスポートしたデータはエビデンスとしても保存可能になります。
#### Command Type
- Assert : No
- Evidence : __Yes__

#### Functions
- Snowflakeのデータを抽出（エクスポート）します。
- XMLで定義されたデータのエクスポートが行えます。（DBUnit）
- Excelで定義されたデータのエクスポートが行えます。（DBUnit）

#### Structure
```yaml
commands : 
  id : コマンドのID
  command : 「ExportSnowflakeData」固定
  summary : コマンドの概要（任意）
  description : コマンドの詳細（任意）
  snowflakeConnectConfigRef : Snowflakeに対する接続先定義の参照ID # (1)
  dataSetType : (xml|flatXml|excel) # (2)
  tables : エクスポート対象のテーブルを定義 # (3)

```

1. RDBへの接続先の設定を行っている &#96;SnowflakeConnectionConfiguration&#96; の参照IDを指定します。
1. DataSetの種類を指定します。DataSetとは、Snowflakeのデータ構造を表したもので、DataSetには、CSV、XML、Excelの形式が選べます。本コマンドが利用するDataSetとはすべてDBUnitのDataSetを指します。現状では、CSVには対応ができておりません。
1. エクスポートを行う対象のテーブルについて細かく指定が行えます。
------

### ExecuteSnowflakeScript
Snowflakeに対してスクリプト（SQL）を実行します。
#### Command Type
- Assert : No
- Evidence : No

#### Functions
- Snowflakeに対してスクリプト（SQL）を実行します。
- スクリプト（SQL）へETTTの変数バインドが可能です。
- スクリプト（SQL）はUTF-8で読み込みます。

#### Structure
```yaml
commands : 
  id : コマンドのID
  command : 「ExecuteSnowflakeScript」固定
  summary : コマンドの概要（任意）
  description : コマンドの詳細（任意）
  snowflakeConnectConfigRef : Snowflakeに対する接続先定義の参照ID # (1)
  value : スクリプト（SQL）のパス（相対パス）

```

1. Snowflakeへの接続先の設定を行っている &#96;SnowflakeConnectionConfiguration&#96; の参照IDを指定します。
------

### ExecuteSnowflakeScriptQuery
Snowflakeに対してスクリプト（SQL）を実行します。
#### Command Type
- Assert : No
- Evidence : No

#### Functions
- Snowflakeに対してスクリプト（SQL）を実行します。
- スクリプト（SQL）へETTTの変数バインドが可能です。
- スクリプト（SQL）はUTF-8で読み込みます。

#### Structure
```yaml
commands : 
  id : コマンドのID
  command : 「ExecuteSnowflakeScriptQuery」固定
  summary : コマンドの概要（任意）
  description : コマンドの詳細（任意）
  snowflakeConnectConfigRef : Snowflakeに対する接続先定義の参照ID # (1)
  value : スクリプト（SQL）のパス（相対パス）

```

1. Snowflakeへの接続先の設定を行っている &#96;SnowflakeConnectionConfiguration&#96; の参照IDを指定します。

## Configuration List

本カスタム機能が提供する設定定義の一覧及び詳細。

|Name|Summary|
|:---|:---|
|[SnowflakeConnectionConfiguration](#SnowflakeConnectionConfiguration)|Snowflake接続先設定です。  |

------

### SnowflakeConnectionConfiguration
Snowflake接続先設定です。
#### Description
- SnowflakeのJDBC接続を行う際に必要な情報を定義します。

#### Structure
```yaml
commands : 
  configuration : 「SnowflakeConnectionConfiguration」固定
  id : 設定のID
  summary : 設定の概要（任意）
  description : 設定の詳細（任意）
  driverClassName : Snowflake接続用のJDBCドライバーのクラス名（FQCN）
  url : Snowflake接続用のJDBCのURL
  username : Snowflake接続用のユーザー
  password : Snowflake接続用のパスワード
  schema : Snowflakeの接続スキーマ
  rdbKind : 「snowflake」固定
  dbName : Snowflakeの接続データベース

```


## Message List

本カスタム機能が出力するメッセージの一覧及び内容。

|MessageID|MessageContents|
|:---|:---|
|com.epion_t3.snowflake.err.9010|DataSetのインポートに失敗しました.|
|com.epion_t3.snowflake.err.9003|Scriptのパスが指定されていません.|
|com.epion_t3.snowflake.err.9014|Snowflakeへの接続先定義のSnowflake種別が不正です.対応するSnowflakeの値を設定してください.Snowflake種別：{0}|
|com.epion_t3.snowflake.err.9002|Queryの実行時にエラーが発生しました.|
|com.epion_t3.snowflake.err.9013|Snowflakeへの接続先定義のSnowflake種別は必須です.|
|com.epion_t3.snowflake.err.9001|Queryが指定されていません.|
|com.epion_t3.snowflake.err.9012|Snowflakeへの接続先定義は必須です.|
|com.epion_t3.snowflake.err.9011|Snowflakeアクセスに失敗したため、DataSetのエクスポートに失敗しました.|
|com.epion_t3.snowflake.err.9007|DataSetの種別が解決できません.種別：{0}|
|com.epion_t3.snowflake.err.9018|結果値を参照するためのFlowIDが指定されていません.|
|com.epion_t3.snowflake.err.9006|DataSetのパスが存在しません.パス：{0}|
|com.epion_t3.snowflake.err.9017|期待値のDataSetのパスが指定されていません.|
|com.epion_t3.snowflake.err.9005|DataSetのパスが指定されていません.|
|com.epion_t3.snowflake.err.9016|DataSetの読み込みに失敗しました.パス：{0}|
|com.epion_t3.snowflake.err.9004|Scriptのパスが存在しません.パス：{0}|
|com.epion_t3.snowflake.err.9015|Snowflakeへのコネクションの確率に失敗しました.|
|com.epion_t3.snowflake.err.9009|インポート用のオペレーションではありません.オペレーション：{0}|
|com.epion_t3.snowflake.err.9008|CSVによるDataSetには現状対応していません.|
|com.epion_t3.snowflake.err.9019|カラムの型が解決できません.テーブル：{0},カラム：{1}|
