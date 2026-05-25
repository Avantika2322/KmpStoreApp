import Foundation
import Shared

@MainActor
class DetailsObservableViewModel: ObservableObject {

    let viewModel: DetailsViewModel

    @Published var uiState = DetailsUiState(
        isLoading: false,
        error: "",
        productDetails: nil,
    )

    private var watcher: Closeable?

    init(viewModel: DetailsViewModel) {

        self.viewModel = viewModel

        observe()
    }

    private func observe() {

        watcher = viewModel.uiStateCommon.watch { [weak self] state in

            guard let self = self else { return }
            guard let state = state else { return }

            self.uiState = state
        }
    }

    func getProductDetail(id: Int32) {
        viewModel.getProductDetail(id: id)
    }
}
