package fr.maxba.dreamkotlinapi.ui.peoples.detail

import android.os.Bundle
import fr.maxba.dreamkotlinapi.R
import fr.maxba.dreamkotlinapi.databinding.ActivityDetailPeopleBinding
import fr.maxba.dreamkotlinapi.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPeopleActivity : BaseActivity<DetailPeopleViewModel, ActivityDetailPeopleBinding>() {

    override val layout: Int = R.layout.activity_detail_people

    override val viewModel: DetailPeopleViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.peopleId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
