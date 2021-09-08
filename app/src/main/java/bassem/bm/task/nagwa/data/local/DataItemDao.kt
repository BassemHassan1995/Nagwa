package bassem.bm.task.nagwa.data.local

import androidx.room.*
import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DataItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(items: List<DataItem>) : Completable

    @Query("SELECT * FROM DataItem")
    fun getAllItems(): Single<List<DataItem>>

    @Query("SELECT * FROM DataItem WHERE isDownloaded = 1 ")
    fun getDownloadedItems(): Single<List<DataItem>>

    @Update
    fun downloadItem(dataItem: DataItem): Single<Unit>
}