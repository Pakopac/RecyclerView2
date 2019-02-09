package com.example.lilia.recyclerview2.files

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lilia.recyclerview2.BaseActivity
import com.example.lilia.recyclerview2.R
import com.example.lilia.recyclerview2.files.model.File
import kotlinx.android.synthetic.main.files_list_activity.*
import kotlinx.android.synthetic.main.files_list_item.view.*
import java.lang.Long.parseLong
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class FilesListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.files_list_activity)

        fun getDate(s: String): String{
            val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.US)
            val date = formatter.parse(s)
            formatter.applyPattern("dd MMM yyyy")
            return formatter.format(date)
        }


        val files = listOf(
            File(
                name = "Bill Report",
                author = "You",
                date = getDate("2018-02-12")
            ),
            File(
                name = "Diagnostics Reports",
                author = "Ayush Kumar",
                date = getDate("2018-02-20")
            )
        )


        files_list.layoutManager = LinearLayoutManager(this)
        files_list.adapter = FileListAdapter(files)
    }
}
private class FileListAdapter(val files: List<File>) : RecyclerView.Adapter<FileViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FileViewHolder {
        return FileViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.files_list_item, p0, false))
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bindFiles(files[position])
    }

    override fun getItemCount(): Int {
        return files.size
    }
}

private class FileViewHolder (v : View) : RecyclerView.ViewHolder(v) {

    private val name : AppCompatTextView = v.file_name
    private val author : AppCompatTextView = v.file_author
    private val date : AppCompatTextView = v.file_date

    fun bindFiles(files:File) {
        name.text = files.name
        author.text = files.author
        date.text = files.date
    }
}
