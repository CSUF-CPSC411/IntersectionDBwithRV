package edu.fullerton.ecs.mdap.intersectiondb.intersectionlist

import android.app.Application
import androidx.lifecycle.*
import edu.fullerton.ecs.mdap.intersectiondb.database.Intersection
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class IntersectionViewModel(
    val database: IntersectionDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var location = MutableLiveData("")

    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val intersectionList = database.getAllIntersections()

    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var intersection = Intersection()
            intersection.name = name.value.toString()
            intersection.location = location.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(intersection)
        }
    }

    /**
     * Deletes all Intersection entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}