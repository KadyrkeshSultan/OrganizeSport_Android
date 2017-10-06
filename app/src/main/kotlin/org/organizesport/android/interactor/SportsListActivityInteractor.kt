package org.organizesport.android.interactor

import android.util.Log
import com.google.firebase.database.*
import org.organizesport.android.SportsListContract

/**
 * This class refers to the interactor attached to the {@link SportsListActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class SportsListActivityInteractor(private var output: SportsListContract.InteractorOutput?) : SportsListContract.Interactor {

    companion object {
        private val TAG: String = "SportsListInteractor"
    }
    private val fbDatabase: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }

    override fun fetchData() {
        val sportsListListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "loadPost:onSuccess ${dataSnapshot.key}")
                val list = mutableListOf<String>()
                dataSnapshot.children.mapNotNullTo(list) { it.value as String }
                output?.onDataFetched(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
                output?.onDataError()
            }
        }

        fbDatabase.child("sports").addValueEventListener(sportsListListener)
//        fbDatabase.child("users").addListenerForSingleValueEvent(sportsListListener)
    }

    override fun unregister() {
        output = null
    }
}
