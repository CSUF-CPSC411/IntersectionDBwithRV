package edu.fullerton.ecs.mdap.intersectiondb.intersectionlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao

/**
 * Generates an IntersectionViewModel tied to the database.
 */
class IntersectionViewModelFactory(
    private val dataSource: IntersectionDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the IntersectionViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntersectionViewModel::class.java)) { // ViewModel class
            return IntersectionViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}