package edu.fullerton.ecs.mdap.intersectiondb.intersectionitem
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao
import edu.fullerton.ecs.mdap.intersectiondb.intersectionlist.IntersectionViewModel

/**
 * Generates an IntersectionViewModel tied to the database. It uses the provided intersection ID to
 * to create the IntersectionItemViewModel.
 */
class IntersectionItemViewModelFactory(
    private val intersectionId: Long,
    private val dataSource: IntersectionDao, // Data access object
    private val application: Application
): ViewModelProvider.Factory {

    /**
     * Creates the IntersectionViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntersectionItemViewModel::class.java)) { // ViewModel class
            return IntersectionItemViewModel(intersectionId, dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}