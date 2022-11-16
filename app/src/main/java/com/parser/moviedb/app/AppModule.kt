package com.parser.moviedb.app

import android.content.Context
import android.media.AudioManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MovieDBApplication {
        return app as MovieDBApplication
    }

    @Singleton
    @Provides
    fun provideAudioManager(@ApplicationContext app: Context): AudioManager {
        return app.getSystemService((Context.AUDIO_SERVICE)) as AudioManager
    }
}