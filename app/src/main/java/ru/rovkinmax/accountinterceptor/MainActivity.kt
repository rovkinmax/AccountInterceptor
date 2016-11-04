package ru.rovkinmax.accountinterceptor

import android.accounts.AccountManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.rovkinmax.accountinterceptor.account.AuthUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnIntercept.setOnClickListener {
            val am = AccountManager.get(this)
            tvAccount.text = buildString {
                am.getAccountsByType(BuildConfig.ACCOUNT_TYPE).forEach { account ->
                    try {
                        append("${account?.name}\n${account?.type}")
                        append("\n${AuthUtil.getToken(this@MainActivity)}")
                        append("\n\n")
                    } catch(e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
