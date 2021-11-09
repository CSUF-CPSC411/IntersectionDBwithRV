package edu.fullerton.ecs.mdap.intersectiondb.intersectionlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.fullerton.ecs.mdap.intersectiondb.R
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDatabase
import edu.fullerton.ecs.mdap.intersectiondb.databinding.IntersectionListFragmentBinding

/**
 * Fragment that displays the input text fields and intersection list
 */
class IntersectionListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: IntersectionListFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.intersection_list_fragment, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve Intersection data access object.
        val dataSource = IntersectionDatabase.getInstance(application).intersectionDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = IntersectionViewModelFactory(dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val intersectionViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(IntersectionViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.intersectionViewModel = intersectionViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        // Provide a lambda function that is called when the RacyclerView item is selected.
        var intersectionAdapter = IntersectionListAdapter(IntersectionListener {
            intersectionId ->
            // Navigate to the intersection view provide the id of the intersection that is shown.
            this.findNavController().navigate(
                IntersectionListFragmentDirections
                    .actionIntersectionListFragmentToIntersectionItemFragment(intersectionId)
            )
        })
        // Attach intersection adapter.
        binding.intersectionRecyclerview.adapter = intersectionAdapter

        // Submit an updated list to the intersection listAdapter whenever it changes. Take note
        // intersectionList is a LiveData object.
        intersectionViewModel.intersectionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                intersectionAdapter.submitList(it)
            }
        })
        return binding.root
    }
}