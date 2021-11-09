package edu.fullerton.ecs.mdap.intersectiondb.intersectionitem

import android.app.Application
import androidx.lifecycle.*
import edu.fullerton.ecs.mdap.intersectiondb.database.Intersection
import edu.fullerton.ecs.mdap.intersectiondb.database.IntersectionDao
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding intersection
 * with the provided intersection ID.
 */
class IntersectionItemViewModel(
    val intersectionId: Long,
    val database: IntersectionDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped intersection object given its ID
    val intersection = database.get(intersectionId)
}