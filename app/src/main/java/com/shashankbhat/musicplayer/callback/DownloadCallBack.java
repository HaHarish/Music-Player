package com.shashankbhat.musicplayer.callback;

public interface DownloadCallBack {
    void onCompleteListener(String path);
    void onProgressUpdate(int progress);
}
