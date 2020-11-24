package com.udacity.shoestore.ui.shoelist

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.floatingActionButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailShoeFragment())
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            val inflater = binding.linerShoes

            it.forEach { shoe ->
                Timber.i(shoe.toString())
                createCardView(shoe, inflater)
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
            R.id.share -> findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createCardView(shoe: Shoe, linerShoes: LinearLayout) {
        val cardLinearLayout = LinearLayout(linerShoes.context)
        cardLinearLayout.orientation = LinearLayout.VERTICAL

        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(16,16,16,16)

        val cardView = CardView(linerShoes.context)
        cardView.radius = 15f
        cardView.setContentPadding(16,16,16,16)
        cardView.layoutParams = params

        val quote = TextView(linerShoes.context)
        quote.text = shoe.name
        quote.textSize = 20f
        quote.setTextColor(Color.parseColor("#000000"))

        val name = TextView(linerShoes.context)
        name.text = shoe.company
        name.textSize = 16f

        val desc = TextView(linerShoes.context)
        desc.text = shoe.description
        desc.textSize = 14f

        cardLinearLayout.addView(quote)
        cardLinearLayout.addView(name)
        cardLinearLayout.addView(desc)
        cardView.addView(cardLinearLayout)
        linerShoes.addView(cardView)
    }

}