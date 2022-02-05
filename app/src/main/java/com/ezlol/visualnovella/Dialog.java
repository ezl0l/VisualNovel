package com.ezlol.visualnovella;

import android.util.Log;

public class Dialog {
    public interface OnDialogEndListener {
        void onDialogEnd(Dialog dialog);
    }

    static class Choice {
        public Environment environment;
        public String agreeTitle, declineTitle,
                agreeAction, declineAction,
                agreeImage, declineImage;

        public Choice(Environment environment, String agreeTitle, String declineTitle, String agreeAction, String declineAction) {
            this.environment = environment;
            this.agreeTitle = agreeTitle;
            this.declineTitle = declineTitle;
            this.agreeAction = agreeAction;
            this.declineAction = declineAction;
        }

        public Choice(Environment environment, String agreeTitle, String declineTitle, String agreeAction, String declineAction, String agreeImage, String declineImage) {
            this.environment = environment;
            this.agreeTitle = agreeTitle;
            this.declineTitle = declineTitle;
            this.agreeAction = agreeAction;
            this.declineAction = declineAction;
            this.agreeImage = agreeImage;
            this.declineImage = declineImage;
        }
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

    public void reset() {
        onDialogEndListener = onDialogEndListenerNonStatic = null;
        cursor = 0;
    }

    public boolean next() {
        Log.d("My", "phrases: " + cursor + " " + phrases.length);
        if(cursor + 1 >= phrases.length) {
            if(!isEnded) {
                isEnded = true;
                if (onDialogEndListener != null)
                    onDialogEndListener.onDialogEnd(this);
                if (onDialogEndListenerNonStatic != null)
                    onDialogEndListenerNonStatic.onDialogEnd(this);
                reset();
            }
            return false;
        }

        cursor++;
        return true;
    }

    public Phrase getCurrentPhrase() {
        return phrases[cursor];
    }
}
