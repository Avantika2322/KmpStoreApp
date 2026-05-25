import Foundation
import Shared

@MainActor
class SearchObservableViewModel: ObservableObject {

    let viewModel: SearchViewModel

    @Published var uiState = SearchUiState(
        isLoading: false,
        products: [],
        error: nil
    )

    @Published var query: String = "classic-blue-baseball-cap"

    private var uiStateWatcher: (any Closeable_)?
    private var queryWatcher: (any Closeable_)?

    init(viewModel: SearchViewModel) {

        self.viewModel = viewModel

        observe()
    }

    func observe() {

        uiStateWatcher = viewModel.uiStateCommon.watch { [weak self] state in

            guard let self = self else { return }
            guard let state = state else { return }

            self.uiState = state
        }

        queryWatcher = viewModel.queryCommon.watch { [weak self] query in

            guard let self = self else { return }

            self.query = query as? String ?? ""
        }
    }

    func updateQuery(_ text: String) {
        viewModel.updateQuery(query: text)
    }
}
