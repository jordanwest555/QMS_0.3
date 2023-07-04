import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class User(
    val userID: Int,
    val email: String,
    val studentID: String,
    val firstName: String,
    val lastName: String,
    val isAdmin: Boolean
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 2

        // Define the table and column names
        private const val TABLE_NAME = "User"
        private const val COLUMN_USER_ID = "userID"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_STUDENT_ID = "studentID"
        private const val COLUMN_FIRST_NAME = "firstName"
        private const val COLUMN_LAST_NAME = "lastName"
        private const val COLUMN_IS_ADMIN = "isAdmin"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_USER_ID INTEGER PRIMARY KEY,
                $COLUMN_EMAIL TEXT,
                $COLUMN_STUDENT_ID TEXT,
                $COLUMN_FIRST_NAME TEXT,
                $COLUMN_LAST_NAME TEXT,
                $COLUMN_IS_ADMIN BOOLEAN
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            val alterTableQuery = """
                ALTER TABLE $TABLE_NAME
                ADD COLUMN $COLUMN_IS_ADMIN BOOLEAN DEFAULT 0
            """.trimIndent()
            db.execSQL(alterTableQuery)
        }
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, user.userID)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_STUDENT_ID, user.studentID)
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_IS_ADMIN, if (user.isAdmin) 1 else 0)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllUsers(): List<User> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val userList = mutableListOf<User>()
        cursor.use {
            while (it.moveToNext()) {
                val user = User(
                    it.getInt(it.getColumnIndex(COLUMN_USER_ID)),
                    it.getString(it.getColumnIndex(COLUMN_EMAIL)),
                    it.getString(it.getColumnIndex(COLUMN_STUDENT_ID)),
                    it.getString(it.getColumnIndex(COLUMN_FIRST_NAME)),
                    it.getString(it.getColumnIndex(COLUMN_LAST_NAME)),
                    it.getInt(it.getColumnIndex(COLUMN_IS_ADMIN)) == 1
                )
                userList.add(user)
            }
        }
        db.close()
        return userList
    }

    fun getUserByID(userID: Int): List<User> {
        return getUsersByCondition("$COLUMN_USER_ID = $userID")
    }

    fun getUserByEmail(email: String): List<User> {
        return getUsersByCondition("$COLUMN_EMAIL = '$email'")
    }

    fun getUserByStudentID(studentID: String): List<User> {
        return getUsersByCondition("$COLUMN_STUDENT_ID = '$studentID'")
    }

    fun getUserByFirstName(firstName: String): List<User> {
        return getUsersByCondition("$COLUMN_FIRST_NAME = '$firstName'")
    }

    fun getUserByLastName(lastName: String): List<User> {
        return getUsersByCondition("$COLUMN_LAST_NAME = '$lastName'")
    }

    private fun getUsersByCondition(condition: String): List<User> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $condition", null)
        val userList = mutableListOf<User>()
        cursor.use {
            while (it.moveToNext()) {
                val user = User(
                    it.getInt(it.getColumnIndex(COLUMN_USER_ID)),
                    it.getString(it.getColumnIndex(COLUMN_EMAIL)),
                    it.getString(it.getColumnIndex(COLUMN_STUDENT_ID)),
                    it.getString(it.getColumnIndex(COLUMN_FIRST_NAME)),
                    it.getString(it.getColumnIndex(COLUMN_LAST_NAME)),
                    it.getInt(it.getColumnIndex(COLUMN_IS_ADMIN)) == 1
                )
                userList.add(user)
            }
        }
        db.close()
        return userList
    }
}
