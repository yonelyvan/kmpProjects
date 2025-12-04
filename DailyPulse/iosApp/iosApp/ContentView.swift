import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false
    
    
    var body: some View {
        let articlesScreen = ArticlesScreen(viewModel: .init())
        let sourcesScreen = SourcesScreen(viewModel: .init())
        
        NavigationStack{
            ArticlesScreen(viewModel:  .init())
                .toolbar {
                    // Botón (Sources)
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button {
                            shouldOpenSources = true
                        } label: {
                            Label("Sources", systemImage: "list.bullet")
                        }
                        .popover(isPresented: $shouldOpenSources){
                            sourcesScreen
                        }
                    }
                    
                    // Botón (About)
                    ToolbarItem(placement: .navigationBarTrailing){
                        
                        Button{
                            shouldOpenAbout = true
                        }
                        label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout){
                            AboutScreen()
                        }
                        
                    }
                   
                  
                    
                }
        }.refreshable {
            articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
      
    }
}



