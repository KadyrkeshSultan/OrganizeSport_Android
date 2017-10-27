package org.organizesport.android.interactor

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.organizesport.android.SportsListContract
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import org.organizesport.android.BaseApplication
import org.organizesport.android.utils.isNetworkConnected
import javax.inject.Inject

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

    @Inject
    lateinit var context: Context
    private val fbAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }
    private val fbDatabase: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    private var data: String? = null
    private val sportsListListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d(TAG, "sportsListListener, load:onSuccess")
            val list = mutableListOf<String>()
            dataSnapshot.children.mapNotNullTo(list) { it.value as String }
            output?.onSportsListLoaded(list)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
            output?.onDataError()
        }
    }
    private val userSportsListListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d(TAG, "userSportsListListener, load:onSuccess")
            val list = mutableListOf<String>()
            dataSnapshot.children.mapNotNullTo(list) { it.value as String }
            if (data != null) {
                if (!list.remove(data!!)) {
                    list.add(data!!)
                }
                data = null
            }
            output?.onUserSportsListLoaded(list)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
            output?.onDataError()
        }
    }

    // Inject Dagger 2 dependencies
    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun loadSportsList() {
        this.fetchData("sports", sportsListListener)
    }

    override fun loadUserSportsList() {
        this.fetchData("users/${fbAuth?.currentUser?.uid}/sports", userSportsListListener)
    }

    override fun queryUserData(dataKey: String, newData: String?) {
        if (isNetworkConnected(context)) {
            data = newData
            fbDatabase.child("users").child(fbAuth?.currentUser?.uid).child(dataKey)
                    .addListenerForSingleValueEvent(userSportsListListener)
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun updateUserData(dataKey: String, data: List<String>) {
        if (isNetworkConnected(context)) {
            fbDatabase.child("users").child(fbAuth?.currentUser?.uid).child(dataKey).setValue(data)
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun unregister() {
        output = null
    }

    private fun fetchData(dataKey: String, listener: ValueEventListener) {
        if (isNetworkConnected(context)) {
            fbDatabase.child(dataKey).addValueEventListener(listener)
        } else {
            output?.noNetworkAccess()
        }
    }
}
