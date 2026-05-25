// The Swift Programming Language
// https://docs.swift.org/swift-book

import SwiftUI
import Shared
import Combine
import KMPObservableViewModelCore
import KMPObservableViewModelSwiftUI

public struct SearchView: View {

    @StateObject
    private var vm = SearchObservableViewModel(
        viewModel: SearchViewModelProvider().provideSearchViewModel()
    )

    let action: (Int) -> Void

    public init(action: @escaping (Int) -> Void) {
        self.action = action
    }

    public var body: some View {

        VStack(spacing: 0) {

            TextField(
                "Search Movies",
                text: Binding(
                    get: { vm.query },
                    set: { vm.updateQuery($0) }
                )
            )
            .textFieldStyle(.roundedBorder)
            .padding()

            contentView
        }
    }

    @ViewBuilder
    private var contentView: some View {

        if vm.uiState.isLoading {

            VStack {
                Spacer()
                ProgressView()
                Spacer()
            }

        } else if let error = vm.uiState.error {

            VStack {
                Spacer()
                Text(error)
                Spacer()
            }

        } else if !vm.uiState.products.isEmpty {

            List(vm.uiState.products, id: \.id) { item in

                Text(item.title)
                    .onTapGesture {
                        action(Int(item.id))
                    }
            }

        } else {

            VStack {
                Spacer()
                Text("No Data Found")
                Spacer()
            }
        }
    }
}
