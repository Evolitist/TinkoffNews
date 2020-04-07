package com.evolitist.tinkoffnews.di.components

import com.evolitist.tinkoffnews.di.FragmentScope
import com.evolitist.tinkoffnews.di.modules.PresenterModule
import com.evolitist.tinkoffnews.view.entry.EntryFragment
import com.evolitist.tinkoffnews.view.list.ListFragment
import dagger.Component

@Component(modules = [PresenterModule::class])
@FragmentScope
interface FragmentComponent {
    fun injectListFragment(fragment: ListFragment)
    fun injectEntryFragment(fragment: EntryFragment)
}
