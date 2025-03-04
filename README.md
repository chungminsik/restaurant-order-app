# レストラン注文管理システム
## 概要
レストランを運営する際にテーブル注文を受けて注文履歴を計算してくれる機能の職員用アプリケーションです。

従業員は自分のスマートフォンで注文を受け、支払い時に総額を計算できます。

## このアプリを使ってできること
1. 登録したメニューから注文を受けることができる。
2. WEBを通じて注文を受けて職員がレストランの注文情報を一緒に共有することができる。
3. 顧客が注文した数量をWEB画面で直接登録して合計を計算することができる

# 技術スペック
## back-end framework : Java spring boot
- 使用理由 : 
  1. ウェブプロジェクトに必要な多くの機能を持っている(Tomcat、テストツールなど)
  2. Springのモジュール化された設計構造によりアプリケーションの拡張性が良い
  3. 多くの開発者が使用しているウェブプログラミングツールとして多くのリファレンス資料がある

## front-end : thymeleaf, Javascript
- 使用理由 :　
  1. 速い画面開発のためにThymeleafを利用

## database : mybatis, mysql
- 使用理由 : 
  1. オープンソース関係型データベースの一つとして多くの開発者が使用する技術であるため、リファレンス資料が豊富。
  2. 小規模のプロジェクトでも十分な性能を保障する。 特にトランザクション処理、インデックス機能を基本に持っているため、安定したデータ管理が可能。

# 機能 & サービス説明
機能は大きくメニュー管理、ホーム管理、席管理に分かれています

## ホーム画面
レストランにメニューを登録するボタンと、テーブルと注文を管理するボタンが存在します。
![Image](https://github.com/user-attachments/assets/f72d288e-9b73-4edd-8bab-2b3c18e1ba50)

## メニュー管理
すでに登録されているメニューを見ることができます。
![Image](https://github.com/user-attachments/assets/b8a98be7-084f-40e5-9e3f-a7ff0e6fa8e5)

レストランで注文できるメニューを登録します。
![Image](https://github.com/user-attachments/assets/7c5e7918-335c-4c63-bd36-fb3a05ce5582)

## 席管理
テーブルを設定できる画面です。 レストランによってテーブルの数と名前を決めることができます。

テーブルでは、テーブルで注文した価格の合計を表示して計算するときに便利です。

注文が完了したら、初期化ボタンを押して、新しいお客様のための準備をします。
![Image](https://github.com/user-attachments/assets/c55168bf-43d7-411c-826a-e3ec343c46ea)

## 注文管理
テーブルごとに注文を等読できます。
![Image](https://github.com/user-attachments/assets/6300761c-d17e-432e-89d7-a36eeb8c1237)

数量とメニューを定めることができます。数量の基本値は1です。
![Image](https://github.com/user-attachments/assets/0595caa0-332e-45dd-b8e7-ff3014db211b)

注文登録はメニューから登録したメニューをセレクトボックスから選択できます。
![Image](https://github.com/user-attachments/assets/9620bdbb-021d-4a4e-bedd-80344d592b7f)

注文を追加すると次のようにテーブルに表示されます。
![Image](https://github.com/user-attachments/assets/cd4c1f8f-1caa-4014-8e50-b87e0d16a753)

初期化ボタンを押すと、注文に保存した内容が消えます。
![Image](https://github.com/user-attachments/assets/59a05b44-0e5d-4a23-aa6f-768953bb1f1b)

# アーキテクチャ説明


## アーキテクチャ
- Controller：リクエストを受け取り、レスポンスを返す
- Service：ビジネスロジックを処理する
- Repository（DAO）：データベースと接続する
- Entity：データモデルを表す

## request流れ
```mermaid
graph TD;
    User -->|Request| Controller;
    Controller -->|Process| Service;
    Service -->|Data Access| Repository;
    Repository -->|Query| Database;
```

## テーブルERD
```mermaid
erDiagram
    MENU {
        int menu_id PK
        varchar(30) menu_name
        decimal(10) menu_price
    }
    
    SEAT {
        int seat_id PK
        varchar(30) seat_name
        int seat_price_amount
    }
    
    ORDERS {
        int orders_id PK
        varchar(30) orders_menu_name
        int orders_amount
        int orders_price_amount
        timestamp orders_datetime
        int orders_seat_id FK
    }

    SEAT ||--o{ ORDERS : "1:N"
```







