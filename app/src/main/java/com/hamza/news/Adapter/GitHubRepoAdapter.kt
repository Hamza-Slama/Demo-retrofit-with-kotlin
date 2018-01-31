package com.hamza.news.Adapter

/**
 * Created by hamza on 31/01/18.
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.hamza.news.Models.Repo
import com.hamza.news.R
import kotlinx.android.synthetic.main.repo_name_ticket.view.*

class GitHubRepoAdapter : BaseAdapter {
    var context:Context?=null
    lateinit var listOfRepoModelLocal : List<Repo>

    constructor(context: Context, listOfRepoModel:List<Repo>):super(){
        this.context=context
        listOfRepoModelLocal=listOfRepoModel
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val RepoModel =listOfRepoModelLocal[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var RepoModelView= inflator.inflate(R.layout.repo_name_ticket,null)
        RepoModelView.repoName.text = RepoModel.name!!
        return RepoModelView
//           }
    }

    //Not Used
    override fun getItem(p0: Int): Any {
        return listOfRepoModelLocal[p0]
    }
    //Not Used
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {

        return listOfRepoModelLocal.size
    }

}