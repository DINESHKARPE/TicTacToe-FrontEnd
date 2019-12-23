package com.pani.tictactoe.ui.newuser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.pani.tictactoe.R
import com.pani.tictactoe.StandardActivity
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.RetrofitRxWrapper
import com.pani.tictactoe.ui.liveuser.LiveUserActivity
import kotlinx.android.synthetic.main.activity_login.*


class AddUserActivity : StandardActivity(), AddUserDelegate {

    private var presenter: AddUserPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = AddUserPresenter(this, RetrofitRxWrapper())
        bindViews()

        startNextActivity()

    }


    private fun bindViews() {


        addButton.setOnClickListener { clickAddButton() }

    }


    fun clickAddButton() {
        if (isUiValid()) {
            presenter?.addUser()
        }
    }

    fun clickBackButton() {
        finish()
    }

    private fun isUiValid(): Boolean {
        var isUiValid = true

        if (userName.text.toString().trim { it <= ' ' }.isEmpty()) {
            userNameInputLayout.requestFocus()
            userNameInputLayout.isErrorEnabled = true
            userNameInputLayout.error = getString(R.string.error_user)
            isUiValid = false
            return isUiValid
        } else {
            userNameInputLayout.isErrorEnabled = false
        }

        return isUiValid
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                clickBackButton()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun getName(): String {
        return userName.text.toString()
    }

    override fun finishActivity() {
        finish()
    }

    override fun hideKeyboard() {
        deliverr.deliverrconsumer.utils.hideKeyboard(this)
    }

    override fun removeUserNameError() {
        if (userNameInputLayout.isErrorEnabled) {
            userNameInputLayout.error = null
            userNameInputLayout.isErrorEnabled = false
        }
    }


    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun clearData() {
        userName.setText("")
    }

    override fun redirectUser() {
        LiveUserActivity.launch(this)
        finish()
    }

    private fun startHomeActivity() {
        LiveUserActivity.launch(this)
        finish()
    }
    fun startNextActivity() {
        if (sharedPrefs?.isJoin!!) {
            startHomeActivity()
        }
    }
    override fun showInputError(message: String?) {
        userNameInputLayout.requestFocus()
        userNameInputLayout.isErrorEnabled = true
        userNameInputLayout.error = message
    }

    override fun showLiveUser(name: String) {
        sharedPrefs?.isJoin = true
        sharedPrefs?.userName = getName()
        startHomeActivity()
    }


    private fun updateUI() {
        presenter?.addUserWrapper()
    }


    companion object {
        fun getLauncherIntent(context: Context): Intent {
            return Intent(context, AddUserActivity::class.java)
        }


//        fun launch(context: Context, engineer: User) {
//            context.startActivity(
//                getLauncherIntent(
//                    context,
//                    engineer
//                )
//            )
//        }

        fun launch(context: Context) {
            context.startActivity(
                getLauncherIntent(
                    context
                )
            )
        }

//        fun getLauncherIntent(
//            context: Context,
//            engineer: User
//        ): Intent {
//            return Intent(context, AddUserActivity::class.java).apply {
//            }
//        }
    }
}
