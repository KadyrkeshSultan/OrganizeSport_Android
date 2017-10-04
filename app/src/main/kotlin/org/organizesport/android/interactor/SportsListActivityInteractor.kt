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
//                menu.clear()
//                dataSnapshot.children.mapNotNullTo(menu) { it.getValue<String>(String::class.java) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
            }
        }

        fbDatabase.child("sports").addListenerForSingleValueEvent(sportsListListener)
    }

    override fun unregister() {
        output = null
    }
}
