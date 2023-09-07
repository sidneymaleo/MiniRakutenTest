package com.rakutentest.android.presentation.di

import android.app.Application
import androidx.room.Room
import com.rakutentest.android.data.db.RakutenDatabase
import com.rakutentest.android.data.db.dao.BuyBoxDAO
import com.rakutentest.android.data.db.dao.ProductDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    /**
     * Here we inject our room data database
     */
    @Singleton
    @Provides
    fun provideRakutenDatabase(app: Application): RakutenDatabase {
        return Room.databaseBuilder(app,RakutenDatabase::class.java, name = "rakuten_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Here we inject our ProductDAO
     */
    @Singleton
    @Provides
    fun provideProductDao(rakutenDatabase: RakutenDatabase): ProductDAO {
        return rakutenDatabase.getProductDAO()
    }

    /**
     * Here we inject our BuyBoxDAO
     */
    @Singleton
    @Provides
    fun provideBuyBoxDao(rakutenDatabase: RakutenDatabase): BuyBoxDAO {
        return rakutenDatabase.getBuyBoxDAO()
    }
}