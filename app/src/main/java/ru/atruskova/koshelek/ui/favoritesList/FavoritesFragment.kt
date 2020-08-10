package ru.atruskova.koshelek.ui.favoritesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_breeds_list.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.action_bar.*
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseFragment
import ru.atruskova.koshelek.ui.breedsList.BreedsListAdapter
import ru.atruskova.koshelek.ui.breedsList.BreedsListViewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel>() {

    private lateinit var adapter: FavoritesListAdapter

    override val layoutId: Int
        get() = R.layout.fragment_favorites

    override lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
    }

    override fun initViews() {
        title_text.text = getText(R.string.title_favorites)
        adapter = FavoritesListAdapter(viewModel)
        favorites.adapter = adapter
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

            it.navigate(
                R.id.action_navigation_favorite_to_galleryFragment,
                bundle
            )

        }
    }

}