package fr.maxba.dreamkotlinapi.ui.peoples.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.maxba.dreamkotlinapi.R
import fr.maxba.dreamkotlinapi.data.model.People
import fr.maxba.dreamkotlinapi.data.remote.PeoplesResponseCallback
import fr.maxba.dreamkotlinapi.databinding.ActivityPeoplesBinding
import fr.maxba.dreamkotlinapi.extension.showAction
import fr.maxba.dreamkotlinapi.extension.showError
import fr.maxba.dreamkotlinapi.extension.startAnimatedActivity
import fr.maxba.dreamkotlinapi.ui.base.BaseActivity
import fr.maxba.dreamkotlinapi.ui.peoples.create.CreatePeopleActivity
import fr.maxba.dreamkotlinapi.ui.peoples.detail.DetailPeopleActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.yesButton
import org.koin.androidx.viewmodel.ext.android.viewModel


class PeoplesActivity : BaseActivity<PeoplesViewModel, ActivityPeoplesBinding>() {

    override val layout: Int = R.layout.activity_peoples

    override val viewModel: PeoplesViewModel by viewModel()

    private var peoplesAdapter = PeoplesAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        setupAdapter()
        setupFab()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupAdapter() {
        viewModel.peoples.observe(this, Observer {
            peoplesAdapter.submitList(it)
        })

        peoplesAdapter.apply {
            onClick = { startAnimatedActivity(intentFor<DetailPeopleActivity>("id" to it.id)) }
            onLongClick = { showDeletePopup(it) }
        }
    }

    private fun setupFab() {
        binding.fab.onClick { startAnimatedActivity(intentFor<CreatePeopleActivity>()) }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@PeoplesActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@PeoplesActivity)
            adapter = peoplesAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.apply {

            fun refresh() {
                isRefreshing = true
                viewModel.refresh(object: PeoplesResponseCallback {
                    override fun onSuccess() {
                        binding.root.showAction(getString(R.string.peoples_loaded))
                        isRefreshing = false
                    }

                    override fun onError(throwable: Throwable) {
                        binding.root.showError(getString(R.string.peoples_loading_error))
                        isRefreshing = false
                    }
                })
            }

            setOnRefreshListener { refresh() }
            post { refresh() }
        }
    }

    private fun showDeletePopup(people: People) {
        alert(getString(R.string.delete_people_warning, people.name)) {
            yesButton { viewModel.delete(people) }
            noButton { }
        }.show()
    }
}