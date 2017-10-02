package org.organizesport.android.interactor

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.organizesport.android.BaseApplication
import org.organizesport.android.LoginContract
import org.organizesport.android.presenter.LoginActivityPresenter
import org.organizesport.android.utils.isNetworkConnected
import javax.inject.Inject

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

    @Inject
    lateinit var context: Context
    private val auth: FirebaseAuth? by lazy { FirebaseAuth.getInstance() }

    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun login(email: String, password: String) {
        if (isNetworkConnected(context)) {
            auth?.signInWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            presenter?.loginSuccessful()
                        } else {
                            presenter?.loginError()
                        }
                    }
        } else {
            presenter?.noNetworkAccess()
        }

    }

    override fun register(email: String, password: String) {
        if (isNetworkConnected(context)) {
            auth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            presenter?.registerSuccessful()
                        } else {
                            presenter?.registerError()
                        }
                    }
        } else {
            presenter?.noNetworkAccess()
        }
    }
}
