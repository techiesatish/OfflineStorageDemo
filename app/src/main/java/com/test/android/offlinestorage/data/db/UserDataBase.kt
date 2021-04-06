package com.test.android.offlinestorage.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.android.offlinestorage.data.db.entity.UserDetails

@Database(
    entities = [UserDetails::class],
    version = 1,
    exportSchema = false
)

abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "note_database.db"
        @Volatile private var instance: UserDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDataBase::class.java,
            DB_NAME
        ).build()
    }
}