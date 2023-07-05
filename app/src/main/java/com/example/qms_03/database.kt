package com.example.qms_03

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.mindrot.jbcrypt.BCrypt

data class User(
    val userID: Int?,
    val email: String,
    val password: String,
    val studentID: String,
    val firstName: String,
    val lastName: String,
    val isAdmin: Boolean
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "database.db"
        private const val DATABASE_VERSION = 4 // Updated to 4

        // Define the table and column names
        private const val TABLE_NAME = "User"
        private const val COLUMN_USER_ID = "userID"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_STUDENT_ID = "studentID"
        private const val COLUMN_FIRST_NAME = "firstName"
        private const val COLUMN_LAST_NAME = "lastName"
        private const val COLUMN_IS_ADMIN = "isAdmin"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_STUDENT_ID TEXT,
                $COLUMN_FIRST_NAME TEXT,
                $COLUMN_LAST_NAME TEXT,
                $COLUMN_IS_ADMIN BOOLEAN
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 4) { // Updated to 4
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PASSWORD, hashedPassword)
            put(COLUMN_STUDENT_ID, user.studentID)
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_IS_ADMIN, if (user.isAdmin) 1 else 0)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun validateUser(email: String, password: String): User? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        if (cursor.moveToFirst()) {
            val hashedPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            if (BCrypt.checkpw(password, hashedPassword)) {
                val user = User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_ADMIN)) == 1
                )
                cursor.close()
                return user
            }
        }
        cursor.close()
        return null
    }


    fun getAllUsers(): List<User> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val userList = mutableListOf<User>()
        cursor.use {
            while (it.moveToNext()) {
                val user = User(
                    it.getInt(it.getColumnIndexOrThrow(COLUMN_USER_ID)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_STUDENT_ID)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_FIRST_NAME)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_LAST_NAME)),
                    it.getInt(it.getColumnIndexOrThrow(COLUMN_IS_ADMIN)) == 1
                )
                userList.add(user)
            }
        }
        db.close()
        return userList
    }
}
