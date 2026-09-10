package com.example.hospitalfinddoc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerDocuments: RecyclerView
    private lateinit var documentAdapter: DocumentAdapter
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var etSearch: EditText
    private var allDocuments = ArrayList<Document>()

    private lateinit var tvDocumentCount: TextView

    private lateinit var ivEmptyDocuments: View
    private lateinit var tvEmptyTitle: TextView
    private lateinit var tvEmptyMessage: TextView

    private lateinit var fabAdd: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        databaseHelper = DatabaseHelper(this)


        recyclerDocuments =
            findViewById(R.id.recyclerDocuments)


        etSearch =
            findViewById(R.id.etSearch)


        tvDocumentCount =
            findViewById(R.id.tvDocumentCount)


        ivEmptyDocuments =
            findViewById(R.id.ivEmptyDocuments)


        tvEmptyTitle =
            findViewById(R.id.tvEmptyTitle)


        tvEmptyMessage =
            findViewById(R.id.tvEmptyMessage)


        fabAdd =
            findViewById(R.id.fabAdd)


        recyclerDocuments.layoutManager =
            LinearLayoutManager(this)


        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }


            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                filterDocuments(s.toString())
            }


            override fun afterTextChanged(s: Editable?) {
            }
        })


        fabAdd.setOnClickListener {

            val intent = Intent(
                this,
                AddDocumentActivity::class.java
            )

            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()

        loadDocuments()
    }


    private fun loadDocuments() {

        val documentList =
            databaseHelper.getAllDocuments()


        allDocuments.clear()

        allDocuments.addAll(documentList)


        documentAdapter =
            DocumentAdapter(ArrayList(documentList))


        recyclerDocuments.adapter =
            documentAdapter


        if (documentList.isEmpty()) {

            recyclerDocuments.visibility =
                View.GONE

            ivEmptyDocuments.visibility =
                View.VISIBLE

            tvEmptyTitle.visibility =
                View.VISIBLE

            tvEmptyMessage.visibility =
                View.VISIBLE


            tvDocumentCount.text =
                "0 Documents"

        } else {

            recyclerDocuments.visibility =
                View.VISIBLE

            ivEmptyDocuments.visibility =
                View.GONE

            tvEmptyTitle.visibility =
                View.GONE

            tvEmptyMessage.visibility =
                View.GONE


            tvDocumentCount.text =
                "${documentList.size} Documents"
        }
    }


    private fun filterDocuments(query: String) {

        val filteredList = ArrayList<Document>()

        for (document in allDocuments) {

            if (

                document.documentName.contains(
                    query,
                    ignoreCase = true
                )

                ||

                document.ward.contains(
                    query,
                    ignoreCase = true
                )

                ||

                document.storageLocation.contains(
                    query,
                    ignoreCase = true
                )

                ||

                document.category.contains(
                    query,
                    ignoreCase = true
                )
            ) {

                filteredList.add(document)
            }
        }


        if (::documentAdapter.isInitialized) {

            documentAdapter.updateList(filteredList)
        }
    }
}