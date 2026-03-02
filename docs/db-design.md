タスクテーブル
| 型 | カラム論理名 | カラム物理名 |
|---|---|---|
| BIGINT| ID|タスクID|
| TEXT| タイトル|タイトル|
| TEXT| 説明|説明|
| VARCHAR(20) | ステータス|ステータス|
| DATE | 期限|期限|
| VARCHAR(10) | 優先度 |優先度|
| TEXT | 区分|区分|
| BIGINT| 担当者|担当者ID|
| BIGINT| 登録者|登録者ID|
| TIMESTAMP| 登録年月日 |登録年月日|
| TIMESTAMP | 更新年月日 |更新年月日|
| BIGINT| 更新者|更新者ID|
| boolean| 削除フラグ|削除フラグ|


ユーザーテーブル
| 型 | カラム論理名 | カラム物理名 |
|---|---|---|
| BIGINT| ID|ユーザーID|
| TEXT| パスワード|パスワード|
| TEXT | 名前|名前|
| VARCHAR(20) | 権限 |権限|
| TIMESTAMP| 登録年月日 |登録年月日|
| TIMESTAMP| 更新年月日 |更新年月日|
| BIGINT| 更新者|更新者ID|
| boolean| 削除フラグ|削除フラグ|