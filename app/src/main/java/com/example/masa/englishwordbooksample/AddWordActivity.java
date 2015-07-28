package com.example.masa.englishwordbooksample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jp.co.techfun.englishwordbook.R;

// 単語登録画面Activity
public class AddWordActivity extends Activity {
    // 単語情報保存用DBインスタンス
    private EnglishWordBookDbUtill dbUtil;

    // ボタンのタグ文字列定義
    // 「単語を登録」ボタンタグ
    static final String BTN_SAVE = "btnSave";
    // 「トップへ」ボタンタグ
    static final String BTN_TOP = "btnTop";


    // onCreateメソッド(画面初期表示イベント)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイル指定
        setContentView(R.layout.addword);

        // 「単語を登録」ボタンにリスナー設定
        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setTag(BTN_SAVE);
        btnSave.setOnClickListener(buttonClickListener);

        // 「トップへ」ボタンにリスナー設定
        Button btnTopPage = (Button) findViewById(R.id.btn_toppage);
        btnTopPage.setTag(BTN_TOP);
        btnTopPage.setOnClickListener(buttonClickListener);

        // DBUtilインスタンス生成
        dbUtil = new EnglishWordBookDbUtill(this);
    }

    // ボタンクリックリスナーの定義
    private OnClickListener buttonClickListener = new OnClickListener() {
        // onClickメソッド(クリックイベント)
        @Override
        public void onClick(View v) {
            // ボタンオブジェクト取得
            Button button = (Button) v;
            // 「単語を登録」ボタンの場合、単語登録
            if (button.getTag().equals(BTN_SAVE)) {
                // 確認ダイアログ表示
                AddWordActivity.this.showDialog();
                // 「トップへ」ボタンの場合、トップ画面へ遷移
            } else if (button.getTag().equals(BTN_TOP)) {
                // 画面クローズ
                finish();
            }
        }
    };

    // showDialogメソッド(確認ダイアログの表示)
    private void showDialog() {
        // ダイアログ生成
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(AddWordActivity.this);
        // ダイアログタイトル設定
        dialog.setTitle("タイトル");
        // ダイアログメッセージ設定
        dialog.setMessage("メッセージ");
        // 「はい」ボタン設定
        dialog.setPositiveButton("YES",
                dialogPositiveButtonClickListener);
        // 「いいえ」ボタン設定
        dialog.setNegativeButton("NO",
                dialogNegativeButtonClickListener);
        // ダイアログ表示
        dialog.show();
    }

    // ダイアログボタンクリックリスナーの定義(「はい」ボタン)
    private DialogInterface.OnClickListener dialogPositiveButtonClickListener =
            new DialogInterface.OnClickListener() {
                // onClickメソッド(ダイアログのボタンクリックイベント)
                public void onClick(DialogInterface dialog, int whichButton) {
                    // テキスト入力情報取得
                    EditText etEnglishword =
                            (EditText) findViewById(R.id.et_englishword);
                    EditText etJapaneseword =
                            (EditText) findViewById(R.id.et_japaneseword);
                    // 未入力の場合、エラーメッセージをトーストで表示
                    if (etEnglishword.getText().toString().equals("")
                            || etJapaneseword.getText().toString().equals("")) {
                        Toast.makeText(AddWordActivity.this,
                                "クリックされました", Toast.LENGTH_LONG).show();
                        return;
                        // 入力されている場合、データベースに登録
                    }

                    // WordBeanインスタンス生成
                    WordBean wbn =
                            new WordBean(etEnglishword.getText().toString(),
                                    etJapaneseword.getText().toString());

                    // DBへ単語情報を登録
                    dbUtil.addWord(wbn);
                }
            };

    // ダイアログボタンクリックリスナーの定義(「いいえ」ボタン)
    private DialogInterface.OnClickListener dialogNegativeButtonClickListener =
            new DialogInterface.OnClickListener() {
                // onClickメソッド(ダイアログのボタンクリックイベント)
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            };
}
