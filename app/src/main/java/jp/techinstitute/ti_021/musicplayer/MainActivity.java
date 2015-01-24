package jp.techinstitute.ti_021.musicplayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    // ヘルパークラス
    CreateProductHelper helper = null;
    // DB
    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DBから曲名一覧を取得する
        List lstMusic = new ArrayList();
        lstMusic = getMusicNameList();

        // リストビューに曲名を設定する
        ArrayAdapter adpAryMusic = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstMusic);

        // 初回登録ボタンを設定
        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setTag("INSERT");
        btnInsert.setOnClickListener(new btnClick());

        // 登録内容確認ボタンを設定
        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setTag("SELECT");
        btnSelect.setOnClickListener(new btnClick());

    }

    private List getMusicNameList() {
        List lstResult = new ArrayList();

        // DB作成
        helper = new CreateProductHelper(MainActivity.this);

        try {
            // DBオブジェクトを生成する
            db = helper.getReadableDatabase();

            // 取得する項目を設定する
            String[] strAryGetClm = {DBConst.TIT_MSY};

            // DBから値を取得する
            Cursor cursor = db.query(DBConst.TBN_MSC_PLY_LST, strAryGetClm, null, null, null, null, DBConst._ID);

            while (cursor.moveToNext()) {
                lstResult.add(cursor.getString(0));
            }
        } catch (Exception e){
            Toast.makeText(this, "エラー発生：" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lstResult;
    }

    private class btnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // DB作成
            helper = new CreateProductHelper(MainActivity.this);
            String strTag = (String) v.getTag();

            // テーブルレイアウトを初期化する。
            TableLayout tbl = (TableLayout) findViewById(R.id.tbl_db);
            tbl.removeAllViews();

            if ("INSERT".equals(strTag)) {
                // 更新用DBオブジェクトを取得
                db = helper.getWritableDatabase();

                // SQL文作成
                String str = "create table " + DBConst.TBN_MSC_PLY_LST + " (" +
                        DBConst._ID + " integer primary key autoincrement," +
                        DBConst.RES_ID + " integer not null," +
                        DBConst.TIT_MSY + " text not null)";
                try {
                    // SQL実行
                    db.execSQL(str);
                    // メッセージ出力
                    Toast.makeText(MainActivity.this, "テーブルを作成しました。", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("エラー", e.getMessage());
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                try {
                    // トランザクション開始
                    db.beginTransaction();
                    // 登録中のデータを削除
                    db.delete(DBConst.TBN_MSC_PLY_LST, null, null);

                    // データ登録
                    ContentValues val = new ContentValues();
                    val.put(DBConst.RES_ID, R.raw.river_song);
                    val.put(DBConst.TIT_MSY, "川の歌");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.valentain_song);
                    val.put(DBConst.TIT_MSY, "バレンタインの歌");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.sokka);
                    val.put(DBConst.TIT_MSY, "そっか");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.happy);
                    val.put(DBConst.TIT_MSY, "やさしい気持ち");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.crazy);
                    val.put(DBConst.TIT_MSY, "クレイジークッキング");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.summer);
                    val.put(DBConst.TIT_MSY, "サマーガーデン");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.labo);
                    val.put(DBConst.TIT_MSY, "ラボラトリー");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.pinch);
                    val.put(DBConst.TIT_MSY, "大慌て");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.school);
                    val.put(DBConst.TIT_MSY, "教室");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.ragu);
                    val.put(DBConst.TIT_MSY, "気まぐれラグ");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);
                    val.put(DBConst.RES_ID, R.raw.shine);
                    val.put(DBConst.TIT_MSY, "風光る");
                    db.insert(DBConst.TBN_MSC_PLY_LST, null, val);

                    // コミット、トランザクション終了
                    db.setTransactionSuccessful();
                    db.endTransaction();

                    // メッセージ出力
                    Toast.makeText(MainActivity.this, "データが登録できました。", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.e("エラー", e.getMessage());
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            } else if ("SELECT".equals(strTag)) {

                try {
                    // 参照用のDBオブジェクトを取得する。
                    db = helper.getReadableDatabase();
                    // 取得カラム設定
                    String[] strAryDBClm = {DBConst._ID, DBConst.RES_ID, DBConst.TIT_MSY};

                    // DBデータ取得
                    Cursor cursor = db.query(DBConst.TBN_MSC_PLY_LST, strAryDBClm, null, null, null, null, DBConst._ID);

                    // テーブルの表示範囲を設定
                    tbl.setStretchAllColumns(true);
                    // テーブルヘッダ設定
                    TableRow tblRow = new TableRow(MainActivity.this);
                    TextView txv1 = new TextView(MainActivity.this);
                    txv1.setText(DBConst.NM_ID);
                    txv1.setGravity(Gravity.CENTER_HORIZONTAL);
                    txv1.setWidth(100);
                    TextView txv2 = new TextView(MainActivity.this);
                    txv2.setText(DBConst.NM_RES_ID);
                    txv2.setGravity(Gravity.CENTER_HORIZONTAL);
                    txv2.setWidth(150);
                    TextView txv3 = new TextView(MainActivity.this);
                    txv3.setText(DBConst.NM_TIT_MSY);
                    txv3.setGravity(Gravity.CENTER_HORIZONTAL);
                    txv3.setWidth(150);
                    tblRow.addView(txv1);
                    tblRow.addView(txv2);
                    tblRow.addView(txv3);
                    tbl.addView(tblRow);

                    // テーブルデータ行設定
                    while (cursor.moveToNext()) {
                        TableRow tblDataRow = new TableRow(MainActivity.this);
                        TextView txv4 = new TextView(MainActivity.this);
                        txv4.setText(cursor.getString(0));
                        txv4.setGravity(Gravity.CENTER_HORIZONTAL);
                        TextView txv5 = new TextView(MainActivity.this);
                        txv5.setText(cursor.getString(1));
                        txv5.setGravity(Gravity.CENTER_HORIZONTAL);
                        TextView txv6 = new TextView(MainActivity.this);
                        txv6.setText(cursor.getString(2));
                        txv6.setGravity(Gravity.CENTER_HORIZONTAL);
                        tblDataRow.addView(txv4);
                        tblDataRow.addView(txv5);
                        tblDataRow.addView(txv6);
                        tbl.addView(tblDataRow);
                    }
                    // メッセージ出力
                    Toast.makeText(MainActivity.this, "データを設定しました。", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.e("エラー", e.getMessage());
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            // DBオブジェクトをクローズする
            db.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
