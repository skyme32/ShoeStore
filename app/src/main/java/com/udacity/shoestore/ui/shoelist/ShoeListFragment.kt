package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.NavShoesDirections
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /**
        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )**/
        val binding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailShoeFragment(null))
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                //Timber.i(shoe.toString())
                createItem(shoe, binding.linerShoes)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                /**
                 * I'm not sure is the best transaction, but works, if you can told me if the best or suggest for this problem,
                 * before the course i develop some applications add finish on the activity, but now i'm not sure.
                 * I tried supportfragment, but i can't get works.
                 */
                findNavController().navigate(NavShoesDirections.actionToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createItem(shoeItem: Shoe, linerShoes: LinearLayout) {
        val inflater = LayoutInflater.from(linerShoes.context)
        val binding = DataBindingUtil.inflate<ItemShoeBinding>(
            inflater,
            R.layout.item_shoe,
            linerShoes,
            true
        )

        with(binding) {
            shoe = shoeItem
            root.setOnClickListener(View.OnClickListener { clickInLinearLayout(shoeItem)})
        }
    }


    private fun clickInLinearLayout(shoe: Shoe) {
        Timber.i(shoe.toString())
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailShoeFragment(shoe))
    }

}