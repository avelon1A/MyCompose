package com.bosch.composewithkotlin20.presentaion.ui.todo.di

import TodoViewModel
import com.bosch.composewithkotlin20.presentaion.ui.todo.data.dataSorce.dataSorceImp.AppDatabase
import com.bosch.composewithkotlin20.presentaion.ui.todo.data.dataSorce.repoImp.TaskRepositoryImpl
import com.bosch.composewithkotlin20.presentaion.ui.todo.data.dataSorce.source.TaskUseCasesImpl
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.repo.TaskRepository
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.usecase.TaskUseCases
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  TodoModule = module {
    single { AppDatabase.getDatabase(androidContext()).taskDao() }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<TaskUseCases> { TaskUseCasesImpl(get()) }
    viewModel { TodoViewModel(get()) }
    }
