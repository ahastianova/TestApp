package ru.atruskova.koshelek.ui.breedsList

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_breeds_list.*
import kotlinx.android.synthetic.main.action_bar.*
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseFragment

class BreedsListFragment : BaseFragment<BreedsListViewModel>() {

    private lateinit var adapter: BreedsListAdapter

    override val layoutId: Int
        get() = R.layout.fragment_breeds_list

    override lateinit var viewModel: BreedsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  ViewModelProviders.of(this).get(BreedsListViewModel::class.java)
    }

    override fun initViews() {
        title_text.text = getText(R.string.title_breeds)
        adapter = BreedsListAdapter(viewModel)
        breeds.adapter = adapter
    }

    override fun initViewModel(owner: LifecycleOwner) {
        super.initViewModel(owner)
        viewModel.breedsList.observe(viewLifecycleOwner, Observer {
            onBreedsListChanged(it)
        })

        viewModel.navigateToFragment.observe(viewLifecycleOwner,
            Observer {
                onNavigateFragment(it)
            })
    }

    private fun onBreedsListChanged(list: List<BreedWithCounter>) {
        adapter.submitList(list)
    }

    private fun onNavigateFragment(args: Pair<Screen, List<Pair<String, String>>>) {
        var bundle = Bundle()
        for (arg: Pair<String, String> in args.second) {
            bundle.putString(arg.first, arg.second)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.let {
            when (args.first) {
                Screen.GALLERY -> it.navigate(
                    R.id.action_navigation_list_to_galleryFragment,
                    bundle
                )
                Screen.SUBBREEDS -> it.navigate(
                    R.id.action_navigation_list_to_subbreedsListFragment,
                    bundle
                )
            }
        }
    }

}