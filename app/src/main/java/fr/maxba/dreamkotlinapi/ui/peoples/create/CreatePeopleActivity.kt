package fr.maxba.dreamkotlinapi.ui.peoples.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import fr.maxba.dreamkotlinapi.R
import fr.maxba.dreamkotlinapi.databinding.ActivityCreatePeopleBinding
import fr.maxba.dreamkotlinapi.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePeopleActivity : BaseActivity<CreatePeopleViewModel, ActivityCreatePeopleBinding>() {

    override val layout: Int = R.layout.activity_create_people

    override val viewModel: CreatePeopleViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_people, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.confirm -> {
            viewModel.insert()
            ActivityCompat.finishAfterTransition(this@CreatePeopleActivity)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun setupViews() {
        binding.nameEditText.requestFocus()
    }
}
