package com.example.hospitalfinddoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DocumentAdapter(
    private val documentList: ArrayList<Document>
) : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {


    class DocumentViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val ivDocument: ImageView =
            itemView.findViewById(R.id.ivDocument)

        val tvDocumentName: TextView =
            itemView.findViewById(R.id.tvDocumentName)

        val tvLocation: TextView =
            itemView.findViewById(R.id.tvLocation)

        val tvLastSeen: TextView =
            itemView.findViewById(R.id.tvLastSeen)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DocumentViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_document_card,
                parent,
                false
            )

        return DocumentViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: DocumentViewHolder,
        position: Int
    ) {

        val document = documentList[position]

        holder.tvDocumentName.text =
            document.documentName

        holder.tvLocation.text =
            "${document.ward} • ${document.storageLocation}"

        holder.tvLastSeen.text =
            "Last seen: ${document.lastSeen}"

        holder.ivDocument.setImageResource(
            android.R.drawable.ic_menu_agenda
        )
    }


    fun updateList(newList: ArrayList<Document>) {

        documentList.clear()

        documentList.addAll(newList)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {

        return documentList.size
    }
}