package com.example.hospitalfinddoc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddDocumentActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnSave: Button

    private lateinit var etDocumentName: EditText
    private lateinit var etWard: EditText
    private lateinit var etStorage: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var etDescription: EditText
    private lateinit var etLastSeen: EditText

    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_document)



        databaseHelper = DatabaseHelper(this)



        btnBack = findViewById(R.id.btnBack)
        btnSave = findViewById(R.id.btnSave)

        etDocumentName =
            findViewById(R.id.etDocumentName)

        etWard =
            findViewById(R.id.etWard)

        etStorage =
            findViewById(R.id.etStorage)

        spinnerCategory =
            findViewById(R.id.spinnerCategory)

        etDescription =
            findViewById(R.id.etDescription)

        etLastSeen =
            findViewById(R.id.etLastSeen)



        val categories = arrayOf(
            "Medical Report",
            "Patient File",
            "Prescription",
            "Lab Report",
            "Other"
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )

        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinnerCategory.adapter = spinnerAdapter



        btnBack.setOnClickListener {
            finish()
        }




        btnSave.setOnClickListener {
            saveDocument()
        }
    }


    private fun saveDocument() {



        val documentName =
            etDocumentName.text.toString().trim()

        val ward =
            etWard.text.toString().trim()

        val storage =
            etStorage.text.toString().trim()

        val category =
            spinnerCategory.selectedItem.toString()

        val description =
            etDescription.text.toString().trim()

        val lastSeen =
            etLastSeen.text.toString().trim()



        if (documentName.isEmpty()) {
            etDocumentName.error = "Enter document name"
            return
        }

        if (ward.isEmpty()) {
            etWard.error = "Enter ward or department"
            return
        }

        if (storage.isEmpty()) {
            etStorage.error = "Enter storage location"
            return
        }

        if (lastSeen.isEmpty()) {
            etLastSeen.error = "Enter last seen information"
            return
        }




        val document = Document(
            documentName = documentName,
            ward = ward,
            storageLocation = storage,
            category = category,
            description = description,
            lastSeen = lastSeen
        )




        val result =
            databaseHelper.insertDocument(document)


        if (result != -1L) {

            Toast.makeText(
                this,
                "Document saved successfully",
                Toast.LENGTH_SHORT
            ).show()




            finish()

        } else {

            Toast.makeText(
                this,
                "Failed to save document",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}