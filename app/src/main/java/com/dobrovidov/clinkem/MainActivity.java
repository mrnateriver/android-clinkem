/*
 * Copyright (c) 2016 Evgenii Dobrovidov
 * This file is part of "Clink'em!".
 *
 * "Clink'em!" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Clink'em!" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with "Clink'em!".  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dobrovidov.clinkem;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClink(View button) {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.reset();
            mPlayer.release();
        }

        Random rand = new Random();
        int randomNum = rand.nextInt(6) + 1;

        int soundID = this.getResources().getIdentifier("clink" + randomNum, "raw", this.getPackageName());

        mPlayer = MediaPlayer.create(button.getContext(), soundID);
        mPlayer.setOnCompletionListener(this);
        mPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mPlayer.reset();
        mPlayer.release();
        mPlayer = null;
    }
}
