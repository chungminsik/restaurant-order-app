# 🍽️ Restaurant Order System

## 概要
このプロジェクトは、レストランの注文管理を行うためのシステムです。メニュー管理、座席管理、注文管理を行うことができ、Spring Boot を用いて開発されました。

### このアプリの主な機能
- **メニュー管理**: メニューの追加、編集、削除
- **注文管理**: 顧客の注文受付、注文履歴管理
- **座席管理**: レストランの座席予約および管理
- **検索機能（開発予定）**: メニューや注文の検索機能

---

## 技術スタック
| 分類        | 技術                   |
|------------|----------------------|
| **言語**   | Java 17              |
| **フレームワーク** | Spring Boot, Mybatis |
| **テンプレートエンジン** | Thymeleaf            |
| **フロントエンド** | JavaScript           |
| **データベース** | MySQL                |
| **ビルドツール** | Gradle               |

---

## アーキテクチャ
```mermaid
graph TD;
    subgraph クライアント
        User["👤 ユーザー"]
        Browser["🌐 ブラウザ (Thymeleaf)"]
    end

    subgraph サーバー["Spring Boot サーバー"]
        Controller["コントローラー\n(リクエスト処理)"]
        Service["サービス\n(ビジネスロジック)"]
        Repository["リポジトリ\n(MyBatis)"]
    end

    subgraph データベース["Database"]
        MySQL["MySQL"]
    end

    User -->|リクエスト| Browser
    Browser -->|リクエスト| Controller
    Controller -->|処理| Service
    Service -->|データ取得| Repository
    Repository -->|DB クエリ| MySQL
    MySQL -->|データ返却| Repository
    Repository -->|データ返却| Service
    Service -->|レスポンス生成| Controller
    Controller -->|レスポンス送信| Browser
```

---

## データベース ER 図
```mermaid
erDiagram
    MENU {
        Long menu_id PK "AUTO_INCREMENT"
        String menu_name "NOT NULL"
        Decimal menu_price "NOT NULL"
    }
    
    SEAT {
        Long seat_id PK "AUTO_INCREMENT"
        String seat_name "NOT NULL"
        Integer seat_price_amount
    }
    
    ORDERS {
        Long orders_id PK "AUTO_INCREMENT"
        String orders_menu_name "NOT NULL"
        Integer orders_amount "DEFAULT 1"
        Integer orders_price_amount
        Timestamp orders_datetime "DEFAULT CURRENT_TIMESTAMP"
        Long orders_seat_id FK "REFERENCES SEAT(seat_id)"
    }
    
    SEAT ||--o{ ORDERS : "1:N"
```

---

## セットアップ & 実行方法
### **環境構築**
- JDK 17 以上をインストール
- MySQL (DB: `restaurant_db`) をセットアップ
- `application.yml` にデータベース情報を設定

### **アクセス**
- **アプリ URL:** `http://localhost:8080`

---

## スクリーンショット
### ホーム画面
ホーム画面では、メニューを設定するボタンと、レストランの席ごとの注文を管理する画面を選択できます。

<img width="1448" alt="Image" src="https://github.com/user-attachments/assets/4d9d5994-9801-49a3-855a-bbf46e1564e2" />

### メニュー設定
メニューを設定、編集、削除する画面です。

<img width="1113" alt="Image" src="https://github.com/user-attachments/assets/d23e0a2b-9c7e-4aad-ba11-30c82c750ac8" />

メニューは名前と価格を設定します。

<img width="1106" alt="Image" src="https://github.com/user-attachments/assets/5dbd932b-c2b0-42d2-bc28-6eb387c8925a" />


### 席の設定
各テーブルごとの注文状況を一目で確認できます。注文の合計金額は、そのテーブルで注文された合計金額です。会計が完了したら、リセットボタンを押してください。

<img width="1432" alt="Image" src="https://github.com/user-attachments/assets/8035917c-5943-4419-8237-498354ffc549" />

注文を設定する際は、名前のみを設定します。名前はテーブルごとに変更や削除が可能です。

<img width="1431" alt="Image" src="https://github.com/user-attachments/assets/80af7391-19ac-43e8-8e3c-540758202031" />

### 注文詳細
注文リストでは、テーブルの顧客の注文を追加できます。

<img width="1310" alt="Image" src="https://github.com/user-attachments/assets/a5997f80-c341-4d73-8a73-8f1d46c0e4c8" />

「注文追加」ボタンを押すと、注文可能なメニューがセレクトボックスに表示されます。注文の数量とメニューを選択します。

<img width="1320" alt="Image" src="https://github.com/user-attachments/assets/f7ef0fb6-7564-41bc-af7f-3ffc08c5cb49" />

注文を設定すると、テーブル画面には次のように表示されます。

<img width="1352" alt="Image" src="https://github.com/user-attachments/assets/0cd4bdd5-a701-4d8e-ba62-05516ed08866" />

リセットボタンを押すと、すべての注文が削除されます。

<img width="1129" alt="Image" src="https://github.com/user-attachments/assets/d8911aad-5939-4ce4-bac9-1b9c503359f3" />

---

## ✨ 今後のアップデート予定
- **検索機能**: メニューや注文を検索できる機能
- **予約機能**: 座席の事前予約を可能にする









