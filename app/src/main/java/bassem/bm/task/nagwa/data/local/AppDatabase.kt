package bassem.bm.task.nagwa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import bassem.bm.task.nagwa.data.model.DataItem

@Database(entities = [DataItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataItemDao(): DataItemDao

}