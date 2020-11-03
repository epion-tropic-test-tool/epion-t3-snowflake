#  Command

- Contents
  - [Information](#Information)
  - [Description](#Description)
  - [Flow List](#Flow-List)
  - [Command List](#Command-List)
  - [Configuration List](#Configuration-List)
  - [Message List](#Message-List)


## Information
Snowflakeに対するアクセス機能を提供します。

- Name : `snowflake`
- Custom Package : `com.epion_t3.snowflake`

## Description
Snowflakeに対するアクセス機能を提供します。

## Flow List

## Command List

|Name|Summary|Assert|Evidence|
|:---|:---|:---|:---|
|[ExecuteSnowflakeScript](#ExecuteSnowflakeScript)|Snowflakeに対してスクリプト（SQL）を実行します。  |||

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

1. Snowflakeへの接続先の設定を行っている &#96;Configuration&#96; の参照IDを指定します。

## Configuration List

## Message List

 Command output messages.

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
