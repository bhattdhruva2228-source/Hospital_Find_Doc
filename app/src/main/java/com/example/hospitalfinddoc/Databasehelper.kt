package com.example.hospitalfinddoc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

    companion object {

        private const val DATABASE_NAME = "FindDoc.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_DOCUMENTS = "documents"

        private const val COLUMN_ID = "id"
        private const val COLUMN_DOCUMENT_NAME = "document_name"
        private const val COLUMN_WARD = "ward"
        private const val COLUMN_STORAGE = "storage_location"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_LAST_SEEN = "last_seen"
    }


    override fun onCreate(db: SQLiteDatabase) {

        val query = """
            CREATE TABLE $TABLE_DOCUMENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DOCUMENT_NAME TEXT,
                $COLUMN_WARD TEXT,
                $COLUMN_STORAGE TEXT,
                $COLUMN_CATEGORY TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_LAST_SEEN TEXT
            )
        """.trimIndent()

        db.execSQL(query)
    }


    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_DOCUMENTS"
        )

        onCreate(db)
    }


    fun insertDocument(document: Document): Long {

        val db = writableDatabase
        val values = ContentValues()

        values.put(
            COLUMN_DOCUMENT_NAME,
            document.documentName
        )

        values.put(
            COLUMN_WARD,
            document.ward
        )

        values.put(
            COLUMN_STORAGE,
            document.storageLocation
        )

        values.put(
            COLUMN_CATEGORY,
            document.category
        )

        values.put(
            COLUMN_DESCRIPTION,
            document.description
        )

        values.put(
            COLUMN_LAST_SEEN,
            document.lastSeen
        )

        val result = db.insert(
            TABLE_DOCUMENTS,
            null,
            values
        )

        db.close()

        return result
    }


    fun getAllDocuments(): ArrayList<Document> {

        val documentList = ArrayList<Document>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_DOCUMENTS ORDER BY $COLUMN_ID DESC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val document = Document(

                    id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_ID
                        )
                    ),

                    documentName = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_DOCUMENT_NAME
                        )
                    ),

                    ward = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_WARD
                        )
                    ),

                    storageLocation = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_STORAGE
                        )
                    ),

                    category = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_CATEGORY
                        )
                    ),

                    description = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_DESCRIPTION
                        )
                    ),

                    lastSeen = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            COLUMN_LAST_SEEN
                        )
                    )
                )

                documentList.add(document)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return documentList
    }


    fun getDocumentById(id: Int): Document? {

        val db = readableDatabase

        val cursor = db.query(
            TABLE_DOCUMENTS,
            null,
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var document: Document? = null

        if (cursor.moveToFirst()) {

            document = Document(

                id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_ID
                    )
                ),

                documentName = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_DOCUMENT_NAME
                    )
                ),

                ward = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_WARD
                    )
                ),

                storageLocation = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_STORAGE
                    )
                ),

                category = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_CATEGORY
                    )
                ),

                description = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_DESCRIPTION
                    )
                ),

                lastSeen = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_LAST_SEEN
                    )
                )
            )
        }

        cursor.close()
        db.close()

        return document
    }



    fun deleteDocument(id: Int): Int {

        val db = writableDatabase

        val result = db.delete(
            TABLE_DOCUMENTS,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )

        db.close()

        return result
    }
}