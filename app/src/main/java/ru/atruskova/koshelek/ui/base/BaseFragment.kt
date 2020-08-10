package ru.atruskova.koshelek.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import ru.atruskova.koshelek.R


abstract class BaseFragment<VM>: Fragment() {
    abstract val layoutId : Int

    abstract var viewModel : VM

    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel(viewLifecycleOwner)
    }

    @CallSuper
    protected open fun initViewModel(owner: LifecycleOwner) {
        if (viewModel is BaseViewModel) {
            with(viewModel as BaseViewModel) {
                errorLiveData.observe(owner, Observer { onErrorMessage(it) })
                isLoadingLiveData.observe(owner, Observer { onLoading(it) })
            }
        }
    }

    protected open fun onLoading(isLoading: Boolean) {
            activity?.progressBar?.apply {
                if (isLoading)
                    show()
                else
                    hide()
            }
    }

    protected open fun onErrorMessage(message: String) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this.requireContext()).create()
        alertDialog.apply {
            setTitle(getString(R.string.api_exception_error_sub))
            setMessage(message)
            setButton(AlertDialog.BUTTON_NEUTRAL, "OK") { dialog, _ ->
                dialog.dismiss()
            }
        }
        alertDialog.show()
    }

}