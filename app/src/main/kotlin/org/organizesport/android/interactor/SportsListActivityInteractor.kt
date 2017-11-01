package org.organizesport.android.interactor

import android.content.Context
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

import org.organizesport.android.BaseApplication
import org.organizesport.android.entity.Sport
import org.organizesport.android.SportsListContract
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

    // Inject Dagger 2 dependencies
    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun loadSportsList() {
        this.fetchData()
    }

    override fun updateUserData(dataKey: String, sport: String?) {
        if (isNetworkConnected(context)) {
            getFirebaseDataList("users/${fbAuth?.currentUser?.uid}/$dataKey")
                    .subscribe({
                        if (it is MutableList<Sport> && !it.remove(Sport(sport!!))) {
                            it.add(Sport(sport))
                        }
                        fbDatabase.child("users").child(fbAuth?.currentUser?.uid).child(dataKey)
                                .setValue(it.map { it.name })
                        this.fetchData()
                    }, { output?.noNetworkAccess() })
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun unregister() {
        output = null
    }

    private fun fetchData() {
        if (isNetworkConnected(context)) {
            Single.zip(
                    getFirebaseDataList("sports"),
                    getFirebaseDataList("users/${fbAuth?.currentUser?.uid}/sports"),
                    BiFunction<List<Sport>, List<Sport>, Unit> { e1, e2 ->
                        output?.onSportsListLoaded(e1.map { it to e2.contains(it) }.toMap())
                    }).subscribe()
        } else {
            output?.noNetworkAccess()
        }
    }

    private fun getFirebaseDataList(dataKey: String) = Single.create<List<Sport>> { emitter ->
        fbDatabase.child(dataKey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "userSportsListListener, load:onSuccess")
                val list = mutableListOf<String>()
                emitter.onSuccess(
                        dataSnapshot.children.mapNotNullTo(list) { it.value as String }
                                .map { Sport(it) }
                                .toMutableList()
                )
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled ${databaseError.toException()}")
                output?.onDataError()
            }
        })
    }.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}
