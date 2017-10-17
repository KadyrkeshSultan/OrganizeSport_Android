package org.organizesport.android.interactor

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.organizesport.android.BaseApplication
import org.organizesport.android.LoginContract
import org.organizesport.android.entity.User
import org.organizesport.android.utils.isNetworkConnected
import javax.inject.Inject

/**
 * This class refers to the interactor attached to the {@link LoginActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class LoginActivityInteractor(private var output: LoginContract.InteractorOutput?): LoginContract.Interactor {

    companion object {
        private val TAG: String = "LoginActivityInteractor"
    }

    @Inject
    lateinit var context: Context
    private val fbAuth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }
    private val fbDatabase: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }

    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun login(email: String, password: String) {
        if (isNetworkConnected(context)) {
            fbAuth?.signInWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            output?.onLoginSuccess()
                        } else {
                            output?.onLoginError()
                        }
                    }
        } else {
            output?.noNetworkAccess()
        }

    }

    override fun register(email: String, password: String) {
        if (isNetworkConnected(context)) {
            fbAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            output?.onRegisterSuccess()
                        } else {
                            output?.onRegisterError()
                        }
                    }
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun createUserIfNotExisting(userEmail: String?) {
        if (isNetworkConnected(context)) {

            fbDatabase.child("users").child(fbAuth?.currentUser?.uid).setValue(
                    User(email = fbAuth?.currentUser?.email, sports = listOf())
            )
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun unregister() {
        output = null
    }
}
