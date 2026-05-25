import SwiftUI
import Shared
import SearchUi
import DetailUi
import Foundation

struct ContentView: View {

    @StateObject private var navHostController = NavHostController()

    var body: some View {

        NavigationStack(path: $navHostController.path) {

            SearchView { movieId in

                navHostController.navigateTo(
                    path: Destination.details(movieId)
                )

            }
            .navigationDestination(for: Destination.self) { destination in

                switch destination {

//                case .search:
//                    EmptyView()

                case .details(let movieId):
                    DetailsView(movieId: movieId)
                }
            }
        }
        .frame(
            maxWidth: .infinity,
            maxHeight: .infinity,
            alignment: .top
        )
    }
}


enum Destination: Hashable {
    case details(Int)
}

@MainActor
class NavHostController: ObservableObject {

    @Published var path = NavigationPath()

    func navigateTo(path destination: Destination) {
        path.append(destination)
    }

    func popBackStack() {
        path.removeLast()
    }
}
