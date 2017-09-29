package org.organizesport.android.interactor

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.organizesport.android.LoginContract
import org.organizesport.android.presenter.LoginActivityPresenter
import org.organizesport.android.utils.Utils

/**
 * This class refers to the interactor attached to the {@link LoginActivity} view and related
 * entities.
 *
 * @author psor1i
 * @since 1.0
 */
class LoginActivityInteractor(private val presenter: LoginActivityPresenter?): LoginContract.Interactor {

    companion object {
        private val TAG: String = "LoginActivityInteractor"
    }


    private val auth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    override fun login(email: String, password: String) {
        auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        presenter?.loginSuccessful()
                    } else {
                        presenter?.loginError()
                    }
                }

//        Utils.isNetworkConnected(this)

    }

    override fun register(email: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}