package me.pisal.abaclone.scene.notifcation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.pisal.abaclone.scene.notifcation.announcement.AnnouncementNotiFragment
import me.pisal.abaclone.scene.notifcation.transaction.TransactionNotiFragment

class NotificationPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    private val fragments = listOf(TransactionNotiFragment(), AnnouncementNotiFragment())
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}