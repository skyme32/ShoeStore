package com.udacity.shoestore.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailShoeBinding
import timber.log.Timber


class DetailShoeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailShoeBinding>(
            inflater,
            R.layout.fragment_detail_shoe,
            container,
            false
        )

        // Get the viewmodel
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this

        //ARGS shoe to above view
        val args = DetailShoeFragmentArgs.fromBundle(requireArguments())
        Timber.i("Edit the shoe: ${args.shoe}")
        args.shoe.let {
            if (it != null) {
                viewModel.shoe = it
            }
        }

        with(viewModel) {
            val observer = Observer<Any> { viewModel.updateflagButtonSave() }
            title.observe(viewLifecycleOwner, observer)
            sizer.observe(viewLifecycleOwner, observer)
            companiest.observe(viewLifecycleOwner, observer)
            descriptions.observe(viewLifecycleOwner, observer)
        }

        binding.btnCancelDetail.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(DetailShoeFragmentDirections.actionDetailShoeFragmentToShoeListFragment())
        }

        binding.btnDeleteDetail.setOnClickListener { view: View ->
            mainViewModel.deleteShoe(viewModel.shoe)
            view.findNavController()
                .navigate(DetailShoeFragmentDirections.actionDetailShoeFragmentToShoeListFragment())
        }

        binding.btnSaveDetail.setOnClickListener { view: View ->
            if (viewModel.flagisEdit.value!!) {
                mainViewModel.updateShoe(viewModel.shoe)
            } else {
                mainViewModel.addShoe(viewModel.shoe)
            }

            view.findNavController()
                .navigate(DetailShoeFragmentDirections.actionDetailShoeFragmentToShoeListFragment())
        }

        return binding.root
    }

}