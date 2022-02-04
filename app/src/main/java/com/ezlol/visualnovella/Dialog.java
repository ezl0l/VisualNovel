package com.ezlol.visualnovella;

import android.util.Log;

public class Dialog {
    public interface OnDialogEndListener {
        void onDialogEnd(Dialog dialog);
    }

    static class Phrase {
        public String author, content;

        public Phrase(String author, String content) {
            this.author = author;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Phrase{" +
                    "author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public int id;
    public Phrase[] phrases;
    public int cursor;
    public boolean isEnded = false;

    protected static OnDialogEndListener onDialogEndListener = null;
    protected OnDialogEndListener onDialogEndListenerNonStatic = null; // todo КОСТЫЫЫЫЫЛЬ!!!

    public Dialog(int id, Phrase[] phrases) {
        this.id = id;
        this.phrases = phrases;
    }

    public boolean next() {
        Log.d("My", "phrases: " + cursor + " " + phrases.length);
        if(cursor + 2 >= phrases.length && !isEnded) {
            isEnded = true;
            if(onDialogEndListener != null)
                onDialogEndListener.onDialogEnd(this);
            if(onDialogEndListenerNonStatic != null)
                onDialogEndListenerNonStatic.onDialogEnd(this);
        }

        if (cursor + 1 >= phrases.length)
            return false;
        cursor++;
        return true;
    }

    public Phrase getCurrentPhrase() {
        return phrases[cursor];
    }
}
