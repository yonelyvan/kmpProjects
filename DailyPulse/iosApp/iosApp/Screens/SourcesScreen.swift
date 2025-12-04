//
//  SurcesScreen.swift
//  iosApp
//
//  Created by yonel on 3/12/25.
//

import SwiftUI
import ComposeApp

extension SourcesScreen{
    
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel
        
        init(){
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.sourcesState.value
        }
        
        @Published var sourcesState: SourcesState
        
        func startObserving(){
            Task {
                for await state in sourcesViewModel.sourcesState {
                    self.sourcesState = state
                }
            }
        }
    }
    
}


struct SourcesScreen: View {
    
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.sourcesState.isLoading {
                Loader()
            }
            
            if let error = viewModel.sourcesState.error {
                ErrorMessage(message: error)
            }
            
            if (!viewModel.sourcesState.sources.isEmpty){
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.sourcesState.sources, id: \.self) {
                            source in SourceItemView(source: source)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}


struct SourceItemView: View {
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
        
            Text(source.name)
                .font(.title)
                .fontWeight(.bold)
            Text(source.desc)
            Text("hola como vay")
            Text(source.language)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundColor(.gray)
        }
        .padding(16)
    }
}
