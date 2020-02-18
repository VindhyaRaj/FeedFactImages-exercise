package com.display.feedimages

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.display.feedimages.adapter.FeedAdapter
import com.display.feedimages.interfaces.FactPresenter
import com.display.feedimages.interfaces.FactView
import com.display.feedimages.models.FactsModel

import java.util.ArrayList

/**
 * This is the main activity which is our View.
 * This class handles all the operation on the Views side.
 */

class MainActivity : AppCompatActivity(), FactView {

    private var srlRefresh: SwipeRefreshLayout? = null
    private var adapter: FeedAdapter? = null
    private var factsModels: ArrayList<FactsModel>? = null
    private var screenTitle: String? = null
    private var factPresenter: FactPresenter? = null
    private var tvNoData: TextView? = null
    private val FACTS_LIST = "FACTS_LIST"
    private val SCREEN_TITLE = "SCREEN_TITLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews(savedInstanceState)
    }

    /**
     * This will initialize the views
     * Checks if the factsModels is in savedInstanceState to handle the rotation properly.
     * initializes the adapter and adds it to the recycler view
     * initializes the presenter and interactor
     *
     * @param savedInstanceState To handle orientation change
     */
    private fun initViews(savedInstanceState: Bundle?) {


        if (savedInstanceState != null) {
            factsModels = savedInstanceState.getParcelableArrayList(FACTS_LIST)
            showTitle(savedInstanceState.getString(SCREEN_TITLE))
        } else {
            factsModels = ArrayList()
            showTitle("")
        }

        adapter = FeedAdapter(factsModels, this)
        val rvFacts = findViewById<RecyclerView>(R.id.rv_facts)
        rvFacts.layoutManager = LinearLayoutManager(this)
        rvFacts.adapter = adapter

        srlRefresh = findViewById(R.id.srl_refresh)

        factPresenter = FactPresenterImpl(this, FeedInteractorImpl())
        if (factsModels!!.isEmpty())
            checkConnectionAndGetFacts()

        srlRefresh!!.setOnRefreshListener { checkConnectionAndGetFacts() }

        tvNoData = findViewById(R.id.tv_no_data_found)
    }

    /**
     * This method will check if the internet is connected and only then will it call the api
     */
    private fun checkConnectionAndGetFacts() {
        if (Utils.isNetworkAvailable(this))
            factPresenter!!.getFact()
        else
            Toast.makeText(this, R.string.check_internet_connection, Toast.LENGTH_LONG).show()
    }

    /**
     * This method will set the toolbar title as given
     *
     * @param title The toolbar title to be set
     */
   override fun showTitle(title: String) {
        if (supportActionBar != null)
            supportActionBar!!.title = title
        screenTitle = title
    }

    /**
     * This method will send the data to the adapter and update the view if the ArrayList is not empty.
     * If the ArrayList is empty it will show No data Found text.
     *
     * @param factsModel The ArrayList to be sent to the adapter.
     */
    override fun loadRecyclerView(factsModel: ArrayList<FactsModel>) {
        if (Utils.notEmptyOrNull(factsModel)) {
            tvNoData!!.visibility = View.GONE
            factsModels!!.clear()
            factsModels!!.addAll(factsModel)
            adapter!!.notifyDataSetChanged()
        } else {
            tvNoData!!.visibility = View.VISIBLE
        }
    }

    /**
     * This method will set the refreshing status of SwipeRefreshLayout according to the parameter.
     *
     * @param showRefresh The status to set.
     */
    override fun changeRefreshStatus(showRefresh: Boolean) {
        srlRefresh!!.isRefreshing = showRefresh
    }

    /**
     * This method will show an error toast if there is any Error while calling the API.
     */
    override fun showError() {
        tvNoData!!.visibility = View.VISIBLE
        Toast.makeText(this, R.string.error_while_fetching_facts, Toast.LENGTH_LONG).show()
    }

    /**
     * This method is used to save the current state of the Activity
     * in our case we are saving the data to be shown in the recyclerview.
     *
     * @param outState The bundle in which we save our state.
     */
    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(FACTS_LIST, factsModels)
        outState.putString(SCREEN_TITLE, screenTitle)
        super.onSaveInstanceState(outState)
    }
}
