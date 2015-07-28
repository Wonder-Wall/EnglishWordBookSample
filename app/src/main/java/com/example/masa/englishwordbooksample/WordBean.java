package com.example.masa.englishwordbooksample;

// 英単語・日本語訳JavaBeans
public class WordBean {
    // 英単語
    private String englishword;
    // 日本語訳
    private String japaneseword;

    // コンストラクタ
    public WordBean(String englishword, String japaneseword) {

        // 英単語を設定
        this.englishword = englishword;

        // 日本語訳を設定
        this.japaneseword = japaneseword;
    }

    // 英単語を返すメソッド
    public String getEnglishword() {
        return englishword;
    }

    // 英単語を設定するメソッド
    public void setEnglishword(String englishword) {
        this.englishword = englishword;
    }

    // 日本語訳を返すメソッド
    public String getJapaneseword() {
        return japaneseword;
    }

    // 日本語訳を設定するメソッド
    public void setJapaneseword(String japaneseword) {
        this.japaneseword = japaneseword;
    }
}
