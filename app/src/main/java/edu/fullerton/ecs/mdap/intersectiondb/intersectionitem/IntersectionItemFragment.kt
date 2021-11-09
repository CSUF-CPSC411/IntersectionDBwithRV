package edu.fullerton.ecs.mdap.intersectiondb.intersectionitem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.R
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDatabase
import edu.fullerton.ecs.mdap.intersectiondb.databinding.IntersectionItemFragmentBinding
import edu.fullerton.ecs.mdap.intersectiondb.intersectionlist.IntersectionViewModel
import edu.fullerton.ecs.mdap.intersectiondb.intersectionlist.IntersectionViewModelFactory

/**
 * Fragment that displays a single intersection.
 */
class IntersectionItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve arguments passed from the RecyclerView
        val args = IntersectionItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: IntersectionItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.intersection_item_fragment, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        // Retrieve Intersection data access object.
        val dataSource = IntersectionDatabase.getInstance(application).intersectionDao

        // Create a factory that generates an IntersectionViewModel connected to the database. The
        // intersectionId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            IntersectionItemViewModelFactory(args.intersectionId, dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val intersectionItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(IntersectionItemViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.intersectionItemViewModel = intersectionItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}