package com.pocket.contacts.di

import com.pocket.contacts.interfaces.ItemClickListener
import com.pocket.contacts.interfaces.ItemClickListenerImpl
import dagger.Module
import dagger.Provides

@Module
class UiModule {
    @Provides
    fun providesItemClickListener(): ItemClickListener {
        return ItemClickListenerImpl()
    }
}