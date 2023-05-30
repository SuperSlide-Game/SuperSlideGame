import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.superslidegame.database.GameDao
import com.example.superslidegame.game.entities.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Game::class], version = 1, exportSchema = false)

abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    private class GameDatabaseCallback(private val scope: CoroutineScope)
        : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.gameDao())
                }
            }
        }

        suspend fun populateDatabase(gameDao: GameDao) {
            // Delete all content here.
            gameDao.deleteAll()

            // Add sample words.
            var game = Game(0, "Hello", 1, true, 10, 1)
            gameDao.insert(game)
            game = Game(0, "World", 1, false, 10, 1)
            gameDao.insert(game)
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): GameRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "game_database"
                )
                .addCallback(GameDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}