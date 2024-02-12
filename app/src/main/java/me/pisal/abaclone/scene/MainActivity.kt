package me.pisal.abaclone.scene

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import me.pisal.abaclone.R
import me.pisal.abaclone.data.repo.FakerManager
import me.pisal.abaclone.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initFaker()
    }

    private fun initFaker() {
        lifecycleScope.launch {
            FakerManager.init()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if ((ev?.pointerCount ?: 0) > 1) {
            ev?.action = MotionEvent.ACTION_CANCEL
        }
        return super.dispatchTouchEvent(ev)
    }

    /////////////////////////////////////////
    //#region Permissions
    /////////////////////////////////////////
    private val permissionHandlers: HashMap<String, ((success: Boolean) -> Unit)> = hashMapOf()
    fun requestPermissionThen(
        permission: Permission,
        onSuccess: () -> Unit,
        onDenied: (() -> Unit)? = null,
    ) {
        val handler: (success: Boolean) -> Unit = { success ->
            if (success) {
                onSuccess()
            } else {
                onDenied?.invoke()
            }
        }
        permissionHandlers[permission.value] = handler
        if (!isPermissionGrated(permission)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                permission.requestCode
            )
        } else {
            handler.invoke(true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()) {
            permissionHandlers.forEach {
                if (permissions.contains(it.key)) {
                    it.value.invoke(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                }
            }
        }
    }
    //#endregion
}

enum class Permission(val value: String, val requestCode: Int) {
    CAMERA(Manifest.permission.CAMERA, 111)
}