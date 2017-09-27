package org.organizesport.android.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

import com.jakewharton.rxbinding2.view.clicks

import io.reactivex.android.schedulers.AndroidSchedulers

import kotlinx.android.synthetic.main.activity_login.*

import org.jetbrains.anko.toast
import org.organizesport.android.BaseApplication
import org.organizesport.android.LoginContract
import org.organizesport.android.R
import org.organizesport.android.presenter.LoginActivityPresenter
import ru.terrakok.cicerone.Navigator

import ru.terrakok.cicerone.commands.*

/**
 * This class refers to the 'LoginActivity' View, following the MVP architectural pattern.
 *
 * The 'LoginContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author psor1i
 * @since 1.0
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {
    companion object {
        val TAG = "LoginActivity"
    }

    // Reference to an interface, so that polymorphism is used later
    private var presenter: LoginContract.Presenter? = null
    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    SampleActivity.TAG -> startActivity(Intent(this@LoginActivity, SampleActivity::class.java))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    private val tvTitle: TextView? by lazy { tv_title_activity_login }
    private val etEmail: EditText? by lazy { et_email_activity_login }
    private val etPassword: EditText? by lazy { et_password_activity_login }
    private val btnLogin: Button? by lazy { btn_login_activity_login }
    private val btnRegister: Button? by lazy { btn_register_activity_login }
    private val tbAccessModeStatus: ToggleButton? by lazy { tb_access_mode_status_activity_login }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginActivityPresenter(this,
                BaseApplication.INSTANCE.cicerone.router)

        btnLogin?.addClickAction({
            presenter?.loginClicked(etEmail?.text.toString(), etPassword?.text.toString())
        })
        tbAccessModeStatus?.addClickAction({
            presenter?.accessModeStatusClicked()
        })
        btnRegister?.addClickAction({ toast("Register clicked") })
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(this.navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    /**
     *
     */
    private fun Button.addClickAction(f: () -> Unit): Unit {
        this.clicks()
                .observeOn(AndroidSchedulers.mainThread())   // subscribers post on the UI thread
                .subscribe { f() }
    }

    override fun showLoginUi() {
        Log.d(TAG, "In-showLoginUi()")
        tvTitle?.text = this.getString(R.string.tv_login_text)
        btnRegister?.visibility = View.INVISIBLE
        btnLogin?.visibility = View.VISIBLE
    }

    override fun showRegisterUi() {
        Log.d(TAG, "In-showRegisterUI()")
        tvTitle?.text = this.getString(R.string.tv_register_text)
        btnRegister?.visibility = View.VISIBLE
        btnLogin?.visibility = View.INVISIBLE
    }

    override fun showLoginSuccessful() {
        toast("Login successful")
    }

    override fun showLoginError() {
        toast("Login error")
    }

    override fun accessApplication() {
//        presenter?.accessModeStatusClicked()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}
