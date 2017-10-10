package org.organizesport.android.interactor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
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

    private val fbAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }
    private val fbDatabase: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    private var data: String? = null
    private val sportsListListener = object : ValueEventListener {
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
    private val userSportsListListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d(TAG, "loadPost:onSuccess ${dataSnapshot.key}")
            val list = mutableListOf<String>()
            dataSnapshot.children.mapNotNullTo(list) { it.value as String }
            if (data != null) {
                if (!list.remove(data!!)) {
                    list.add(data!!)
                }
                data = null
                output?.onQueryResult(list)
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
            output?.onDataError()
        }
    }

    override fun fetchData(dataKey: String) {
        fbDatabase.child(dataKey).addValueEventListener(sportsListListener)
    }

    override fun queryUserData(dataKey: String, newData: String?) {
        data = newData
        fbDatabase.child("users").child(fbAuth?.currentUser?.uid).child(dataKey)
                .addListenerForSingleValueEvent(userSportsListListener)
    }

    override fun updateUserData(dataKey: String, data: List<String>) {
        fbDatabase.child("users").child(fbAuth?.currentUser?.uid).child(dataKey).setValue(data)
    }

    override fun unregister() {
        output = null
    }
}
