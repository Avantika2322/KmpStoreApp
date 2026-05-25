import SwiftUI
import Shared

public struct DetailsView: View {

    let movieId: Int

    @StateObject
    private var vm = DetailsObservableViewModel(
        viewModel: DetailsViewModelProvider()
            .provideDetailsViewModel()
    )

    public init(movieId: Int) {
        self.movieId = movieId
    }

    public var body: some View {

        ZStack {

            if vm.uiState.isLoading {

                ProgressView()

            } else if !vm.uiState.error.isEmpty {

                Text(vm.uiState.error)

            } else if let data = vm.uiState.productDetails {

                ScrollView {

                    VStack(alignment: .leading) {

                        AsyncImage(
                            url: URL(string: data.category.image)
                        ) { phase in

                            switch phase {

                            case .empty:

                                ProgressView()
                                    .frame(maxWidth: .infinity)
                                    .frame(height: 600)

                            case .success(let image):

                                image
                                    .resizable()
                                    .scaledToFill()
                                    .frame(maxWidth: .infinity)
                                    .frame(height: 600)
                                    .clipped()

                            case .failure:

                                Text("No Image Available")
                                    .frame(maxWidth: .infinity)
                                    .frame(height: 600)

                            @unknown default:
                                EmptyView()
                            }
                        }

                        Spacer(minLength: 24)

                        Text(data.title)
                            .font(.title)
                            .padding(.horizontal, 12)

                        Spacer(minLength: 12)

                        Text("\(data.price)")
                            .padding(.horizontal, 12)

                        Spacer(minLength: 64)
                    }
                }
            }
        }
        .ignoresSafeArea()
        .onAppear {

            vm.getProductDetail(
                id: Int32(movieId)
            )
        }
    }
}
