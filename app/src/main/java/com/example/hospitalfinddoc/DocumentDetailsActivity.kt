package com.example.hospitalfinddoc

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DocumentDetailsActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    private lateinit var tvDocumentName: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvWard: TextView
    private lateinit var tvStorage: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvLastSeen: TextView

    private lateinit var databaseHelper: DatabaseHelper

    private var documentId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_document_details
        )

        databaseHelper = DatabaseHelper(this)

        btnBack =
            findViewById(R.id.btnBack)

        btnEdit =
            findViewById(R.id.btnEdit)

        btnDelete =
            findViewById(R.id.btnDelete)


        tvDocumentName =
            findViewById(R.id.tvDocumentName)

        tvCategory =
            findViewById(R.id.tvCategory)

        tvWard =
            findViewById(R.id.tvWard)

        tvStorage =
            findViewById(R.id.tvStorage)

        tvDescription =
            findViewById(R.id.tvDescription)

        tvLastSeen =
            findViewById(R.id.tvLastSeen)


        documentId =
            intent.getIntExtra(
                "DOCUMENT_ID",
                -1
            )


        if (documentId != -1) {

            loadDocument()

        } else {

            Toast.makeText(
                this,
                "Document not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }


        btnBack.setOnClickListener {
            finish()
        }


        btnEdit.setOnClickListener {

            Toast.makeText(
                this,
                "Edit functionality will be added next",
                Toast.LENGTH_SHORT
            ).show()
        }


        btnDelete.setOnClickListener {

            showDeleteConfirmation()
        }
    }
    private fun loadDocument() {

        val document =
            databaseHelper.getDocumentById(
                documentId
            )

        if (document != null) {

            tvDocumentName.text =
                document.documentName

            tvCategory.text =
                document.category

            tvWard.text =
                document.ward

            tvStorage.text =
                document.storageLocation

            tvDescription.text =
                document.description

            tvLastSeen.text =
                document.lastSeen
        }
    }
    private fun showDeleteConfirmation() {

        AlertDialog.Builder(this)
            .setTitle("Delete Document")
            .setMessage(
                "Are you sure you want to delete this document?"
            )

            .setPositiveButton("Delete") { _, _ ->

                deleteDocument()
            }

            .setNegativeButton(
                "Cancel",
                null
            )

            .show()
    }


    private fun deleteDocument() {

        val result =
            databaseHelper.deleteDocument(
                documentId
            )


        if (result > 0) {

            Toast.makeText(
                this,
                "Document deleted successfully",
                Toast.LENGTH_SHORT
            ).show()

            finish()

        } else {

            Toast.makeText(
                this,
                "Failed to delete document",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}