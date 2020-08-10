package ru.atruskova.koshelek.ui.subBreedsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_subbreeds_list.*
import kotlinx.android.synthetic.main.action_bar.*
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.base.SubbreedViewModelFactory
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.helper.views.Common
import ru.atruskova.koshelek.helper.views.Common.BREED_NAME
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseFragment


class SubbreedsListFragment : BaseFragment<SubbreedsListViewModel>() {

    private var breedName: String? = null

    override val layoutId: Int
        get() = R.layout.fragment_subbreeds_list

    private lateinit var adapter: SubbreedsListAdapter

    override lateinit var viewModel: SubbreedsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(BREED_NAME)?.let {
            breedName = it
        }
        viewModel =
            ViewModelProviders.of(
                this,
                SubbreedViewModelFactory(breedName))[SubbreedsListViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    override fun initViews() {
        title_text.text = breedName
        title_nav_btn.apply {
            text = getText(R.string.title_breeds)
            visibility = View.VISIBLE
            setOnClickListener {
                activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
            }
        }

        adapter = SubbreedsListAdapter(viewModel)
        subbreeds.adapter = adapter
    }


    private fun initViewModel() {
        viewModel.subbreedsList.observe(viewLifecycleOwner, Observer {
            onBreedsListChanged(it)
        })

        viewModel.navigateToFragment.observe(viewLifecycleOwner,
            Observer {
                onNavigateFragment(it)
            })
    }

    private fun onBreedsListChanged(list: List<BreedEntity>) {
        adapter.submitList(list)
    }

    private fun onNavigateFragment(args: Pair<Screen, List<Pair<String, String>>>) {
        var bundle = Bundle()
        for (arg: Pair<String, String> in args.second) {
            bundle.putString(arg.first, arg.second)
        }
        activity?.findNavController(R.id.nav_host_fragment)?.let {
            it.navigate(R.id.action_subbreedsListFragment_to_galleryFragment, bundle)
        }
    }
}