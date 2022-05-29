package raq.lop.io.marvelkotlinmvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <VB: ViewBinding, VM: ViewModel>: Fragment() {

    private var _biding: VB? = null
    protected val biding get() = _biding!!
    protected abstract val viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _biding = getViewBiding(inflater, container)
        return biding.root
    }

    protected abstract fun getViewBiding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _biding = null
    }
}