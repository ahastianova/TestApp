package ru.atruskova.koshelek.ui.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.action_bar.*
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.base.GalleryViewModelFactory
import ru.atruskova.koshelek.data.database.entity.PhotoEntity
import ru.atruskova.koshelek.helper.views.Common
import ru.atruskova.koshelek.ui.base.BaseFragment


class GalleryFragment : BaseFragment<GalleryViewModel>() {

    private var breedName: String? = null
    private var subbreedName: String? = null
    private var isFavoriteMode: Boolean = false
    private var isSubbreedMode = false
    override val layoutId: Int
        get() = R.layout.fragment_gallery
    override lateinit var viewModel: GalleryViewModel
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Common.BREED_NAME)?.let {
            breedName = it
        }
        arguments?.getString(Common.SUBBREED_NAME)?.let {
            isSubbreedMode = true
            subbreedName = it
        }
        arguments?.getString(Common.IS_FAVORITE_MODE)?.let {
            isFavoriteMode = true
        }
        viewModel = ViewModelProviders.of(
            this,
            GalleryViewModelFactory(breedName, subbreedName, isSubbreedMode, isFavoriteMode)
        )[GalleryViewModel::class.java]
    }

    override fun initViews() {
        title_text.text = if (isSubbreedMode) subbreedName ?: "" else breedName ?: ""
        title_nav_btn.apply {
            text = getText(R.string.back)
            visibility = View.VISIBLE
            setOnClickListener {
                activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
            }
        }
        btn_share.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "image/jpeg"

                share.putExtra(
                    Intent.EXTRA_STREAM,
                    Uri.parse(viewModel.getCurrentPhotoPath())
                )

                startActivity(Intent.createChooser(share, "Share Image"))
            }
        }

        btn_like.setOnClickListener {
            viewModel.onLikeClick()
        }


        galleryAdapter = GalleryAdapter(viewModel)
        breed_photos.apply {
            adapter = galleryAdapter
            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.setCurrentPhoto(galleryAdapter.getItemByPosition(position))
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
        }


    }

    override fun initViewModel(owner: LifecycleOwner) {
        super.initViewModel(owner)
        viewModel.pathsList.observe(viewLifecycleOwner, Observer {
            onPathsListChanged(it)
        })

        viewModel.currentPhoto.observe(viewLifecycleOwner, Observer {
            onCurrentPhotoChanged(it)
        })

    }

    private fun onPathsListChanged(list: List<String>) {
        //not true
        if (list.isEmpty()&&viewModel.isLoadingLiveData.value==false) {
            requireActivity().findNavController(R.id.nav_host_fragment).popBackStack()
            return
        }
        galleryAdapter.submitList(list)
    }

    private fun onCurrentPhotoChanged(item: PhotoEntity) {
        btn_like.apply {
            if (visibility == View.INVISIBLE)
                visibility = View.VISIBLE
            if (item.isFavorite)
                setImageResource(R.drawable.ic_baseline_favorite_24)
            else
                setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }


    }
}